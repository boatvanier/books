package com.books.demo.service;

import com.books.demo.model.Order;
import com.books.demo.repository.OrderJPARepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderJPARepository orderJPARepository;

    public OrderService(OrderJPARepository orderJPARepository) {
        this.orderJPARepository = orderJPARepository;
    }

    public List<Order> getOrders() {
        return orderJPARepository.findAll();
    }

    public Optional<Order> getOrder (Long orderId) {
        return orderJPARepository.findById(orderId);}
}
