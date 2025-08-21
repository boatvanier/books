package com.books.demo.controller;

import com.books.demo.controller.request.CartCreateRequest;
import com.books.demo.controller.request.CartUpdateRequest;
import com.books.demo.controller.response.CartResponse;
import com.books.demo.service.CartService;
import com.books.demo.service.CurrentUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cart", description = "Cart management APIs")
@RestController
@RequestMapping("/api/myCart")
public class CartController {
    private final CartService cartService;
    private final CurrentUserService currentUserService;

    public CartController(CartService cartService, CurrentUserService currentUserService) {
        this.cartService = cartService;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> getCart(){
    return ResponseEntity.ok(
        cartService.findMyCart(currentUserService.getCurrentUser().getId()).stream()
            .map(CartResponse::toResponse)
            .toList());
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCartItem(@RequestBody @Valid CartCreateRequest cartRequest){
        cartService.createCart(currentUserService.getCurrentUser().getId(), cartRequest.getBookId());
    }

    @PutMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCartItem(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody @Valid CartUpdateRequest cartRequest){
        cartService.updateCart(currentUserService.getCurrentUser().getId(), bookId, cartRequest.getQuantity());
    }

    @DeleteMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCartItem(@PathVariable Long bookId){
        cartService.delete(currentUserService.getCurrentUser().getId(), bookId);
    }
}
