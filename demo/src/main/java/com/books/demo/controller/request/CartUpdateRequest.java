package com.books.demo.controller.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CartUpdateRequest {

    @PositiveOrZero
    private int quantity;
}
