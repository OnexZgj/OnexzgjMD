package com.onexzgj.onexzgjmd.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@Aspect
public class AsyncAspect {

    @Around("execution(@com.onexzgj.onexzgjmd.annotation.Async void *(..))")
    public void doAsyncMethod(final ProceedingJoinPoint joinPoint){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    Log.d("TAG", "subscribe: "+ throwable.getMessage().toString());
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }
}
