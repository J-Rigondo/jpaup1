package com.jpa.jpaup1.domain.item;

import com.jpa.jpaup1.domain.Item;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Data
public class Book extends Item {

    private String author;
    private String isbn;
}
