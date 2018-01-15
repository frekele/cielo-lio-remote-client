package org.frekele.cielo.lio.remote.client.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.frekele.cielo.lio.remote.client.model.id.IdOrderEntity;

import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class IdOrderJsonSerialize extends StdSerializer<IdOrderEntity> {

    protected IdOrderJsonSerialize() {
        super(IdOrderEntity.class);
    }

    @Override
    public void serialize(IdOrderEntity value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null && value.getId() != null) {
            jsonGenerator.writeString(value.getId());
        }
    }
}
