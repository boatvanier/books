package com.books.demo.repository;

import com.books.demo.model.Cart;
import com.books.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartJPARepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
}
