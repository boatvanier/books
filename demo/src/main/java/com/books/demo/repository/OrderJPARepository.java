package com.books.demo.repository;

import com.books.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Long> {}
