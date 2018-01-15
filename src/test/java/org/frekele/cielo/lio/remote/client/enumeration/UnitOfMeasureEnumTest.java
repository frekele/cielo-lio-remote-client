package org.frekele.cielo.lio.remote.client.enumeration;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class UnitOfMeasureEnumTest {

    @Test
    public void testFromValue() throws Exception {
        UnitOfMeasureEnum environment = UnitOfMeasureEnum.fromValue("EACH");
        assertNotEquals(null, environment);
        environment = UnitOfMeasureEnum.fromValue("HOURS");
        assertNotEquals(null, environment);
        environment = UnitOfMeasureEnum.fromValue("DAYS");
        assertNotEquals(null, environment);
        environment = UnitOfMeasureEnum.fromValue("SECONDS");
        assertNotEquals(null, environment);
        environment = UnitOfMeasureEnum.fromValue("CRATE_OF_12");
        assertNotEquals(null, environment);
        environment = UnitOfMeasureEnum.fromValue("SIX_PACK");
        assertNotEquals(null, environment);
        environment = UnitOfMeasureEnum.fromValue("GALLON");
        assertNotEquals(null, environment);
        environment = UnitOfMeasureEnum.fromValue("LITRE");
        assertNotEquals(null, environment);
    }

    @Test
    public void testGetAll() throws Exception {
        List<UnitOfMeasureEnum> listAll = UnitOfMeasureEnum.getAll();
        assertNotEquals(null, listAll);
        assertEquals(listAll.size(), 8);
        assertEquals(listAll.contains(UnitOfMeasureEnum.EACH), true);
        assertEquals(listAll.contains(UnitOfMeasureEnum.HOURS), true);
        assertEquals(listAll.contains(UnitOfMeasureEnum.DAYS), true);
        assertEquals(listAll.contains(UnitOfMeasureEnum.SECONDS), true);
        assertEquals(listAll.contains(UnitOfMeasureEnum.CRATE_OF_12), true);
        assertEquals(listAll.contains(UnitOfMeasureEnum.SIX_PACK), true);
        assertEquals(listAll.contains(UnitOfMeasureEnum.GALLON), true);
        assertEquals(listAll.contains(UnitOfMeasureEnum.LITRE), true);
    }
}
