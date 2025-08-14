package com.project.ecommerce.controller;


import com.project.ecommerce.dto.OrderResponse;
import com.project.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestHeader("X-User-Id") Long userId) {

        // process the checkout
        orderService.checkout(userId);

        return ResponseEntity.ok("Checkout successful!");
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrderHistory(@RequestHeader("X-User-Id") Long userId) {

        // get order history for the user
        List<OrderResponse> orderHistory = orderService.getOrderHistory(userId);

        return ResponseEntity.ok(orderHistory);
    }
}
