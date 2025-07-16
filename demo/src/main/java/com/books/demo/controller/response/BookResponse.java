package com.books.demo.controller.response;

import com.books.demo.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class BookResponse {
    private String bookName;
    private String authorName;
    private double price;
    private int stock;

    public static BookResponse toResponse(Book book) {
        return new BookResponse(book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getStock());
    }
}
