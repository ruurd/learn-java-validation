//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java;

import javax.validation.ConstraintViolationException;

import nl.rfpels.learn.java.model.Book;
import nl.rfpels.learn.java.model.Person;
import nl.rfpels.learn.java.model.Persons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main application class
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    /**
     * Application entry point
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Persons persons = new Persons();

        Person p = new Person();
        p.setFirstName("Ruurd");
        p.setLastName("Pelsje");
        p.setAge(59);
        persons.add(p);

        try {
            p = new Person();
            p.setFirstName("Pels");
        } catch (ConstraintViolationException cvx) {
            LOG.warn("foo!", cvx);
        }

        try {
            p = new Person();
            p.setFirstName("Pelsje");
            p.setLastName("Pelsje");
            p.setAge(59);
            persons.add(p);
        } catch (ConstraintViolationException cvx) {
            LOG.warn("foo!", cvx);
        }

        try {
            persons.add(null);
        } catch (ConstraintViolationException cvx) {
            LOG.warn("bar!", cvx);
        }

        try {
            Book b = new Book();
            b.setTitle("The Book");
            b.setIsbn("978-1-934356-84-5");
        } catch (ConstraintViolationException cvx) {
            LOG.warn("qux!", cvx);
        }

    }
}
