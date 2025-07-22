package com.books.demo.controller;

import com.books.demo.controller.request.CartCreateRequest;
import com.books.demo.controller.request.CartUpdateRequest;
import com.books.demo.controller.response.CartResponse;
import com.books.demo.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> getCart(@PathVariable Long userId){
        return ResponseEntity.ok(cartService
                .findMyCart(userId)
                .stream().map(CartResponse::toResponse)
                .toList());
    }

    @PostMapping("/{userId}/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCartItem(@PathVariable Long userId, @RequestBody @Valid CartCreateRequest cartRequest){
        cartService.createCart(userId, cartRequest.getBookId());
    }

    @PutMapping("/{userId}/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCartItem(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody @Valid CartUpdateRequest cartRequest){
        cartService.updateCart(userId, bookId, cartRequest.getQuantity());
    }

}
