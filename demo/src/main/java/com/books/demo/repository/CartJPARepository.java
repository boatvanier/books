package com.books.demo.repository;

import com.books.demo.model.Book;
import com.books.demo.model.Cart;
import com.books.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartJPARepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);

    Optional<Cart> findByUserAndBook(User user, Book book);

    @Modifying
    @Query(value = "delete from cart_items c where c.user_id = :userId and c.book_id =:bookId", nativeQuery = true)
    void deleteCartItem(@Param("userId") Long userId, @Param("bookId") Long bookId);

    void deleteByUser(User user);
}
