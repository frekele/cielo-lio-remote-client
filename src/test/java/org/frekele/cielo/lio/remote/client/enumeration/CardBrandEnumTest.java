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
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("AMEX");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("CABAL");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("DINERS");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("DISCOVER");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("ELO");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("HIPERCARD");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("HIPER");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("JCB");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("MASTERCARD");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("SOROCRED");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("VISA");
        assertNotNull(environment);
        environment = CardBrandEnum.fromValue("XXXXXXXXXX");
        assertNull(environment);
    }

    @Test
    public void testGetAll() throws Exception {
        List<CardBrandEnum> listAll = CardBrandEnum.getAll();
        assertNotNull(listAll);
        assertEquals(listAll.size(), 12);
        assertEquals(listAll.contains(CardBrandEnum.AURA), true);
        assertEquals(listAll.contains(CardBrandEnum.AMERICAN_EXPRESS), true);
        assertEquals(listAll.contains(CardBrandEnum.CABAL), true);
        assertEquals(listAll.contains(CardBrandEnum.DINERS_CLUB), true);
        assertEquals(listAll.contains(CardBrandEnum.DISCOVER), true);
        assertEquals(listAll.contains(CardBrandEnum.ELO), true);
        assertEquals(listAll.contains(CardBrandEnum.HIPERCARD), true);
        assertEquals(listAll.contains(CardBrandEnum.HIPER), true);
        assertEquals(listAll.contains(CardBrandEnum.JCB), true);
        assertEquals(listAll.contains(CardBrandEnum.MASTERCARD), true);
        assertEquals(listAll.contains(CardBrandEnum.SOROCRED), true);
        assertEquals(listAll.contains(CardBrandEnum.VISA), true);
    }
}
