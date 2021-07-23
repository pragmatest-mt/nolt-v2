package com.pragmatest.nolt.customers.web.matchers;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.customers.service.models.Order;
import org.mockito.ArgumentMatcher;

import java.util.List;

public class OrderMatcher implements ArgumentMatcher<Order> {

    private Order left;

    public OrderMatcher(Order left) {
        this.left = left;
    }

    @Override
    public boolean matches(Order right) {
        if (left == right) return true;
        if (right == null) return false;

        return  left.getUserId().equals(right.getUserId()) &&
                DeepEquals.deepEquals(left.getOrderItems(),
                        right.getOrderItems());
    }

}
