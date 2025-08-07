package com.books.demo.controller.response;

import com.books.demo.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class BookResponse {
    private Long bookId;
    private String bookName;
    private String authorName;
    private double price;
    private int stock;
    private List<UserResponse> likedUsers;
    private int likes;
    private String image;

    public static BookResponse toResponse(Book book) {
        return new BookResponse(book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getStock(),
                book.getLikes().stream().map(UserResponse::toResponse).toList(),
                book.getLikes().size(),
                book.getImage());
    }
}
