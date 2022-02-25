package com.jpa.jpaup1.domain;

import com.jpa.jpaup1.exception.NotEnoughStockExcetpion;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Data
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    //DDD entity 안에 비지니스 로직을 넣는다

    /**
     * add stock
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;

        if(restStock < 0) {
            throw new  NotEnoughStockExcetpion("stock under zero");
        }

        this.stockQuantity = restStock;

    }

}
