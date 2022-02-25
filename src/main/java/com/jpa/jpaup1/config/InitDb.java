package com.jpa.jpaup1.config;

import com.jpa.jpaup1.domain.*;
import com.jpa.jpaup1.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * total 2 order
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {

            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("seoul", "1", "1111"));

            em.persist(member);

            Book book1 = new Book();
            book1.setName("JPA1 book");
            book1.setPrice(10000);
            book1.setStockQuantity(100);

            Book book2 = new Book();
            book2.setName("JPA2 book");
            book2.setPrice(20000);
            book2.setStockQuantity(100);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            em.persist(order);

        }

        public void dbInit2() {

            Member member = new Member();
            member.setName("userB");
            member.setAddress(new Address("incheon", "2", "2222"));

            em.persist(member);

            Book book1 = new Book();
            book1.setName("JPA1 book");
            book1.setPrice(20000);
            book1.setStockQuantity(200);

            Book book2 = new Book();
            book2.setName("JPA2 book");
            book2.setPrice(40000);
            book2.setStockQuantity(300);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            em.persist(order);

        }

    }
}

