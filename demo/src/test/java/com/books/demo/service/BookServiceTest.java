package com.books.demo.service;


import com.books.demo.model.Book;
import com.books.demo.repository.BookJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    //@Autowired
    @InjectMocks
    private BookService bookService;

    //@MockitoBean
    @Mock
    private BookJpaRepository bookJpaRepository;

    @Test
    public void bookService_FindBookById_thenReturnBook() {
        Book book = new Book();

        when(bookJpaRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBook(1L);
        assertTrue(result.isPresent());
        result.ifPresent(value -> assertEquals(book, value));
    }
}

