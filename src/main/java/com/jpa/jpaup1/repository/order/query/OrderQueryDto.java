package com.jpa.jpaup1.repository.order.query;

import com.jpa.jpaup1.domain.Address;
import com.jpa.jpaup1.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderQueryDto {

    private Long orderId;

    private String name;

    private LocalDateTime orderDate;

    private OrderStatus orderStatus;

    private Address address;

    private List<OrderItemQueryDto> orderItems;
}
