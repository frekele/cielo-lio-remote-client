package org.frekele.cielo.lio.remote.client.model.id;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderItemIdTest {

    @Test
    public void testNewInstance() throws Exception {
        String id = "ac9c38-c3dd-49fa-affaa7aa29658-289a";

        OrderItemId entity = new OrderItemId();
        entity.setId(id);

        assertNotNull(entity);
        assertEquals(id, entity.getId());
    }
}
