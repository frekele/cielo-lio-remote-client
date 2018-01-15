package org.frekele.cielo.lio.remote.client.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.frekele.cielo.lio.remote.client.model.id.OrderTransactionId;

import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class OrderTransactionIdJsonSerialize extends StdSerializer<OrderTransactionId> {

    protected OrderTransactionIdJsonSerialize() {
        super(OrderTransactionId.class);
    }

    @Override
    public void serialize(OrderTransactionId value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null && value.getId() != null) {
            jsonGenerator.writeString(value.getId());
        }
    }
}
