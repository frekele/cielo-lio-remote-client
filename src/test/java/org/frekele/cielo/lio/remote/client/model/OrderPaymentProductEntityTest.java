package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderPaymentProductEntityTest {

    @Test
    public void testNewInstance() throws Exception {
        String primaryProductName = "CREDITO";
        String secondaryProductName = "PARCELADO_LOJA";
        Integer numberOfQuotas = 1;

        OrderPaymentProductEntity entity = new OrderPaymentProductEntity();
        entity.setPrimaryProductName(primaryProductName);
        entity.setSecondaryProductName(secondaryProductName);
        entity.setNumberOfQuotas(numberOfQuotas);

        assertNotNull(entity);
        assertEquals(primaryProductName, entity.getPrimaryProductName());
        assertEquals(secondaryProductName, entity.getSecondaryProductName());
        assertEquals(numberOfQuotas, entity.getNumberOfQuotas());
    }
}
