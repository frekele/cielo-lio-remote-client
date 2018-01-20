package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderPaymentProductTest {

    @Test
    public void testNewInstance() throws Exception {
        String primaryProductName = "CREDITO";
        String secondaryProductName = "PARCELADO_LOJA";
        Integer numberOfQuotas = 1;

        OrderPaymentProduct orderPaymentProduct = new OrderPaymentProduct();
        orderPaymentProduct.setPrimaryProductName(primaryProductName);
        orderPaymentProduct.setSecondaryProductName(secondaryProductName);
        orderPaymentProduct.setNumberOfQuotas(numberOfQuotas);

        assertNotNull(orderPaymentProduct);
        assertEquals(primaryProductName, orderPaymentProduct.getPrimaryProductName());
        assertEquals(secondaryProductName, orderPaymentProduct.getSecondaryProductName());
        assertEquals(numberOfQuotas, orderPaymentProduct.getNumberOfQuotas());

        orderPaymentProduct = OrderPaymentProduct.newBuilder()
            .withPrimaryProductName(primaryProductName)
            .withSecondaryProductName(secondaryProductName)
            .withNumberOfQuotas(numberOfQuotas)
            .build();

        assertNotNull(orderPaymentProduct);
        assertEquals(primaryProductName, orderPaymentProduct.getPrimaryProductName());
        assertEquals(secondaryProductName, orderPaymentProduct.getSecondaryProductName());
        assertEquals(numberOfQuotas, orderPaymentProduct.getNumberOfQuotas());
    }
}
