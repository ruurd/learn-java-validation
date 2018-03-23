//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.validation;

import javax.validation.ConstraintViolationException;
import nl.rfpels.learn.java.model.Person;
import nl.rfpels.learn.java.model.Persons;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PersonsTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAddInvalidPerson() throws ConstraintViolationException {
        Persons subject = new Persons();
        Person person = new Person();

        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage(containsString("not be blank"));
        expectedException.expectMessage(containsString("not be the same"));
        subject.add(person);
    }

    @Test
    public void testAddValidPerson() {
        Persons subject = new Persons();
        Person person = new Person();
        person.setFirstName("FirstName");
        person.setLastName("LastName");
        person.setAge(35);
        subject.add(person);
        assertThat("list member count", subject.getPersons().size(), is(1));
    }

    @Test
    public void testAddPersonSameFirstLast() throws ConstraintViolationException {
        Persons subject = new Persons();
        Person person = new Person();
        person.setFirstName("FirstName");
        person.setLastName("FirstName");

        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage(containsString("cannot be the same"));
        subject.add(person);
    }

    @Test
    public void testAddPersonFirstNull() throws ConstraintViolationException {
        Persons subject = new Persons();
        Person person = new Person();
        person.setFirstName("FirstName");

        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage(containsString("last"));
        subject.add(person);
    }

    @Test
    public void testAddPersonNullLast() throws ConstraintViolationException {
        Persons subject = new Persons();
        Person person = new Person();
        person.setLastName("LastName");

        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage(containsString("first"));
        subject.add(person);
    }
}
