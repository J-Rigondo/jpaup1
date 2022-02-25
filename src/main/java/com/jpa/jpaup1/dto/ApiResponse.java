package com.jpa.jpaup1.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private String status;

    private T data;

    private String message;

    public static ApiResponse success(Object data) {
        return new ApiResponse("200", data, "success");
    }
}
