package com.books.demo.service;

import com.books.demo.model.Book;
import com.books.demo.repository.BookJpaRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookJpaRepository repository;

    public BookService(BookJpaRepository repository) {
        this.repository = repository;
    }

    public List<Book> getBooks(String title) {
        if (title == null || title.isBlank())
         return repository.findAll();
        return repository.findByTitleContaining(title);
    }

    public Optional<Book> getBook(Long bookId){
        return repository.findById(bookId);
    }

    public void createBook(@NonNull String title, String author, double price, int stock, String image){
        repository.save(new Book(title, author, price, stock, image));
    }
    public void updateBook(@NonNull Long bookId, @NonNull String title, @NonNull String author, double price, int stock) {
        Book book = repository.findById(bookId).orElseThrow(()->new IllegalArgumentException("book is not found"));
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        book.setStock(stock);
        repository.save(book);
    }

    public void delete(Long bookId) {
        repository.deleteById(bookId);
    }
}

