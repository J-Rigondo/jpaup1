package com.jpa.jpaup1.service;

import com.jpa.jpaup1.domain.*;
import com.jpa.jpaup1.domain.item.Book;
import com.jpa.jpaup1.exception.NotEnoughStockExcetpion;
import com.jpa.jpaup1.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {

//        Member member = getMember("mem1", new Address("seoul", "liver", "2323"));
//
//        Book book = getBook(10);
//
//        int orderCount = 2;
//
//        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
//
//        Order getOrder = orderRepository.findOne(orderId);
//
//        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
//        assertEquals("상품 종류 수", 1, getOrder.getOrderItems().size());
//        assertEquals("total price", 10000 * orderCount, getOrder.getTotalPrice());
//        assertEquals("stock reduce", 10, book.getStockQuantity());


    }

    private Book getBook(String jpa, int price, int quantity) {
        Book book = new Book();
        book.setName(jpa);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;

    }


    private Member getMember(String mem1, Address address) {
        Member member = new Member();
        member.setName(mem1);
        member.setAddress(address);

        em.persist(member);
        return member;
    }



    @Test(expected = NotEnoughStockExcetpion.class)
    public void 주문취소() throws Exception {

        Member member = getMember("mem1", new Address("seoul", "liver", "2323"));
        Book book = getBook("jpa", 10000, 10);

        int orderCount = 11;

        orderService.order(member.getId(), book.getId(), orderCount);

        fail("expect exception");



    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {

    }

}