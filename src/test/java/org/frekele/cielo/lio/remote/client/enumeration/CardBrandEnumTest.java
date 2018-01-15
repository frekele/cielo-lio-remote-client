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
        CardBrandEnum cardBrand = CardBrandEnum.fromValue("AURA");
        assertNotNull(cardBrand);
        assertEquals(cardBrand.getValue(), "AURA");
        cardBrand = CardBrandEnum.fromValue("AMEX");
        assertNotNull(cardBrand);
        assertEquals(cardBrand.getValue(), "AMEX");
        cardBrand = CardBrandEnum.fromValue("CABAL");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("DINERS");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("DISCOVER");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("ELO");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("HIPERCARD");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("HIPER");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("JCB");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("MASTERCARD");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("SOROCRED");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("VISA");
        assertNotNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue(null);
        assertNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("");
        assertNull(cardBrand);
        cardBrand = CardBrandEnum.fromValue("XXXXXXXXXX");
        assertNull(cardBrand);
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
