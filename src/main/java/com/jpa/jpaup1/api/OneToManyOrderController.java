package com.jpa.jpaup1.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.jpaup1.domain.*;
import com.jpa.jpaup1.repository.OrderRepository;
import com.jpa.jpaup1.repository.order.query.OrderQueryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OneToManyOrderController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/v1/orders")
    public List<Order> orderV1() {

        List<Order> all = orderRepository.findAllWithItem();

        return all;
    }

    @GetMapping("/v3_1/orders")
    public List<OrderDto> orderV3_1(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit
            ) {

        List<Order> all = orderRepository.findAllWithMemberDelivery(offset, limit);

        List<OrderDto> orderDtos = all.stream().map(OrderDto::new).collect(Collectors.toList());

        return orderDtos;
    }

//    @GetMapping("/v4/orders")
//    public List<OrderQueryDto> ordersV4() {
//        orderQueryRepository.findOrderQueryDtos();
//    }

    @Getter
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getMember().getAddress();
            orderItems = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());

        }
    }

    @Getter
    static class OrderItemDto {

        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
