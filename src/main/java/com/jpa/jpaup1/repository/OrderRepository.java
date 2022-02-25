package com.jpa.jpaup1.repository;

import com.jpa.jpaup1.domain.Order;
import com.jpa.jpaup1.dto.order.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

//    public List<Order> findAll(OrderSearch orderSearch) {
//
//        //status, name이 없는 경우는 where 절이 안생겨야되는데 이게 문제점임
//        List<Order> resultList = em.createQuery("select o from Order o left join o.member m" +
//                        " where o.status = :status" +
//                        " and m.name like :name", Order.class)
//                .setParameter("status", orderSearch.getOrderStatus())
//                .setParameter("name", orderSearch.getMemberName())
//
//                .setMaxResults(1000)
//                .getResultList();
//
//        return resultList;
//
//    }

    public List<Order> findAll() {

        //status, name이 없는 경우는 where 절이 안생겨야되는데 이게 문제점임
        List<Order> resultList = em.createQuery("select o from Order o left join o.member m")
                .getResultList();

        return resultList;

    }


    public List<Order> findAllWithMemberDelivery() {

        return em.createQuery("select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d", Order.class).getResultList();
    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit) {

        return em.createQuery("select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

    }

    public List<Order> testOneToMany() {

        return em.createQuery("select o from Order o" +
                " join o.member m"
                , Order.class).getResultList();
    }

    public List<Order> findAllWithItem() { //fetch조인과 페이징은 같이 쓸 수 없다 인메모리에 다들고와서 페이징하기때문에 뻑남

        return em.createQuery("select distinct o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d" +
                " join fetch o.orderItems oi" +
                " join fetch oi.item i", Order.class).getResultList();
    }




//    public List<OrderDto> findOrderDtos() {
//        List<OrderDto> resultList = em.createQuery("select new com.jpa.jpaup1.dto.order.OrderDto(o.id, m.name, o.orderDate, o.status, d.address) from Order o" +
//                " join o.member m" +
//                " join o.delivery d", OrderDto.class).getResultList();
//
//    }
}
