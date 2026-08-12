package org.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warehouse.entity.Product;
import org.warehouse.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }

        return productRepository.save(product);
    }

    public List<Product> getSorted(String by) {
        List<Product> products = getAllProducts();

        if (by == null) {
            throw new IllegalArgumentException("Missing required query param 'by'");
        }

        return switch (by.toLowerCase()) {
            case "price" -> sortByPrice(products);
            case "stock" -> sortByStock(products);
            default -> throw new IllegalArgumentException(
                    "Unsupported sort field '" + by + "'. Use 'price' or 'stock'.");
        };
    }

    public List<Product> sortByPrice(List<Product> products) {
        List<Product> sorted = new ArrayList<>(products);

        for (int i = 1; i < sorted.size(); i++) {
            Product key = sorted.get(i);
            int j = i - 1;

            while (j >= 0 && sorted.get(j).getPrice() > key.getPrice()) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }

            sorted.set(j + 1, key);
        }

        return sorted;
    }

    public List<Product> sortByStock(List<Product> products) {
        List<Product> sorted = new ArrayList<>(products);

        for (int i = 1; i < sorted.size(); i++) {
            Product key = sorted.get(i);
            int j = i - 1;

            while (j >= 0 && sorted.get(j).getStock() > key.getStock()) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }

            sorted.set(j + 1, key);
        }

        return sorted;
    }
}
