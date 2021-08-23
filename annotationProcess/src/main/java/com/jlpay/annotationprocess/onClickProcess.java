package com.jlpay.annotationprocess;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * 本次练习暂时没有用到APT，如果需要使用的话，思路如下：
 * 在process方法中，自己写Java代码字符串 或者 通过 javapoet 生成Java代码
 * 这里便是APT的经典应用的地方：生成Java文件
 */
@SupportedAnnotationTypes({"com.jlpay.bindview.OnClick", "com.jlpay.bindview.OnLongClick"})
public class onClickProcess extends AbstractProcessor {

    /**
     * 可以这么理解：
     * javac 编译过程中会回调该方法
     * 这里可以用来生成Java代码(APT经典应用)，但是生成的Java代码文件位于build目录下，想要使用的话就得使用反射
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Messager messager = processingEnv.getMessager();
        //可以在执行Javac编译的时候，打印出来日志
        messager.printMessage(Diagnostic.Kind.WARNING, "=========>onClickProcess");
        return false;
    }

//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        HashSet<String> stringHashSet = new HashSet<>();
//        stringHashSet.add(OnClick.class.getCanonicalName());
//        stringHashSet.add(OnLongClick.class.getCanonicalName());
//        return stringHashSet;
//    }
}