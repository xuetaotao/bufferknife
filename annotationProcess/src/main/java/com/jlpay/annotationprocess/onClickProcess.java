package com.jlpay.annotationprocess;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * 这里便是APT的经典应用的地方：生成Java文件
 */
@SupportedAnnotationTypes({"com.jlpay.bindview.onClick", "com.jlpay.bindview.onLongClick"})
public class onClickProcess extends AbstractProcessor {

    /**
     * 可以这么理解：
     * javac 编译过程中会回调该方法
     * 这里可以用来生成Java代码(APT经典应用)，但是生成的Java代码文件位于build目录下，想要使用的话就得使用反射
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "=====>onClickProcess");
        return false;
    }

//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return super.getSupportedAnnotationTypes();
//    }
}