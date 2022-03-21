package com.jpa.jpaup1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String name; //실무에서는 검증로직이 있어도 멀티 쓰레드 상황을 고려해서 회원명 컬럼에 유니크제약필요

    @Embedded //내장 타입
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member") //Order 테이블에 있는 member에 의해 매핑 된거다 읽기 전용
    private List<Order> orders = new ArrayList<>(); //컬렉션은 필드에서 초기화 하자 hibernate가 한번 감싼다
    //따라서 hibernate가 감싼거를 건들지 못하도록 setter를 못쓰게하는거다
}
