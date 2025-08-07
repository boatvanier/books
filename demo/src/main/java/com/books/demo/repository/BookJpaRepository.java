package com.books.demo.repository;

import com.books.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
}
