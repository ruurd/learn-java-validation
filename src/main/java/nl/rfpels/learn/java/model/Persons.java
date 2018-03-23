//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.model;

import nl.rfpels.learn.java.annotations.Loggable;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * A list of persons.
 */
public class Persons {

    private List<Person> persons = new ArrayList<>();

    /**
     * Add a person to the list
     * @param person the person to add to the list
     */
    @Loggable
    public void add(@NotNull @Valid Person person) {
        persons.add(person);
    }

    /**
     * Get the list of persons
     * @return a list of persons possibly empty
     */
    public List<Person> getPersons() {
        return persons;
    }
}
