//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Intercept setters
 */
@Aspect
public class SetterInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(SetterInterceptor.class);
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Pointcut("within(nl.rfpels.learn.java.model..*)")
    public void inModel() {}

    @Pointcut("within(nl.rfpels.learn.java.validation..*)")
    public void inValidator() {}

    @Pointcut("set(private * *)")
    private void anySetter() {}

    @After("inModel() && !inValidator() && anySetter()")
    public void logSetter(JoinPoint joinPoint) {
        LOG.info(String.format("validating %s", joinPoint.getTarget()));
        Set<ConstraintViolation<Object>> cvs = validator.validateProperty(joinPoint.getTarget(), joinPoint.getStaticPart().getSignature().getName());
        if (cvs.isEmpty() == false) {
            throw new ConstraintViolationException(cvs);
        }
    }
}
