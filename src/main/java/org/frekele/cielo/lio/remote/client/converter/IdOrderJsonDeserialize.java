package org.frekele.cielo.lio.remote.client.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.frekele.cielo.lio.remote.client.model.id.IdOrderEntity;

import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class IdOrderJsonDeserialize extends StdDeserializer<IdOrderEntity> {

    private static final int SCALE = 2;

    protected IdOrderJsonDeserialize() {
        super(IdOrderEntity.class);
    }

    @Override
    public IdOrderEntity deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jsonParser != null) {
            String value = jsonParser.readValueAs(String.class);
            if (value != null) {
                return new IdOrderEntity(value);
            }
        }
        return null;
    }
}
