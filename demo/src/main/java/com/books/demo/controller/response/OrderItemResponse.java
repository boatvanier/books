package com.books.demo.controller.response;

import com.books.demo.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    Long id;
    BookResponse book;
    int quantity;
    double price;

    public static OrderItemResponse toResponse(OrderItem item) {
        return new OrderItemResponse(
                item.getId(),
                BookResponse.toResponse(item.getBook()),
                item.getQuantity(),
                item.getPriceAtPurchase());
    }
}
