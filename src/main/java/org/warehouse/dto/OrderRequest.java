package org.warehouse.dto;

import java.time.LocalDate;
import java.util.List;


public class OrderRequest {

    private Long customerId;
    private int priorityLevel;
    private LocalDate orderDate;
    private List<OrderItemRequest> items;

    public OrderRequest() {}

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public int getPriorityLevel() { return priorityLevel; }
    public void setPriorityLevel(int priorityLevel) { this.priorityLevel = priorityLevel; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
