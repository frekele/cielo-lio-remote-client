package org.frekele.cielo.lio.remote.client.model.id;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Listeners(InvokedMethodListener.class)
public class ResponseIdTest {

    @Test
    public void testNewInstance() throws Exception {
        String id = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";

        ResponseId orderId = new ResponseId();
        orderId.setId(id);

        assertNotNull(orderId);
        assertEquals(id, orderId.getId());
    }
}
