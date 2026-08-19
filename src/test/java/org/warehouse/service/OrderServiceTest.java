package org.warehouse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.dto.OrderItemRequest;
import org.warehouse.dto.OrderRequest;
import org.warehouse.entity.Customer;
import org.warehouse.entity.Order;
import org.warehouse.entity.Product;
import org.warehouse.repository.CustomerRepository;
import org.warehouse.repository.OrderRepository;
import org.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock private OrderRepository orderRepository;
    @Mock private CustomerRepository customerRepository;
    @Mock private ProductRepository productRepository;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        when(orderRepository.findAll()).thenReturn(List.of());
        orderService = new OrderService(orderRepository, customerRepository, productRepository);
        orderService.loadTreeFromDatabase();
    }

    @Test
    void createOrderSavesAndInsertsIntoPriorityTree() {
        Customer customer = new Customer("Jane Doe", "jane@example.com");
        Product product = new Product("Widget", 9.99, 100);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrderRequest request = new OrderRequest();
        request.setCustomerId(1L);
        request.setPriorityLevel(7);
        request.setItems(List.of(new OrderItemRequest(1L, 3)));

        Order created = orderService.createOrder(request);

        assertEquals(7, created.getPriorityLevel());
        assertEquals(1, created.getItems().size());
        verify(orderRepository).save(any(Order.class));

        assertEquals(1, orderService.getInorderPriorities().size());
        assertEquals(7, orderService.getHighestPriorityOrder().getPriorityLevel());
    }
}
