package com.books.demo.repository;

import com.books.demo.model.Book;
import com.books.demo.model.Cart;
import com.books.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartJPARepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);

    Optional<Cart> findByUserAndBook(User user, Book book);
}
