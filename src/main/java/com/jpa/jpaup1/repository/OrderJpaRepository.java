package com.jpa.jpaup1.repository;


import com.jpa.jpaup1.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = "orderItems", type = EntityGraph.EntityGraphType.LOAD)
    List<Order> findByMember_Name(String name);

    @EntityGraph(attributePaths = {"orderItems", "member"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Order> findByOrderDateLessThanEqual(LocalDateTime date);
}
