//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggableAspect {
    @Pointcut("@annotation(nl.rfpels.learn.java.annotations.Loggable)")
    public void logIt() {}

    @Pointcut("execution(* *(..))")
    public void executingMethod() {}

    @Around("executingMethod() && logIt()")
    public Object aroundLoggableMethod(ProceedingJoinPoint pjp) throws Throwable {
        Logger log = LoggerFactory.getLogger(pjp.getSignature().getDeclaringType().getName());
        String methodname = pjp.getSignature().toLongString();
        log.info(String.format("Entering %s", methodname));

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable t) {
            log.info(String.format("Exception excuting %s: %s", methodname, t.getMessage()));
            throw t;
        }

        log.info(String.format("Exiting %s => %s", methodname, result));
        return result;
    }
}
