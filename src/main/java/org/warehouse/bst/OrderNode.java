package org.warehouse.bst;

import org.warehouse.entity.Order;

class OrderNode {

    Order data;
    OrderNode left;
    OrderNode right;

    OrderNode(Order data) {
        this.data = data;
    }
};
