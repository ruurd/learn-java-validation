//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import nl.rfpels.learn.java.model.Person;
import org.apache.commons.lang3.StringUtils;

/**
 * FirstLastName validator
 */
public class FirstLastNameValidator implements ConstraintValidator<FirstLastName, Person> {
    
    /**
     * Determine if person is valid
     * @param value the person to check
     * @param context the validation context
     * @return true if valid
     */
    @Override
    public boolean isValid(Person value, ConstraintValidatorContext context) {
        return !(StringUtils.equalsIgnoreCase(value.getFirstName(), value.getLastName()));
    }
}
