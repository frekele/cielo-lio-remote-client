package org.frekele.cielo.lio.remote.client.converter.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderIdJsonDeserializeTest {

    @Mock
    private JsonParser jsonParser;

    @BeforeClass
    public void beforeClass() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeserialize() throws Exception {
        OrderIdJsonDeserialize jsonDeserialize = new OrderIdJsonDeserialize();
        assertNotNull(jsonDeserialize);

        OrderId resultValue = jsonDeserialize.deserialize(null, null);
        assertNull(resultValue);

        when(jsonParser.readValueAs(String.class)).thenReturn(null);
        resultValue = jsonDeserialize.deserialize(jsonParser, null);
        assertNull(resultValue);

        String orderId = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";
        when(jsonParser.readValueAs(String.class)).thenReturn(orderId);
        resultValue = jsonDeserialize.deserialize(jsonParser, null);
        assertNotNull(resultValue);
        assertEquals(new OrderId(orderId).getId(), resultValue.getId());
    }
}
