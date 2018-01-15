package org.frekele.cielo.lio.remote.client.converter.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import org.frekele.cielo.lio.remote.client.model.id.OrderTransactionId;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderTransactionIdJsonDeserializeTest {

    @Mock
    private JsonParser jsonParser;

    @BeforeClass
    public void beforeClass() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeserialize() throws Exception {
        OrderTransactionIdJsonDeserialize jsonDeserialize = new OrderTransactionIdJsonDeserialize();
        assertNotNull(jsonDeserialize);

        OrderTransactionId resultValue = jsonDeserialize.deserialize(null, null);
        assertNull(resultValue);

        when(jsonParser.readValueAs(String.class)).thenReturn(null);
        resultValue = jsonDeserialize.deserialize(jsonParser, null);
        assertNull(resultValue);

        String orderTransactionId = "a65fc398-c3dd-49fa-acf298-2aa89aa7a";
        when(jsonParser.readValueAs(String.class)).thenReturn(orderTransactionId);
        resultValue = jsonDeserialize.deserialize(jsonParser, null);
        assertNotNull(resultValue);
        assertEquals(new OrderTransactionId(orderTransactionId).getId(), resultValue.getId());
    }
}
