package com.books.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_items")
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="book_id", nullable = false)
    private Book book;

    private int quantity;
    private double priceAtPurchase;

    public OrderItem(Book book, int quantity, double priceAtPurchase) {
        this.book = book;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }
}
