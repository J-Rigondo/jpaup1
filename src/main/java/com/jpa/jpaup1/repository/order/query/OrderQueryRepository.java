package com.jpa.jpaup1.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;


    public List<OrderQueryDto> findOrderQueryDtos() {

        return em.createQuery("select new com.jpa.jpaup1.repository.order.query.OrderQueryDto(o.id,m.name,) from Order o" +
                " join o.member m" +
                " join o.delivery", OrderQueryDto.class).getResultList();
    }
}
