package com.onexzgj.onexzgjmd.annotation;

import org.aspectj.lang.JoinPoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 子线程中运行
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface Async {

}
