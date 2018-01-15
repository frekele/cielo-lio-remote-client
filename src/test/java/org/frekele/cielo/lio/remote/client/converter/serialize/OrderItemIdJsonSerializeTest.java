package org.frekele.cielo.lio.remote.client.converter.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import org.frekele.cielo.lio.remote.client.model.id.OrderItemId;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderItemIdJsonSerializeTest {

    @Mock
    JsonGenerator jsonGenerator;

    @BeforeClass
    public void beforeClass() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSerialize() throws Exception {
        OrderItemIdJsonSerialize jsonSerialize = new OrderItemIdJsonSerialize();
        assertNotNull(jsonSerialize);

        jsonSerialize.serialize(null, null, null);
        jsonSerialize.serialize(null, jsonGenerator, null);
        jsonSerialize.serialize(new OrderItemId(), jsonGenerator, null);
        jsonSerialize.serialize(new OrderItemId("aff29c9658-c3dd-49fa-ac38-2aa7aa89a"), jsonGenerator, null);
    }
}
