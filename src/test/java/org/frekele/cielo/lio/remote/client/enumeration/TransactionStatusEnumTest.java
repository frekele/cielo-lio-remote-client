package org.frekele.cielo.lio.remote.client.enumeration;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class TransactionStatusEnumTest {

    @Test
    public void testFromValue() throws Exception {
        TransactionStatusEnum environment = TransactionStatusEnum.fromValue("CONFIRMED");
        assertNotEquals(null, environment);
        environment = TransactionStatusEnum.fromValue("PENDING");
        assertNotEquals(null, environment);
        environment = TransactionStatusEnum.fromValue("CANCELLED");
        assertNotEquals(null, environment);
        environment = TransactionStatusEnum.fromValue("XXXXXXXXXX");
        assertNull(environment);
    }

    @Test
    public void testGetAll() throws Exception {
        List<TransactionStatusEnum> listAll = TransactionStatusEnum.getAll();
        assertNotEquals(null, listAll);
        assertEquals(listAll.size(), 3);
        assertEquals(listAll.contains(TransactionStatusEnum.CONFIRMED), true);
        assertEquals(listAll.contains(TransactionStatusEnum.PENDING), true);
        assertEquals(listAll.contains(TransactionStatusEnum.CANCELLED), true);
    }
}
