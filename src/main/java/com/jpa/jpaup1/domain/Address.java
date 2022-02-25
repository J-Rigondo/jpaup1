package com.jpa.jpaup1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor //reflection 사용하려면 기본 생성자 필요
@AllArgsConstructor
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
