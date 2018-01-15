package org.frekele.cielo.lio.remote.client.util;

import org.frekele.cielo.lio.remote.client.exception.CieloLioException;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;

@Listeners(InvokedMethodListener.class)
public class CieloLioUtilsTest {

    @Test
    public void testThrowInjection() throws Exception {
        CieloLioUtils.throwInjection("");
        CieloLioUtils.throwInjection("a");
        CieloLioUtils.throwInjection("a", "b");
        CieloLioUtils.throwInjection("a", "b", "c", 1, 3, 5);
        CieloLioUtils.throwInjection("a", "b", "c", 1, 3, 5, new BigDecimal(0.0));
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowInjectionWithError() throws Exception {
        String val = null;
        CieloLioUtils.throwInjection(val);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowInjectionWithError2() throws Exception {
        String val1 = "";
        BigDecimal val2 = null;
        CieloLioUtils.throwInjection(val1, val2);
    }
}
