package org.frekele.cielo.lio.remote.client.enumeration;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderStatusEnumTest {

    @Test
    public void testFromValue() throws Exception {
        OrderStatusEnum orderStatus = OrderStatusEnum.fromValue("DRAFT");
        assertNotNull(orderStatus);
        assertEquals(orderStatus.getValue(), "DRAFT");
        orderStatus = OrderStatusEnum.fromValue("ENTERED");
        assertNotNull(orderStatus);
        orderStatus = OrderStatusEnum.fromValue("RE_ENTERED");
        assertNotNull(orderStatus);
        orderStatus = OrderStatusEnum.fromValue("PAID");
        assertNotNull(orderStatus);
        orderStatus = OrderStatusEnum.fromValue("CANCELED");
        assertNotNull(orderStatus);
        orderStatus = OrderStatusEnum.fromValue("CLOSED");
        assertNotNull(orderStatus);
        orderStatus = OrderStatusEnum.fromValue("XXXXXXXXXX");
        assertNull(orderStatus);
    }

    @Test
    public void testGetAll() throws Exception {
        List<OrderStatusEnum> listAll = OrderStatusEnum.getAll();
        assertNotNull(listAll);
        assertEquals(listAll.size(), 6);
        assertEquals(listAll.contains(OrderStatusEnum.DRAFT), true);
        assertEquals(listAll.contains(OrderStatusEnum.ENTERED), true);
        assertEquals(listAll.contains(OrderStatusEnum.RE_ENTERED), true);
        assertEquals(listAll.contains(OrderStatusEnum.PAID), true);
        assertEquals(listAll.contains(OrderStatusEnum.CANCELED), true);
        assertEquals(listAll.contains(OrderStatusEnum.CLOSED), true);
    }
}
