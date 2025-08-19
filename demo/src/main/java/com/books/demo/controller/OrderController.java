package com.books.demo.controller;

import com.books.demo.controller.request.OrderRequest;
import com.books.demo.controller.response.OrderResponse;
import com.books.demo.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Orders", description = "Order management APIs")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders()
                .stream().map(OrderResponse::toResponse)
                .toList());
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId)
                .map(OrderResponse::toResponse)
                .orElse(null));
    }

    @PostMapping
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest order){
        return ResponseEntity.ok(orderService.placeOrder(order.getUserId()));
    }

}
