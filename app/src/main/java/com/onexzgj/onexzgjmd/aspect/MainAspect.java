package com.onexzgj.onexzgjmd.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Aspect
public class MainAspect {

    @Around("execution(@com.onexzgj.onexzgjmd.annotation.Main void *(..))")
    public void doMainAspect(final ProceedingJoinPoint joinPoint){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
    }

}
