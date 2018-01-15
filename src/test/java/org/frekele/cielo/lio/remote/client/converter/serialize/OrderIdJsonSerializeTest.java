package org.frekele.cielo.lio.remote.client.converter.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderIdJsonSerializeTest {

    @Mock
    JsonGenerator jsonGenerator;

    @BeforeClass
    public void beforeClass() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSerialize() throws Exception {
        OrderIdJsonSerialize jsonSerialize = new OrderIdJsonSerialize();
        assertNotNull(jsonSerialize);

        jsonSerialize.serialize(null, null, null);
        jsonSerialize.serialize(null, jsonGenerator, null);
        jsonSerialize.serialize(new OrderId(), jsonGenerator, null);
        jsonSerialize.serialize(new OrderId("af9658-c3dd-49fa-acf29c38-2aa7aa89a"), jsonGenerator, null);
    }
}
