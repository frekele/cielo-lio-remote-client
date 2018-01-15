package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.enumeration.CardBrandEnum;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderCardEntityTest {

    @Test
    public void testNewInstance() throws Exception {
        String brand = CardBrandEnum.VISA.getValue();
        String mask = "************5487";

        OrderCardEntity entity = new OrderCardEntity();
        entity.setBrand(brand);
        entity.setMask(mask);

        assertNotNull(entity);
        assertEquals(brand, entity.getBrand());
        assertEquals(mask, entity.getMask());
    }
}
