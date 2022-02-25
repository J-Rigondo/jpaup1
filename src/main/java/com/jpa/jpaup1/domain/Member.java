package com.jpa.jpaup1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //내장 타입
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member") //Order 테이블에 있는 member에 의해 매핑 된거다 읽기 전용
    private List<Order> orders = new ArrayList<>(); //컬렉션은 필드에서 초기화 하자 hibernate가 한번 감싼다
    //따라서 hibernate가 감싼거를 건들지 못하도록 setter를 못쓰게하는거다
}
