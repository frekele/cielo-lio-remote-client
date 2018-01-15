package org.frekele.cielo.lio.remote.client.converter.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.frekele.cielo.lio.remote.client.model.id.OrderTransactionId;

import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class OrderTransactionIdJsonDeserialize extends StdDeserializer<OrderTransactionId> {

    protected OrderTransactionIdJsonDeserialize() {
        super(OrderTransactionId.class);
    }

    @Override
    public OrderTransactionId deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jsonParser != null) {
            String value = jsonParser.readValueAs(String.class);
            if (value != null) {
                return new OrderTransactionId(value);
            }
        }
        return null;
    }
}
