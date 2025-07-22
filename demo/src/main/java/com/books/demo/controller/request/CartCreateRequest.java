package com.books.demo.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartCreateRequest {
    @NotNull
    private Long bookId;
}
