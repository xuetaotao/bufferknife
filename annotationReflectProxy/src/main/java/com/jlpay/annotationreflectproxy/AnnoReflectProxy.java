package com.jlpay.annotationreflectproxy;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过反射和动态代理处理注解
 */
public class AnnoReflectProxy {

    public static void bind(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            //获得方法上所有注解 @OnClick2/ @OnLongClick2
            Annotation[] methodDeclaredAnnotations = method.getAnnotations();
            for (Annotation annotation : methodDeclaredAnnotations) {
                //注解类型 interface com.jlpay.annotationreflectproxy.OnLongClick2
                Class<? extends Annotation> annotationType = annotation.annotationType();
                //如果此元素上存在指定类型的注释，则返回 true，否则返回 false。 此方法主要是为了方便访问标记注释而设计的。
                if (annotationType.isAnnotationPresent(EventType.class)) {
                    EventType typeAnnotation = annotationType.getAnnotation(EventType.class);
                    //View.OnClickListener.class
                    Class listenerType = typeAnnotation.listenerType();
                    //"setOnClickListener"
                    String listenerSetter = typeAnnotation.listenerSetter();

                    try {
                        // 不需要关心到底是OnClick 还是 OnLongClick
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        valueMethod.setAccessible(true);
                        //获取到 @OnClick2/ @OnLongClick2 注解的参数值，即R.id.btn_onclick2等
                        int[] viewIds = (int[]) valueMethod.invoke(annotation);

                        method.setAccessible(true);
                        ListenerInvocationHandler<Activity> invocationHandler = new ListenerInvocationHandler<>(activity, method);
                        //通过动态代理实现 View.OnClickListener / View.OnLongClickListener 接口的代理类
                        Object proxyInstance = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, invocationHandler);
                        //遍历注解的值
                        if (viewIds != null && viewIds.length > 0) {
                            for (int viewId : viewIds) {
                                // 获得当前activity的view（赋值）
                                View viewById = activity.findViewById(viewId);
                                // 获取指定的方法(不需要判断是Click还是LongClick)
                                // 如获得：setOnClickLisnter方法，参数为OnClickListener
                                // 获得 setOnLongClickLisnter，则参数为OnLongClickLisnter
                                //通过反射获取View的setOnClickListener/setOnLongClickListener方法
                                Method setter = viewById.getClass().getMethod(listenerSetter, listenerType);
                                // 执行setOnclickListener里面的回调 onclick方法
                                setter.invoke(viewById, proxyInstance);
                            }
                        }

                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 还可能在自定义view注入，所以是泛型： T = Activity/View
     */
    static class ListenerInvocationHandler<T> implements InvocationHandler {

        private Method method;
        private T target;

        public ListenerInvocationHandler(T target, Method method) {
            this.method = method;
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.method.invoke(target, args);
        }
    }
}
