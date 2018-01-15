package org.frekele.cielo.lio.remote.client.enumeration;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OperationEnumTest {

    @Test
    public void testFromValue() throws Exception {
        OperationEnum environment = OperationEnum.fromValue("PLACE");
        assertNotEquals(null, environment);
        environment = OperationEnum.fromValue("PAY");
        assertNotEquals(null, environment);
        environment = OperationEnum.fromValue("CLOSE");
        assertNotEquals(null, environment);
        environment = OperationEnum.fromValue("PAID");
        assertNotEquals(null, environment);
    }

    @Test
    public void testGetAll() throws Exception {
        List<OperationEnum> listAll = OperationEnum.getAll();
        assertNotEquals(null, listAll);
        assertEquals(listAll.size(), 4);
        assertEquals(listAll.contains(OperationEnum.PLACE), true);
        assertEquals(listAll.contains(OperationEnum.PAY), true);
        assertEquals(listAll.contains(OperationEnum.CLOSE), true);
        assertEquals(listAll.contains(OperationEnum.PAID), true);
    }
}
