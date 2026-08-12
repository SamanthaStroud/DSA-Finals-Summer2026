package org.warehouse.bst;

import org.warehouse.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A hand-rolled Binary Search Tree that orders {@link Order} objects by
 * their priorityLevel (1-10).
 *
 * Ordering rule:
 *   - left subtree  = strictly lower priority
 *   - right subtree = higher OR EQUAL priority
 *
 * Duplicate priority levels are pushed into the right subtree instead of
 * being rejected or stored in a side list. Walking "right" past every
 * node whose priority is <= the new order's priority means duplicates
 * end up chained together in the order they were inserted, so an
 * inorder traversal still returns ascending priorities and orders with
 * the same priority come out in FIFO (insertion) order. See the
 * write-up in ANSWERS.md for the full reasoning.
 */
public class OrderBST {

    private OrderNode root;

    public void insert(Order order) {
        root = insertRecursive(root, order);
    }

    private OrderNode insertRecursive(OrderNode current, Order order) {
        if (current == null) {
            return new OrderNode(order);
        }

        if (order.getPriorityLevel() < current.data.getPriorityLevel()) {
            current.left = insertRecursive(current.left, order);
        } else {
            // Higher priority AND duplicates both go right.
            current.right = insertRecursive(current.right, order);
        }

        return current;
    }

    /** Inorder traversal: left, node, right -> ascending priority order. */
    public List<Order> inorder() {
        List<Order> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(OrderNode node, List<Order> result) {
        if (node == null) return;

        inorderRecursive(node.left, result);
        result.add(node.data);
        inorderRecursive(node.right, result);
    }

    /** Highest priority order = rightmost node in the tree. */
    public Order findHighest() {
        if (root == null) {
            throw new NoSuchElementException("Priority tree is empty");
        }

        OrderNode current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current.data;
    }

    /** Lowest priority order = leftmost node in the tree. */
    public Order findLowest() {
        if (root == null) {
            throw new NoSuchElementException("Priority tree is empty");
        }

        OrderNode current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
