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
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
public class BookRepositoryTest {
    @Autowired
    BookJpaRepository repository;

    @Test
    public void bookRepository_FindByTitleContaining_ReturnsBook(){
        Book book1 = new Book("testTitle1", "testAuthor1", 12.56, 100, "");
        Book book2 = new Book("testTitle2", "testAuthor2", 12.56, 100, "");
        repository.save(book1);
        repository.save(book2);

        List<Book> books = repository.findByTitleContaining("testTitle");

        assertEquals(2, books.size());
        assertTrue(books.contains(book1));
    }
}
