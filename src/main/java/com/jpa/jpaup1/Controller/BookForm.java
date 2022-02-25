package com.jpa.jpaup1.Controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private String author;

    private String isbn;
}
