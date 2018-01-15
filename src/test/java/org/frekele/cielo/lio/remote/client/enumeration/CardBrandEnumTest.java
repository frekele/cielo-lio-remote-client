package org.frekele.cielo.lio.remote.client.enumeration;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class CardBrandEnumTest {

    @Test
    public void testFromValue() throws Exception {
        CardBrandEnum environment = CardBrandEnum.fromValue("AURA");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("AMEX");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("CABAL");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("DINERS");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("DISCOVER");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("ELO");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("HIPERCARD");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("HIPER");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("JCB");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("MASTERCARD");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("SOROCRED");
        assertNotEquals(null, environment);
        environment = CardBrandEnum.fromValue("VISA");
        assertNotEquals(null, environment);
    }

    @Test
    public void testGetAll() throws Exception {
        List<CardBrandEnum> listAll = CardBrandEnum.getAll();
        assertNotEquals(null, listAll);
        assertEquals(listAll.size(), 12);
        assertEquals(listAll.contains(CardBrandEnum.AMERICAN_EXPRESS), true);
        assertEquals(listAll.contains(CardBrandEnum.MASTERCARD), true);
        assertEquals(listAll.contains(CardBrandEnum.VISA), true);
        assertEquals(listAll.contains(CardBrandEnum.ELO), true);
    }
}
