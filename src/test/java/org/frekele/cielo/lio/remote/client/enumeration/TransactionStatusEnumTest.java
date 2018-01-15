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
        TransactionStatusEnum transactionStatus = TransactionStatusEnum.fromValue("CONFIRMED");
        assertNotNull(transactionStatus);
        assertEquals(transactionStatus.getValue(), "CONFIRMED");
        transactionStatus = TransactionStatusEnum.fromValue("PENDING");
        assertNotNull(transactionStatus);
        transactionStatus = TransactionStatusEnum.fromValue("CANCELLED");
        assertNotNull(transactionStatus);
        transactionStatus = TransactionStatusEnum.fromValue(null);
        assertNull(transactionStatus);
        transactionStatus = TransactionStatusEnum.fromValue("");
        assertNull(transactionStatus);
        transactionStatus = TransactionStatusEnum.fromValue("XXXXXXXXXX");
        assertNull(transactionStatus);
    }

    @Test
    public void testGetAll() throws Exception {
        List<TransactionStatusEnum> listAll = TransactionStatusEnum.getAll();
        assertNotNull(listAll);
        assertEquals(listAll.size(), 3);
        assertEquals(listAll.contains(TransactionStatusEnum.CONFIRMED), true);
        assertEquals(listAll.contains(TransactionStatusEnum.PENDING), true);
        assertEquals(listAll.contains(TransactionStatusEnum.CANCELLED), true);
    }
}
