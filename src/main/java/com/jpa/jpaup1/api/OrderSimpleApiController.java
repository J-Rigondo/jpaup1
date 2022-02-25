package com.jpa.jpaup1.api;

import com.jpa.jpaup1.domain.Address;
import com.jpa.jpaup1.domain.Order;
import com.jpa.jpaup1.domain.OrderStatus;
import com.jpa.jpaup1.dto.order.OrderDto;
import com.jpa.jpaup1.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * XtoOne
 * order -> member
 * order -> delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/simple-order")
    public List<Order> getOrder() {

        List<Order> all = orderRepository.testOneToMany();

        return all;
    }

//    @GetMapping("/v2/simple-order")
//    public ResponseEntity<List<OrderDto>> orderV2() {
//
//        List<Order> all = orderRepository.findAll();
//
//        List<OrderDto> orderDtos = all.stream()
//                .map(o -> new OrderDto(o)).collect(Collectors.toList());
//
//        return ResponseEntity.ok(orderDtos) ;
//
//
//    }
//
    @GetMapping("/v3/orders")
    public List<Order> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();

//        List<OrderDto> orderDtos = orders.stream()
//                .map(o -> new OrderDto(o)).collect(Collectors.toList());

        return orders;
    }




}
