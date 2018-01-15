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
        assertNotNull(environment);
        environment = OperationEnum.fromValue("PAY");
        assertNotNull(environment);
        environment = OperationEnum.fromValue("CLOSE");
        assertNotNull(environment);
        environment = OperationEnum.fromValue("PAID");
        assertNotNull(environment);
        environment = OperationEnum.fromValue("XXXXXXXXXX");
        assertNull(environment);
    }

    @Test
    public void testGetAll() throws Exception {
        List<OperationEnum> listAll = OperationEnum.getAll();
        assertNotNull(listAll);
        assertEquals(listAll.size(), 4);
        assertEquals(listAll.contains(OperationEnum.PLACE), true);
        assertEquals(listAll.contains(OperationEnum.PAY), true);
        assertEquals(listAll.contains(OperationEnum.CLOSE), true);
        assertEquals(listAll.contains(OperationEnum.PAID), true);
    }
}
