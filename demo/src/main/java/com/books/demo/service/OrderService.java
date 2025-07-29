package com.books.demo.service;

import com.books.demo.model.*;
import com.books.demo.repository.BookJpaRepository;
import com.books.demo.repository.OrderJPARepository;
import com.books.demo.repository.UserJPARepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderJPARepository orderJPARepository;
    private final BookJpaRepository bookJpaRepository;
    private final UserJPARepository userJPARepository;
    private final CartService cartService;

    private final String USER_NOT_FOUND_ERROR = "user is not found";
    private final String BOOK_NOT_FOUND_ERROR = "book is not found";

    public OrderService(OrderJPARepository orderJPARepository, BookJpaRepository bookJpaRepository, UserJPARepository userJPARepository, CartService cartService) {
        this.orderJPARepository = orderJPARepository;
        this.bookJpaRepository = bookJpaRepository;
        this.userJPARepository = userJPARepository;
        this.cartService = cartService;
    }

    public List<Order> getOrders() {
        return orderJPARepository.findAll();
    }

    public Optional<Order> getOrder (Long orderId) {
        return orderJPARepository.findById(orderId);}

    @Transactional
    public Long placeOrder(Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ERROR));

        List<Cart> cartItems = cartService.findMyCart(userId);

        // create an order
        Order order = new Order();

        // set user
        order.setUser(user);

        double total = 0;
        // for all items in the cart
        for (Cart item : cartItems) {
            Book book = item.getBook();

            // check stock
            if(book.getStock() < item.getQuantity()) {
                throw new RuntimeException(BOOK_NOT_FOUND_ERROR);
            }

            // remove from the stock
            book.setStock(book.getStock() - item.getQuantity());
            bookJpaRepository.save(book);

            // create order item
            order.addItem(new OrderItem(book, item.getQuantity(), book.getPrice()));
            // calculate the total amount
            total += item.getQuantity() * book.getPrice();
        }

        // set the order total amount
        order.setTotalAmount(total);
        // set the order status
        order.setStatus(OrderStatus.PLACED);
        orderJPARepository.save(order);

        cartService.clearCart(userId);

        return order.getId();
    }
}
