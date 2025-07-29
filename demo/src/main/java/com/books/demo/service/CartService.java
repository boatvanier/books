package com.books.demo.service;

import com.books.demo.model.Book;
import com.books.demo.model.Cart;
import com.books.demo.model.User;
import com.books.demo.repository.BookJpaRepository;
import com.books.demo.repository.CartJPARepository;
import com.books.demo.repository.UserJPARepository;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartJPARepository cartJPARepository;
    private final UserJPARepository userJPARepository;
    private final BookJpaRepository bookJpaRepository;

    public CartService(CartJPARepository cartJPARepository, UserJPARepository userJPARepository, BookJpaRepository bookJpaRepository) {
        this.cartJPARepository = cartJPARepository;
        this.userJPARepository = userJPARepository;
        this.bookJpaRepository = bookJpaRepository;
    }

    public List<Cart> findMyCart(Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user is not found"));

        return cartJPARepository.findByUser(user);
    }

    public void createCart(Long userId, Long bookId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user is not found"));
        Book book = bookJpaRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("book is not found"));

        Cart cart;
        Optional<Cart> cartOptional = cartJPARepository.findByUserAndBook(user, book);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
            cart.setQuantity(cart.getQuantity()+1);
        } else {
            cart = new Cart(user, book);
        }
        cartJPARepository.save(cart);
    }

    public void updateCart(Long userId, Long bookId, int quantity) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user is not found"));
        Book book = bookJpaRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("book is not found"));
        Cart cart = cartJPARepository.findByUserAndBook(user, book)
                .orElseThrow(()->new RuntimeException("cart is not found"));

        cart.setQuantity(quantity);
        cartJPARepository.save(cart);
    }


    @Transactional
    public void delete(Long userId, Long bookId) {
        cartJPARepository.deleteCartItem(userId, bookId);
    }

    public void clearCart(Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user is not found"));
        cartJPARepository.deleteByUser(user);
    }
}
