package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderEntityTest {

    @Test
    public void testNewInstance() throws Exception {
        String orderId = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";
        OrderId id = new OrderId(orderId);
        String number = "1234356";
        String reference = "Order 1234356";
        OrderStatusEnum status = OrderStatusEnum.DRAFT;
        String notes = "Consumer Jim Jonson";
        BigDecimal price = BigDecimal.valueOf(643.46);
        BigDecimal remaining = BigDecimal.valueOf(10.20);
        String orderType = "PAYMENT";
        String merchant = "0596001092033001";
        String sourceId = "eQDKs463ZeQz";
        OffsetDateTime createdAt = OffsetDateTime.now();
        OffsetDateTime updatedAt = OffsetDateTime.now().plusHours(6);
        List<OrderItemEntity> items = new ArrayList<>();
        List<OrderTransactionEntity> transactions = new ArrayList<>();

        OrderEntity entity = new OrderEntity();
        entity.setId(id);
        entity.setNumber(number);
        entity.setReference(reference);
        entity.setStatus(status);
        entity.setNotes(notes);
        entity.setPrice(price);
        entity.setRemaining(remaining);
        entity.setOrderType(orderType);
        entity.setMerchant(merchant);
        entity.setSourceId(sourceId);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);
        entity.setItems(items);
        entity.setTransactions(transactions);

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals(orderId, entity.getId().getId());
        assertEquals(number, entity.getNumber());
        assertEquals(reference, entity.getReference());
        assertEquals(status, entity.getStatus());
        assertEquals(notes, entity.getNotes());
        assertEquals(price, entity.getPrice());
        assertEquals(remaining, entity.getRemaining());
        assertEquals(orderType, entity.getOrderType());
        assertEquals(merchant, entity.getMerchant());
        assertEquals(sourceId, entity.getSourceId());
        assertEquals(createdAt, entity.getCreatedAt());
        assertEquals(updatedAt, entity.getUpdatedAt());
        assertEquals(items, entity.getItems());
        assertEquals(transactions, entity.getTransactions());
    }
}
