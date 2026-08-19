package org.warehouse.bst;

import org.junit.jupiter.api.Test;
import org.warehouse.entity.Customer;
import org.warehouse.entity.Order;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderBSTTest {

    private Order order(int priority) {
        Customer customer = new Customer("Test Customer", "test@example.com");
        return new Order(LocalDate.now(), priority, customer);
    }

    @Test
    void insertInorderHighestAndLowestWork() {
        OrderBST tree = new OrderBST();
        int[] priorities = {5, 2, 8, 1, 9, 3};

        for (int p : priorities) {
            tree.insert(order(p));
        }

        List<Order> result = tree.inorder();
        List<Integer> actual = result.stream().map(Order::getPriorityLevel).toList();

        assertEquals(List.of(1, 2, 3, 5, 8, 9), actual);
        assertEquals(9, tree.findHighest().getPriorityLevel());
        assertEquals(1, tree.findLowest().getPriorityLevel());
    }
}
