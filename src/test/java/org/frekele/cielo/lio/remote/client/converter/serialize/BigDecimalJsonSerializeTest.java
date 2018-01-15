package org.frekele.cielo.lio.remote.client.converter.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class BigDecimalJsonSerializeTest {

    @Mock
    JsonGenerator jsonGenerator;

    @BeforeClass
    public void beforeClass() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSerialize() throws Exception {
        BigDecimalJsonSerialize jsonSerialize = new BigDecimalJsonSerialize();
        assertNotNull(jsonSerialize);

        jsonSerialize.serialize(null, null, null);
        jsonSerialize.serialize(null, jsonGenerator, null);
        jsonSerialize.serialize(BigDecimal.valueOf(4565), jsonGenerator, null);
        jsonSerialize.serialize(BigDecimal.valueOf(345675, 2), jsonGenerator, null);
        jsonSerialize.serialize(BigDecimal.valueOf(45353453, 3), jsonGenerator, null);
    }
}
