package org.frekele.cielo.lio.remote.client.util;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.EnvironmentCieloLioEnum;
import org.frekele.cielo.lio.remote.client.exception.CieloLioException;
import org.frekele.cielo.lio.remote.client.model.id.ResponseId;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Listeners(InvokedMethodListener.class)
public class CieloLioUtilsTest {

    String clientId = "jkdsfhskdj";

    String accessToken = "kjhrgfhe8354ty9r";

    String merchantId = "hfjskfh-ertret-rter-tery-dreter";

    EnvironmentCieloLioEnum environmentNull = null;

    EnvironmentCieloLioEnum environment = EnvironmentCieloLioEnum.SANDBOX;

    @Test
    public void testThrowInjection() throws Exception {
        CieloLioUtils c = new CieloLioUtils();
        CieloLioUtils.throwInjection("");
        CieloLioUtils.throwInjection("a");
        CieloLioUtils.throwInjection("a", "b");
        CieloLioUtils.throwInjection("a", "b", "c", 1, 3, 5);
        CieloLioUtils.throwInjection("a", "b", "c", 1, 3, 5, new BigDecimal(0.0));
    }

    @Test
    public void testThrowInjection2() throws Exception {
        CieloLioUtils.throwInjection();
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

    @Test
    public void testThrowAuth() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError() throws Exception {
        CieloLioUtils.throwAuth(null);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError2() throws Exception {

        CieloLioAuth auth = new CieloLioAuth(null, null, null, environmentNull);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError3() throws Exception {
        CieloLioAuth auth = new CieloLioAuth("", null, null, environmentNull);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError4() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, null, null, environmentNull);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError5() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, "", null, environmentNull);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError6() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, null, environmentNull);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError7() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, "", environmentNull);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError8() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environmentNull);
        CieloLioUtils.throwAuth(auth);
    }

    @Test
    public void testThrowObject() throws Exception {
        CieloLioUtils.throwObject("xxxxx", "value");
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowObjectWithError() throws Exception {
        CieloLioUtils.throwObject(null, "value");
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowObjectWithError2() throws Exception {
        CieloLioUtils.throwObject("", "value");
    }

    @Test
    public void testResponseIdToId() throws Exception {
        String result = CieloLioUtils.responseIdToId(null);
        assertNull(result);

        result = CieloLioUtils.responseIdToId(new ResponseId());
        assertNull(result);

        result = CieloLioUtils.responseIdToId(new ResponseId(""));
        assertEquals("", result);

        String id = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";
        result = CieloLioUtils.responseIdToId(new ResponseId(id));
        assertEquals(id, result);
    }
}
