package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.enumeration.CardBrandEnum;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderCardTest {

    @Test
    public void testNewInstance() throws Exception {
        String brand = CardBrandEnum.VISA.getValue();
        String mask = "************5487";

        OrderCard orderCard = new OrderCard();
        orderCard.setBrand(brand);
        orderCard.setMask(mask);

        assertNotNull(orderCard);
        assertEquals(brand, orderCard.getBrand());
        assertEquals(mask, orderCard.getMask());

        orderCard = OrderCard.newBuilder()
            .withBrand(brand)
            .withMask(mask)
            .build();

        assertNotNull(orderCard);
        assertEquals(brand, orderCard.getBrand());
        assertEquals(mask, orderCard.getMask());
    }
}
