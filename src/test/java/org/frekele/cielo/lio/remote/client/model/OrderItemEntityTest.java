package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.model.id.OrderItemId;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderItemEntityTest {

    @Test
    public void testNewInstance() throws Exception {
        String orderItemId = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";
        OrderItemId id = new OrderItemId(orderItemId);
        String uuid = "cf29c38-2aaf9658-c3d7aaad-49fa-a89a";
        String sku = "RFT-546-456-5466-S345";
        String name = "Sofa 3 Lugares Preto";
        String description = "Sofa 3 lugares - preto com pes de ferro.";
        BigDecimal unitPrice = BigDecimal.valueOf(2876.73);
        Integer quantity = 4;
        String unitOfMeasure = "UN";
        String details = "Xpto 1243567";
        String reference = "REF: 8534897";
        OffsetDateTime createdAt = OffsetDateTime.now();
        OffsetDateTime updatedAt = OffsetDateTime.now().plusHours(5);

        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(id);
        entity.setUuid(uuid);
        entity.setSku(sku);
        entity.setName(name);
        entity.setDescription(description);
        entity.setUnitPrice(unitPrice);
        entity.setQuantity(quantity);
        entity.setUnitOfMeasure(unitOfMeasure);
        entity.setDetails(details);
        entity.setReference(reference);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals(orderItemId, entity.getId().getId());
        assertEquals(uuid, entity.getUuid());
        assertEquals(sku, entity.getSku());
        assertEquals(name, entity.getName());
        assertEquals(description, entity.getDescription());
        assertEquals(unitPrice, entity.getUnitPrice());
        assertEquals(quantity, entity.getQuantity());
        assertEquals(unitOfMeasure, entity.getUnitOfMeasure());
        assertEquals(details, entity.getDetails());
        assertEquals(reference, entity.getReference());
        assertEquals(createdAt, entity.getCreatedAt());
        assertEquals(updatedAt, entity.getUpdatedAt());
    }
}
