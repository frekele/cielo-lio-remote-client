package org.frekele.cielo.lio.remote.client.model.id;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderIdTest {

    @Test
    public void testNewInstance() throws Exception {
        String id = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";

        OrderId orderId = new OrderId();
        orderId.setId(id);

        assertNotNull(orderId);
        assertEquals(id, orderId.getId());
    }
}
