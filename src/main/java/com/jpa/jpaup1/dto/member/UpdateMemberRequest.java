package com.jpa.jpaup1.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateMemberRequest {

    @ApiModelProperty(name = "이름", example = "jun", required = true)
    private String name;
}
