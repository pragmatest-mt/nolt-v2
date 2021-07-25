package com.pragmatest.nolt.restaurants.web.matchers;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.restaurants.service.models.Order;
import org.mockito.ArgumentMatcher;

public class OrderMatcher implements ArgumentMatcher<Order> {

    private Order left;

    public OrderMatcher(Order left) {
        this.left = left;
    }

    @Override
    public boolean matches(Order right) {
        if (left == right) return true;
        if (right == null) return false;

        return  left.getOrderId().equals(right.getOrderId()) &&
                left.getOrderState().equals(right.getOrderState()) &&
                //TODO check on how to to implement a future date for this
                //left.getEstimatedReadyTime().equals(right.getEstimatedReadyTime()) &&
                DeepEquals.deepEquals(left.getOrderItems(),
                        right.getOrderItems());
    }
}
