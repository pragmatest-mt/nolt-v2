package com.pragmatest.nolt.customers.utils;

import org.awaitility.core.ConditionTimeoutException;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.fail;

@Component
public class AsyncAssertHelper {

    public void asyncAssert(Callable<Boolean> callable, long timeoutInSeconds) {
        try {
            await().atMost(timeoutInSeconds, SECONDS).until(callable);
        } catch(ConditionTimeoutException e) {
            fail("Order was not accepted within the given consistency period of " + timeoutInSeconds + " seconds.");
        }
    }

    public void asyncAssert(Callable<Boolean> callable) {
        asyncAssert(callable, 5);
    }


}
