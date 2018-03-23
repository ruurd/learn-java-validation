//------------------------------------------------------------------------------
// Copyright (c) 2018 Bureau Pels.  All Rights Reserved.
//------------------------------------------------------------------------------
package nl.rfpels.learn.java.model;


import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotBlank;

import static org.hibernate.validator.constraints.ISBN.Type.ISBN_13;

public class Book {

    @NotBlank
    private String title;

    @ISBN(type = ISBN_13)
    private String isbn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
