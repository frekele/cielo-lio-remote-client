package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderTest {

    @Test
    public void testNewInstance() throws Exception {
        String idOrder = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";
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
        List<OrderItem> items = new ArrayList<>();
        OrderItem orderItem = OrderItem.newBuilder().build();
        items.add(orderItem);
        List<OrderTransaction> transactions = new ArrayList<>();
        OrderTransaction orderTransaction = OrderTransaction.newBuilder().build();
        transactions.add(orderTransaction);

        Order order = new Order();
        order.setId(idOrder);
        order.setNumber(number);
        order.setReference(reference);
        order.setStatus(status);
        order.setNotes(notes);
        order.setPrice(price);
        order.setRemaining(remaining);
        order.setOrderType(orderType);
        order.setMerchant(merchant);
        order.setSourceId(sourceId);
        order.setCreatedAt(createdAt);
        order.setUpdatedAt(updatedAt);
        order.setItems(items);
        order.setTransactions(transactions);

        assertNotNull(order);
        assertEquals(idOrder, order.getId());
        assertEquals(number, order.getNumber());
        assertEquals(reference, order.getReference());
        assertEquals(status, order.getStatus());
        assertEquals(notes, order.getNotes());
        assertEquals(price, order.getPrice());
        assertEquals(remaining, order.getRemaining());
        assertEquals(orderType, order.getOrderType());
        assertEquals(merchant, order.getMerchant());
        assertEquals(sourceId, order.getSourceId());
        assertEquals(createdAt, order.getCreatedAt());
        assertEquals(updatedAt, order.getUpdatedAt());
        assertEquals(items, order.getItems());
        assertEquals(transactions, order.getTransactions());

        order = Order.newBuilder()
            .withId(idOrder)
            .withNumber(number)
            .withReference(reference)
            .withStatus(status)
            .withNotes(notes)
            .withPrice(price)
            .withRemaining(remaining)
            .withOrderType(orderType)
            .withMerchant(merchant)
            .withSourceId(sourceId)
            .withCreatedAt(createdAt)
            .withUpdatedAt(updatedAt)
            .withItems(items)
            .withTransactions(transactions)
            .build();

        assertNotNull(order);
        assertEquals(idOrder, order.getId());
        assertEquals(number, order.getNumber());
        assertEquals(reference, order.getReference());
        assertEquals(status, order.getStatus());
        assertEquals(notes, order.getNotes());
        assertEquals(price, order.getPrice());
        assertEquals(remaining, order.getRemaining());
        assertEquals(orderType, order.getOrderType());
        assertEquals(merchant, order.getMerchant());
        assertEquals(sourceId, order.getSourceId());
        assertEquals(createdAt, order.getCreatedAt());
        assertEquals(updatedAt, order.getUpdatedAt());
        assertEquals(items, order.getItems());
        assertEquals(transactions, order.getTransactions());

        order = Order.newBuilder()
            .withItem(orderItem)
            .withTransaction(orderTransaction)
            .build();
        assertNotNull(order.getItems());
        assertEquals(orderItem, order.getItems().get(0));
        assertNotNull(order.getTransactions());
        assertEquals(orderTransaction, order.getTransactions().get(0));
    }
}
