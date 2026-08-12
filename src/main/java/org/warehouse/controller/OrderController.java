package org.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.warehouse.dto.OrderRequest;
import org.warehouse.entity.Order;
import org.warehouse.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order created = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/add-to-priority-tree")
    public ResponseEntity<Order> addToPriorityTree(@RequestParam Long orderId) {
        Order order = orderService.addOrderToPriorityTree(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/priority/inorder")
    public ResponseEntity<List<Order>> getInorder() {
        return ResponseEntity.ok(orderService.getInorderPriorities());
    }

    @GetMapping("/priority/highest")
    public ResponseEntity<Order> getHighest() {
        return ResponseEntity.ok(orderService.getHighestPriorityOrder());
    }

    @GetMapping("/priority/lowest")
    public ResponseEntity<Order> getLowest() {
        return ResponseEntity.ok(orderService.getLowestPriorityOrder());
    }
}
