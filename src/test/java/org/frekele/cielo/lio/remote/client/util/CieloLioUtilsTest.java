package org.frekele.cielo.lio.remote.client.util;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.EnvironmentCieloLioEnum;
import org.frekele.cielo.lio.remote.client.exception.CieloLioException;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
import org.frekele.cielo.lio.remote.client.model.id.OrderItemId;
import org.frekele.cielo.lio.remote.client.model.id.OrderTransactionId;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;

@Listeners(InvokedMethodListener.class)
public class CieloLioUtilsTest {

    String clientId = "jkdsfhskdj";

    String accessToken = "kjhrgfhe8354ty9r";

    String merchantId = "hfjskfh-ertret-rter-tery-dreter";

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
        CieloLioAuth auth = new CieloLioAuth(null, null, null, null);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError2() throws Exception {
        CieloLioAuth auth = new CieloLioAuth("", null, null, null);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError3() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, null, null, null);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError4() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, "", null, null);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError5() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, null, null);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError6() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, "", null);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError7() throws Exception {
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, null);
        CieloLioUtils.throwAuth(auth);
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowAuthWithError8() throws Exception {
        CieloLioUtils.throwAuth(null);
    }

    @Test
    public void testThrowOrderId() throws Exception {
        CieloLioUtils.throwOrderId(new OrderId("af9658-c3dd-49fa-acf29c38-2aa7aa89a"));
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowOrderIdError() throws Exception {
        CieloLioUtils.throwOrderId(new OrderId(null));
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowOrderIdError2() throws Exception {
        CieloLioUtils.throwOrderId(new OrderId(""));
    }

    @Test
    public void testThrowOrderItemId() throws Exception {
        CieloLioUtils.throwOrderItemId(new OrderItemId("af9658-c3dd-49fa-acf29c38-2aa7aa89a"));
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowOrderItemIdError() throws Exception {
        CieloLioUtils.throwOrderItemId(new OrderItemId(null));
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowOrderItemIdError2() throws Exception {
        CieloLioUtils.throwOrderItemId(new OrderItemId(""));
    }

    @Test
    public void testThrowOrderTransactionId() throws Exception {
        CieloLioUtils.throwOrderTransactionId(new OrderTransactionId("af9658-c3dd-49fa-acf29c38-2aa7aa89a"));
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowOrderTransactionIdError() throws Exception {
        CieloLioUtils.throwOrderTransactionId(new OrderTransactionId(null));
    }

    @Test(expectedExceptions = {CieloLioException.class})
    public void testThrowOrderTransactionIdError2() throws Exception {
        CieloLioUtils.throwOrderTransactionId(new OrderTransactionId(""));
    }
}
