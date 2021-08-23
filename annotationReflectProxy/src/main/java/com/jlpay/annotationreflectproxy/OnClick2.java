package com.jlpay.annotationreflectproxy;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventType(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener")
public @interface OnClick2 {
    int[] value();
}
