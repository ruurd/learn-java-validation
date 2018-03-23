//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.model;

import javax.validation.constraints.*;

import nl.rfpels.learn.java.validation.FirstLastName;
import org.hibernate.validator.constraints.Length;

/**
 * A person
 */
@FirstLastName
public class Person {

    @NotBlank
    @Length(min=5, max=50)
    private String firstName;

    @NotBlank
    @Length(min=5, max=50)
    private String lastName;

    @PositiveOrZero
    @Max(130)
    @NotNull
    private Integer age;

    /**
     * Constructor
     */
    public Person() {
    }

    /**
     * Get the firstName name
     * @return firstName name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get lastName name
     * @return lastName name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get age
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Set firstName name
     * @param firstName the firstName name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set lastName name
     * @param lastName the lastName name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set age
     * @param age the age of the person
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}
