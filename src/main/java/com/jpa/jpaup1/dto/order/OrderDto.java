package com.jpa.jpaup1.dto.order;

import com.jpa.jpaup1.domain.Address;
import com.jpa.jpaup1.domain.Order;
import com.jpa.jpaup1.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
