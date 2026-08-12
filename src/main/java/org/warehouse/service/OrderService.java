package org.warehouse.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warehouse.bst.OrderBST;
import org.warehouse.dto.OrderItemRequest;
import org.warehouse.dto.OrderRequest;
import org.warehouse.entity.Customer;
import org.warehouse.entity.Order;
import org.warehouse.entity.OrderItem;
import org.warehouse.entity.Product;
import org.warehouse.repository.CustomerRepository;
import org.warehouse.repository.OrderRepository;
import org.warehouse.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    private final OrderBST priorityTree = new OrderBST();

    @Autowired
    public OrderService(OrderRepository orderRepository,
                         CustomerRepository customerRepository,
                         ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void loadTreeFromDatabase() {
        for (Order order : orderRepository.findAll()) {
            priorityTree.insert(order);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(OrderRequest request) {
        if (request.getCustomerId() == null) {
            throw new IllegalArgumentException("customerId is required");
        }
        if (request.getPriorityLevel() < 1 || request.getPriorityLevel() > 10) {
            throw new IllegalArgumentException("priorityLevel must be between 1 and 10");
        }
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("An order needs at least one item");
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException(
                        "No customer found with id " + request.getCustomerId()));

        LocalDate orderDate = request.getOrderDate() != null ? request.getOrderDate() : LocalDate.now();

        Order order = new Order(orderDate, request.getPriorityLevel(), customer);

        for (OrderItemRequest itemRequest : request.getItems()) {
            if (itemRequest.getQuantity() <= 0) {
                throw new IllegalArgumentException("Item quantity must be greater than 0");
            }

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new NoSuchElementException(
                            "No product found with id " + itemRequest.getProductId()));

            order.getItems().add(new OrderItem(itemRequest.getQuantity(), product, order));
        }

        Order saved = orderRepository.save(order);
        priorityTree.insert(saved);

        return saved;
    }

    public Order addOrderToPriorityTree(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("No order found with id " + orderId));

        priorityTree.insert(order);
        return order;
    }

    public List<Order> getInorderPriorities() {
        return priorityTree.inorder();
    }

    public Order getHighestPriorityOrder() {
        return priorityTree.findHighest();
    }

    public Order getLowestPriorityOrder() {
        return priorityTree.findLowest();
    }
}
