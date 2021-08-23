package com.jlpay.annotationreflectproxy;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventType(listenerType = View.OnLongClickListener.class, listenerSetter = "setOnLongClickListener")
public @interface OnLongClick2 {
    int[] value();
}
