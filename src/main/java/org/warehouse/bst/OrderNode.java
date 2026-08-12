package org.warehouse.bst;

import org.warehouse.entity.Order;

/**
 * A single node in the {@link OrderBST}. Package-private on purpose -
 * nothing outside the bst package should be touching node internals.
 */
class OrderNode {

    Order data;
    OrderNode left;
    OrderNode right;

    OrderNode(Order data) {
        this.data = data;
    }
}
