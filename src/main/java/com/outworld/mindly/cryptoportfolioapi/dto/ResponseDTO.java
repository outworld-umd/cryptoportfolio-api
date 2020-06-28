package com.outworld.mindly.cryptoportfolioapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {

    @Builder.Default
    private String message = "Success!";

    private T body;
}
