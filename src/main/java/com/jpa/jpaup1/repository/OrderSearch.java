package com.jpa.jpaup1.repository;

import com.jpa.jpaup1.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName;

    private OrderStatus orderStatus;
}
