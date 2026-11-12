package com.site.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {


    @Around("execution(* com.site.service.StudentService.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        System.out.println();
        System.out.println("[AOP AROUND] Executing: "
                + joinPoint.getSignature().getName());

        Object result;

        try {
            result = joinPoint.proceed();
        } finally {

            long endTime = System.currentTimeMillis();

            System.out.println("[AOP AROUND] Execution time: "
                    + (endTime - startTime) + " ms");
        }

        return result;
    }


    @Before("execution(* com.site.service.StudentService.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {

        System.out.println(
                "[AOP BEFORE] Student service method is starting..."
        );
    }


    @AfterReturning(
            pointcut = "execution(* com.site.service.StudentService.*(..))",
            returning = "result"
    )
    public void afterReturningMethod(
            JoinPoint joinPoint,
            Object result
    ) {

        System.out.println(
                "[AOP AFTER RETURNING] Method executed successfully."
        );

        if (result != null) {

            System.out.println("[AOP] Returned result: ");

            System.out.println(result);
        }
    }


    @AfterThrowing(
            pointcut = "execution(* com.site.service.StudentService.*(..))",
            throwing = "error"
    )
    public void afterThrowingMethod(
            JoinPoint joinPoint,
            Throwable error
    ) {

        System.out.println(
                "[AOP AFTER THROWING] An exception occurred!"
        );

        System.out.println(
                "[AOP ERROR] " + error.getMessage()
        );
    }


    @After("execution(* com.site.service.StudentService.*(..))")
    public void afterMethod(JoinPoint joinPoint) {

        System.out.println(
                "[AOP AFTER] Student service method has finished."
        );
    }
}