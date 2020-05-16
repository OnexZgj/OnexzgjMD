package com.onexzgj.onexzgjmd.aspect;

import android.text.TextUtils;
import android.util.Log;

import com.onexzgj.onexzgjmd.annotation.Async;
import com.onexzgj.onexzgjmd.annotation.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;

import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Aspect
public class LoggerAspect {

    @Around("execution(@com.onexzgj.onexzgjmd.annotation.Logger void *(..))")
    public void printLog(ProceedingJoinPoint joinPoint) {
        /**
         * 执行原代码 获得耗时
         */

        try {
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long end = System.currentTimeMillis();

            Log.e("TAG", "printLog: " + start + " : " + end);

            /**
             * 获得方法签名
             */
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

            Method method = methodSignature.getMethod();
            Logger logger = method.getAnnotation(Logger.class);

            //  根据方法签名 获得参数  type name = value
            Class[] parameterTypes = methodSignature.getParameterTypes();
            String[] parameterNames = methodSignature.getParameterNames();
            Object[] args = joinPoint.getArgs();
            StringBuilder builder = new StringBuilder();
            builder.append(parameterTypes[0].getSimpleName());
            builder.append(" ");
            builder.append(parameterNames[0]);
            builder.append(" = ");
            builder.append(args[0]);
            for (int i = 1; i < parameterTypes.length; i++) {
                builder.append(", ");
                builder.append(parameterTypes[i].getSimpleName());
                builder.append(" ");
                builder.append(parameterNames[i]);
                builder.append(" = ");
                builder.append(args[i]);
            }


            // 根据方法签名 获得返回类型
            String type = methodSignature.getReturnType().getSimpleName();


            /**
             * 获得源码文件位置信息 拼接日志支持可跳转格式的字符串
             */
            SourceLocation sourceLocation = joinPoint.getSourceLocation();
            int lineNumber = sourceLocation.getLine();
            String fileName = sourceLocation.getFileName();
            String fullClassName = methodSignature.getDeclaringTypeName();
            String methodName = methodSignature.getMethod().getName();

            StringBuilder content = new StringBuilder(" \n");
            content.append("╔═════════════════════════════════════════════════════════════════════\n");
            content.append("║ 当前线程: ");
            content.append(Thread.currentThread().getName());
            content.append("\n");
            content.append("╟─────────────────────────────────────────────────────────────────────\n");
            content.append("║ 函数: ");
            content.append(fullClassName);
            content.append(".");
            content.append(methodName);
            content.append("(");
            content.append(fileName);
            content.append(":");
            content.append(lineNumber);
            content.append(")\n");
            content.append("║ 参数: ");
            content.append(builder);
            content.append("\n");
            content.append("╟─────────────────────────────────────────────────────────────────────\n");
            content.append("║ 返回: ");
            content.append(type);
            content.append(" ");
            content.append(TextUtils.equals(type.toLowerCase(), "void") ? "" : "123123");
            content.append("\n");
            content.append("║ 耗时: ");
            content.append(end - start);
            content.append(" ms\n");
            content.append("╚═════════════════════════════════════════════════════════════════════");


            Log.println(logger.value(), methodSignature.getDeclaringType().getSimpleName(), content
                    .toString());

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

}
