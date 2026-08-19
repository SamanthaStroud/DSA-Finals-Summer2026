package org.warehouse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.warehouse.entity.Product;
import org.warehouse.repository.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(mock(ProductRepository.class));
    }

    @Test
    void sortByPriceAndStockOrderAscending() {
        List<Product> products = List.of(
                new Product("Widget", 19.99, 5),
                new Product("Gadget", 5.49, 20),
                new Product("Gizmo", 42.00, 1),
                new Product("Doohickey", 5.49, 8)
        );

        List<Product> byPrice = productService.sortByPrice(products);
        assertEquals(List.of(5.49, 5.49, 19.99, 42.00),
                byPrice.stream().map(Product::getPrice).toList());

        List<Product> byStock = productService.sortByStock(products);
        assertEquals(List.of(1, 5, 8, 20),
                byStock.stream().map(Product::getStock).toList());
    }
}
