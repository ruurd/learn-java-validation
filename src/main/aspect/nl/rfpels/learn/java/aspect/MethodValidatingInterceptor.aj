//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.aspect;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class MethodValidatingInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(MethodValidatingInterceptor.class);
    private static final ExecutableValidator validator = Validation.buildDefaultValidatorFactory().getValidator().forExecutables();

    //Match any public methods in a class annotated with @AutoValidating
    @Around("execution(public * nl.rfpels.learn.java.model..*.*(..)) && " +
            "!execution(public * nl.rfpels.learn.java.*.*Test.*(..)) && " +
            "!execution(public * nl.rfpels.learn.java.*.*Validator.*(..)) && " +
            "!execution(public * nl.rfpels.learn.java.model..*.get*(..)) && " +
            "!execution(public * nl.rfpels.learn.java.model..*.set*(..))")
    public Object validateMethodInvocation(ProceedingJoinPoint pjp) throws Throwable {
        Object result;

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        LOG.info(String.format("validating %s", signature.getMethod().getName()));

        Set<ConstraintViolation<Object>> pvs = validator.validateParameters(
                pjp.getTarget(), signature.getMethod(), pjp.getArgs()
        );
        if (!pvs.isEmpty()) {
            LOG.error(String.format("validation failed: %s", pvs.toString()));
            throw new ConstraintViolationException(pvs);
        }

        result = pjp.proceed(); //Execute the method

        Set<ConstraintViolation<Object>> returnValueViolations = validator.validateReturnValue(
                pjp.getTarget(), signature.getMethod(), result
        );
        if (!returnValueViolations.isEmpty()) {
            throw new ConstraintViolationException(returnValueViolations);
        }

        return result;
    }
}