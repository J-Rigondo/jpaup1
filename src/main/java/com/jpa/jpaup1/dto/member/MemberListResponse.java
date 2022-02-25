package com.jpa.jpaup1.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberListResponse {

    private Integer count;
    private List<MemberDto> list = new ArrayList<>();

    @Builder
    public static class MemberDto {

        private String name;
    }
}
