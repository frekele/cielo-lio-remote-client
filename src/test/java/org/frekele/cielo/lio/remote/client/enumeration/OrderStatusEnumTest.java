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
        OrderStatusEnum environment = OrderStatusEnum.fromValue("DRAFT");
        assertNotNull(environment);
        environment = OrderStatusEnum.fromValue("ENTERED");
        assertNotNull(environment);
        environment = OrderStatusEnum.fromValue("RE_ENTERED");
        assertNotNull(environment);
        environment = OrderStatusEnum.fromValue("PAID");
        assertNotNull(environment);
        environment = OrderStatusEnum.fromValue("CANCELED");
        assertNotNull(environment);
        environment = OrderStatusEnum.fromValue("CLOSED");
        assertNotNull(environment);
        environment = OrderStatusEnum.fromValue("XXXXXXXXXX");
        assertNull(environment);
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
