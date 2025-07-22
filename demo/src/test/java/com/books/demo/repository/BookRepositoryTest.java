package com.books.demo.repository;

import com.books.demo.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
public class BookRepositoryTest {
    @Autowired
    BookJpaRepository repository;

    @Test
    public void bookRepository_FindByTitleContaining_ReturnsBook(){
        Book book = new Book("testTitle", "testAuthor", 12.56, 100);
        repository.save(book);

        List<Book> books = repository.findByTitleContaining("testTitle");

        System.out.print(books);
        assertEquals(1, books.size());
        assertEquals("testTitle", books.getFirst().getTitle());

    }
}
