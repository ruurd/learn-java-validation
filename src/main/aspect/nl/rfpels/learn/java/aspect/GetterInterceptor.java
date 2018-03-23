//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Intercept setters
 */
@Aspect
public class GetterInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(GetterInterceptor.class);

    @Pointcut("within(nl.rfpels.learn.java.model..*)")
    public void inModel() {}

    @Pointcut("within(nl.rfpels.learn.java.validation..*)")
    public void inValidator() {}

    @Pointcut("execution(public * *..*.get*())")
    private void aGetter() {}

    @AfterReturning(pointcut = "inModel() && !inValidator() && aGetter()", returning = ("retval"))
    public void annologGetter(JoinPoint joinPoint, Object retval) {
        LOG.info(String.format("%s = %s", joinPoint.getSignature().toLongString(), retval));
    }
}
