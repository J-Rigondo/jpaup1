package com.jpa.jpaup1.dto.member;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestResponse {

    private List<Integer> ids = new ArrayList<>();

    private Integer count;
}
