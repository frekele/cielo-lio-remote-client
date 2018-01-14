package org.frekele.cielo.lio.remote.client.auth;

import java.io.Serializable;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public final class CieloLioAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String clientId;

    private final String accessToken;

    private final String merchantId;

    public CieloLioAuth(String clientId, String accessToken, String merchantId) {
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.merchantId = merchantId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getMerchantId() {
        return merchantId;
    }
}
