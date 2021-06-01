package com.example.restservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
@Aspect
public class LogParameterAspect {
    private Logger logger = Logger.getLogger(LogParameterAspect.class.getName());

    @Pointcut("@annotation(com.example.restservice.aop.LogParameters)")
    public void logMethod() {
    }

    @Before("logMethod()")
    public void beforeLogMethod(JoinPoint jp) {
        Object[] parameterValue = jp.getArgs();

        MethodSignature signature = (MethodSignature)jp.getSignature();
        String[] parameterNames = signature.getParameterNames();

        final int[] i = {0};
        Arrays.stream(parameterNames).forEach(name -> {
            logger.info("Parameter " + name + ": " + parameterValue[i[0]]);
            i[0]++;
        });
    }

    @AfterReturning(value = "logMethod()", returning = "result")
    public void afterLogMethod(JoinPoint jp, Object result){
        logger.info("Return parameter: " + result.toString());
    }
}
