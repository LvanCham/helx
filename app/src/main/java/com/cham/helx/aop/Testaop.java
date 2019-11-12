package com.cham.helx.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Hello World
 * Date: 2019/11/7
 * Author: Cham
 */
@Aspect
public class Testaop {

    //获取该Activity下的所有on开头的方法耗时
    @Around("execution(* *..MainActivity+.on**(..))")
    public Object getTime(ProceedingJoinPoint joinPoint) {
        Object proceed = null;
        long start = System.currentTimeMillis();
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        Log.d("IntercepLifeCycleAOP", joinPoint.getSignature().getName() + ":执行时间:" + (end - start));
        return proceed;

    }
}
