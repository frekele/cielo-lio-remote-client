package org.frekele.cielo.lio.remote.client.converter.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import org.frekele.cielo.lio.remote.client.model.id.OrderTransactionId;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderTransactionIdJsonSerializeTest {

    @Mock
    JsonGenerator jsonGenerator;

    @BeforeClass
    public void beforeClass() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSerialize() throws Exception {
        OrderTransactionIdJsonSerialize jsonSerialize = new OrderTransactionIdJsonSerialize();
        assertNotNull(jsonSerialize);

        jsonSerialize.serialize(null, null, null);
        jsonSerialize.serialize(null, jsonGenerator, null);
        jsonSerialize.serialize(new OrderTransactionId(), jsonGenerator, null);
        jsonSerialize.serialize(new OrderTransactionId("a65fc398-c3dd-49fa-acf298-2aa89aa7a"), jsonGenerator, null);
    }
}
