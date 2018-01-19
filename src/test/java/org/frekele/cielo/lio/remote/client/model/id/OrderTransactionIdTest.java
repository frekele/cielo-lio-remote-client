package org.frekele.cielo.lio.remote.client.model.id;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderTransactionIdTest {

    @Test
    public void testNewInstance() throws Exception {
        String id = "a32aa8-c3dd-49fa-58cf29c-7aaaf9689a";

        OrderTransactionId orderTransactionId = new OrderTransactionId();
        orderTransactionId.setId(id);

        assertNotNull(orderTransactionId);
        assertEquals(id, orderTransactionId.getId());
    }
}
