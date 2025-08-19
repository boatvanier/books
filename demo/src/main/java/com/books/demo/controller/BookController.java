package com.books.demo.controller;

import com.books.demo.controller.request.BookRequest;
import com.books.demo.controller.request.CreateGroup;
import com.books.demo.controller.request.UpdateGroup;
import com.books.demo.controller.response.BookResponse;
import com.books.demo.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Books", description = "Book management APIs")
@Validated
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks(@RequestParam(name="title", required = false) String title){
        return ResponseEntity.ok(bookService.getBooks(title)
                .stream()
                .map(BookResponse::toResponse)
                .toList());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId){
        return ResponseEntity.ok(bookService.getBook(bookId)
                .map(BookResponse::toResponse)
                .orElse(null));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody @Validated(CreateGroup.class) BookRequest bookRequest){
        bookService.createBook(bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getPrice(),
                bookRequest.getStock(),
                bookRequest.getImage());
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long bookId, @RequestBody @Validated(UpdateGroup.class) BookRequest bookRequest){
        bookService.updateBook(bookId,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getPrice(),
                bookRequest.getStock());
    }
    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long bookId){
        bookService.delete(bookId);
    }
}
