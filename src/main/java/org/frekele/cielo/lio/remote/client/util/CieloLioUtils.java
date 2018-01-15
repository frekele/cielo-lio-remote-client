package org.frekele.cielo.lio.remote.client.util;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.exception.CieloLioException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public final class CieloLioUtils {

    public static void throwInjection(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                String msg = "Parameters in the constructor were not injected!";
                throw new CieloLioException(msg);
            }
        }
    }

    public static void throwAuth(CieloLioAuth auth) {
        if (auth == null) {
            throw new CieloLioException("CieloLioAuth can not be Null!");
        }
        if (auth.getClientId() == null) {
            throw new CieloLioException("ClientId can not be Null!");
        }
        if (auth.getClientId().trim().isEmpty()) {
            throw new CieloLioException("ClientId can not be Empty!");
        }
        if (auth.getAccessToken() == null) {
            throw new CieloLioException("AccessToken can not be Null!");
        }
        if (auth.getAccessToken().trim().isEmpty()) {
            throw new CieloLioException("AccessToken can not be Empty!");
        }
        if (auth.getMerchantId() == null) {
            throw new CieloLioException("MerchantId can not be Null!");
        }
        if (auth.getMerchantId().trim().isEmpty()) {
            throw new CieloLioException("MerchantId can not be Empty!");
        }
        if (auth.getEnvironment() == null) {
            throw new CieloLioException("Environment can not be Null!");
        }
    }
}
