package org.frekele.cielo.lio.remote.client.enumeration;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class TransactionTypeEnumTest {

    @Test
    public void testFromValue() throws Exception {
        TransactionTypeEnum environment = TransactionTypeEnum.fromValue("PAYMENT");
        assertNotNull(environment);
        environment = TransactionTypeEnum.fromValue("CANCELLATION");
        assertNotNull(environment);
        environment = TransactionTypeEnum.fromValue("XXXXXXXXXX");
        assertNull(environment);
    }

    @Test
    public void testGetAll() throws Exception {
        List<TransactionTypeEnum> listAll = TransactionTypeEnum.getAll();
        assertNotNull(listAll);
        assertEquals(listAll.size(), 2);
        assertEquals(listAll.contains(TransactionTypeEnum.PAYMENT), true);
        assertEquals(listAll.contains(TransactionTypeEnum.CANCELLATION), true);
    }
}
