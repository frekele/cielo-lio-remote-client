package org.frekele.cielo.lio.remote.client.auth;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class CieloLioAuthTest {

    @Test
    public void testNewInstance() throws Exception {
        String clientId = "jkdsfhskdj";
        String accessToken = "kjhrgfhe8354ty9r";
        String merchantId = "hfjskfh-ertret-rter-tery-dreter";
        EnvironmentCieloLioEnum environment = EnvironmentCieloLioEnum.SANDBOX;

        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);

        assertNotEquals(null, auth);
        assertEquals(clientId, auth.getClientId());
        assertEquals(accessToken, auth.getAccessToken());
        assertEquals(merchantId, auth.getMerchantId());
        assertEquals(environment, auth.getEnvironment());
    }
}