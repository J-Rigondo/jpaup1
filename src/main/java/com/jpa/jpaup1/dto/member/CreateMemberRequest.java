package com.jpa.jpaup1.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateMemberRequest {

    @ApiModelProperty(name = "이름", required = true)
    private String name;
}
