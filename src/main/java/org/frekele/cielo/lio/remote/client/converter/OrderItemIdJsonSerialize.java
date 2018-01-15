package org.frekele.cielo.lio.remote.client.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.frekele.cielo.lio.remote.client.model.id.OrderItemId;

import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class OrderItemIdJsonSerialize extends StdSerializer<OrderItemId> {

    protected OrderItemIdJsonSerialize() {
        super(OrderItemId.class);
    }

    @Override
    public void serialize(OrderItemId value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null && value.getId() != null) {
            jsonGenerator.writeString(value.getId());
        }
    }
}
