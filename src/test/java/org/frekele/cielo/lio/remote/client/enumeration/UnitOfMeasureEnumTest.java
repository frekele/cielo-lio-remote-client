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
        UnitOfMeasureEnum unitOfMeasure = UnitOfMeasureEnum.fromValue("EACH");
        assertNotNull(unitOfMeasure);
        assertEquals(unitOfMeasure.getValue(), "EACH");
        unitOfMeasure = UnitOfMeasureEnum.fromValue("HOURS");
        assertNotNull(unitOfMeasure);
        unitOfMeasure = UnitOfMeasureEnum.fromValue("DAYS");
        assertNotNull(unitOfMeasure);
        unitOfMeasure = UnitOfMeasureEnum.fromValue("SECONDS");
        assertNotNull(unitOfMeasure);
        unitOfMeasure = UnitOfMeasureEnum.fromValue("CRATE_OF_12");
        assertNotNull(unitOfMeasure);
        unitOfMeasure = UnitOfMeasureEnum.fromValue("SIX_PACK");
        assertNotNull(unitOfMeasure);
        unitOfMeasure = UnitOfMeasureEnum.fromValue("GALLON");
        assertNotNull(unitOfMeasure);
        unitOfMeasure = UnitOfMeasureEnum.fromValue("LITRE");
        assertNotNull(unitOfMeasure);
        unitOfMeasure = UnitOfMeasureEnum.fromValue("XXXXXXXXXX");
        assertNull(unitOfMeasure);
    }

    @Test
    public void testGetAll() throws Exception {
        List<UnitOfMeasureEnum> listAll = UnitOfMeasureEnum.getAll();
        assertNotNull(listAll);
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
