package com.jpa.jpaup1.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //fk이름이 member_id로 설정
    private Member member; //Lazy이기 때문에 proxy가 들어있음 Entity를 직접 반환하면 안되고 getMember를 통해 lazy를 활성화시켜줘야 값이들어감

    //orderItem이 order에서만 참조한다면 커스케이드 사용 커스케이드 있으면 persist할때 orderItem도 persist되서 save안해도 됨
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
     private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    /**
     * 생성
     * @param member
     * @param delivery
     * @param orderItems
     * @return
     */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();

        order.setMember(member);
        order.setDelivery(delivery);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);

        }

        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;

    }

    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMPLETED) {
            throw new IllegalStateException("already delivery");
        }

        this.setStatus(OrderStatus.CANCEL);

        for (OrderItem orderItem : this.orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice() {
       return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }

}
