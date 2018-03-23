//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * FirstLastName annotation
 */
@Documented
@Constraint(validatedBy = FirstLastNameValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstLastName {
    
    /**
     * Get the error message
     * @return the error message
     */
    String message() default "{First and last name cannot be the same}";
    
    /**
     * Get the array of groups this annotation is invoked in
     * @return the array of groups
     */
    Class<?>[] groups() default {};
    
    /**
     * The payloads
     * @return The annotation payloads
     */
    Class<? extends Payload>[] payload() default {};
}
