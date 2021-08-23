package com.jlpay.annotationreflect;

import android.app.Activity;
import android.view.View;

import com.jlpay.bindview.OnClick;
import com.jlpay.bindview.OnLongClick;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 使用反射来处理注解文件
 */
public class AnnotationReflect {

    public static void bind(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        for (Method method : aClass.getDeclaredMethods()) {
            OnClick onClickAnnotation = method.getAnnotation(OnClick.class);
            if (onClickAnnotation != null) {
                int[] values = onClickAnnotation.value();
                if (values.length > 0) {
                    for (int value : values) {
                        View viewById = activity.findViewById(value);
                        viewById.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    method.setAccessible(true);
                                    method.invoke(activity, v);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }

            OnLongClick onLongClickAnnotation = method.getAnnotation(OnLongClick.class);
            if (onLongClickAnnotation != null) {
                int[] values = onLongClickAnnotation.value();
                if (values.length > 0) {
                    for (int value : values) {
                        View viewById = activity.findViewById(value);
                        viewById.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                method.setAccessible(true);
                                try {
                                    method.invoke(activity, v);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                                return false;
                            }
                        });
                    }
                }
            }
        }
    }
}
