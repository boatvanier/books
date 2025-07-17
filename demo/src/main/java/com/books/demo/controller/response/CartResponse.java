package com.books.demo.controller.response;

import com.books.demo.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    UserResponse user;
    BookResponse book;
    int quantity;

    public static CartResponse toResponse(Cart cart){
        return new CartResponse(
                UserResponse.toResponse(cart.getUser()),
                BookResponse.toResponse(cart.getBook()),
                cart.getQuantity()
        );
    }
}
