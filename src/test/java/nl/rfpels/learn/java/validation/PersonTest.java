//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.validation;

import javax.validation.*;

import nl.rfpels.learn.java.model.Person;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;


public class PersonTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testSetNameNull() throws ConstraintViolationException {
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage(containsString("not be blank"));

        Person subject = new Person();
        subject.setLastName("Zoover");
        subject.setAge(35);
        subject.setFirstName(null);
    }

    @Test
    public void testSetNameTooShort() {
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage(containsString("must be between"));

        Person subject = new Person();
        subject.setLastName("Zoover");
        subject.setAge(35);
        subject.setFirstName("Foo");
    }

    @Test
    public void testSetNameTooLong() {
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage(containsString("must be between"));

        Person subject = new Person();
        subject.setLastName("Zoover");
        subject.setAge(35);
        subject.setFirstName("FooBaFooBaFooBaFooBaFooBaFooBaFooBaFooBaFooBaFooBaFooBa");
    }

    @Test
    public void testSameValueFirstLast() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Person subject = new Person();
        subject.setFirstName("Zoover");
        subject.setLastName("Zoover");
        subject.setAge(35);
        Set<ConstraintViolation<Person>> cvs = validator.validate(subject);

        assertThat("person does not valudate", cvs.size() > 0, is(true));
        assertThat("cv about first and last name same",
                cvs.stream()
                        .filter(cv -> cv.getMessage().contains("First and last name cannot be the same"))
                        .count(),
                is(1L));
    }
}
