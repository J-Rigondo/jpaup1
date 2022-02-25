package com.jpa.jpaup1.dto.member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponse {
    private String name;
}
