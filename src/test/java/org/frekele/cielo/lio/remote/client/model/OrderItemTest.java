package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderItemTest {

    @Test
    public void testNewInstance() throws Exception {
        String idOrderItem = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";
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

        OrderItem orderItem = new OrderItem();
        orderItem.setId(idOrderItem);
        orderItem.setUuid(uuid);
        orderItem.setSku(sku);
        orderItem.setName(name);
        orderItem.setDescription(description);
        orderItem.setUnitPrice(unitPrice);
        orderItem.setQuantity(quantity);
        orderItem.setUnitOfMeasure(unitOfMeasure);
        orderItem.setDetails(details);
        orderItem.setReference(reference);
        orderItem.setCreatedAt(createdAt);
        orderItem.setUpdatedAt(updatedAt);

        assertNotNull(orderItem);
        assertEquals(idOrderItem, orderItem.getId());
        assertEquals(uuid, orderItem.getUuid());
        assertEquals(sku, orderItem.getSku());
        assertEquals(name, orderItem.getName());
        assertEquals(description, orderItem.getDescription());
        assertEquals(unitPrice, orderItem.getUnitPrice());
        assertEquals(quantity, orderItem.getQuantity());
        assertEquals(unitOfMeasure, orderItem.getUnitOfMeasure());
        assertEquals(details, orderItem.getDetails());
        assertEquals(reference, orderItem.getReference());
        assertEquals(createdAt, orderItem.getCreatedAt());
        assertEquals(updatedAt, orderItem.getUpdatedAt());

        orderItem = OrderItem.newBuilder()
            .withId(idOrderItem)
            .withUuid(uuid)
            .withSku(sku)
            .withName(name)
            .withDescription(description)
            .withUnitPrice(unitPrice)
            .withQuantity(quantity)
            .withUnitOfMeasure(unitOfMeasure)
            .withDetails(details)
            .withReference(reference)
            .withCreatedAt(createdAt)
            .withUpdatedAt(updatedAt)
            .build();

        assertNotNull(orderItem);
        assertEquals(idOrderItem, orderItem.getId());
        assertEquals(uuid, orderItem.getUuid());
        assertEquals(sku, orderItem.getSku());
        assertEquals(name, orderItem.getName());
        assertEquals(description, orderItem.getDescription());
        assertEquals(unitPrice, orderItem.getUnitPrice());
        assertEquals(quantity, orderItem.getQuantity());
        assertEquals(unitOfMeasure, orderItem.getUnitOfMeasure());
        assertEquals(details, orderItem.getDetails());
        assertEquals(reference, orderItem.getReference());
        assertEquals(createdAt, orderItem.getCreatedAt());
        assertEquals(updatedAt, orderItem.getUpdatedAt());
    }
}
