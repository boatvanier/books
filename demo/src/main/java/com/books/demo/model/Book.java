package com.books.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Double price;
    private Integer stock;
    private String image;

  /**
   * CREATE TABLE IF NOT EXISTS public.likes
   * (
   * id serial primary key,
   * user_id integer not null,
   * book_id integer not null,
   * constraint fk_user_id foreign key(user_id) references users(id),
   * constraint fk_book_id foreign key(book_id) references books(id),
   * unique(user_id, book_id)
   * )
   */
  @ManyToMany
  @JoinTable(
      name = "likes",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> likes;

    public Book(String title, String author, Double price, Integer stock, String image) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }
}