package com.books.demo.service;

import com.books.demo.model.Cart;
import com.books.demo.model.User;
import com.books.demo.repository.CartJPARepository;
import com.books.demo.repository.UserJPARepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartJPARepository cartJPARepository;
    private final UserJPARepository userJPARepository;

    public CartService(CartJPARepository cartJPARepository, UserJPARepository userJPARepository) {
        this.cartJPARepository = cartJPARepository;
        this.userJPARepository = userJPARepository;
    }

    public List<Cart> findMyCart(Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user is not found"));

        return cartJPARepository.findByUser(user);
    }
}
