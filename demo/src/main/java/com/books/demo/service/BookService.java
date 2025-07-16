package com.books.demo.service;

import com.books.demo.model.Book;
import com.books.demo.repository.BookJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookJpaRepository repository;

    public BookService(BookJpaRepository repository) {
        this.repository = repository;
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBook(Long bookId){
        return repository.findById(bookId);
    }

    public List<Book> getBooksByTitle(String title){
        return repository.findByTitleContaining(title);
    }

}

