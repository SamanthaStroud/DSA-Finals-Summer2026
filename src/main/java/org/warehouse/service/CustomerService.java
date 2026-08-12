package org.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warehouse.entity.Customer;
import org.warehouse.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            throw new IllegalArgumentException("Customer email is required");
        }

        return customerRepository.save(customer);
    }
}
