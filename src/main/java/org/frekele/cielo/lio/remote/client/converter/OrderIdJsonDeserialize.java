package org.frekele.cielo.lio.remote.client.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;

import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class OrderIdJsonDeserialize extends StdDeserializer<OrderId> {

    protected OrderIdJsonDeserialize() {
        super(OrderId.class);
    }

    @Override
    public OrderId deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jsonParser != null) {
            String value = jsonParser.readValueAs(String.class);
            if (value != null) {
                return new OrderId(value);
            }
        }
        return null;
    }
}
