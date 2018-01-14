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

    private final CieloLioEnvironmentEnum environment;

    public CieloLioAuth(String clientId, String accessToken, String merchantId, CieloLioEnvironmentEnum environment) {
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.merchantId = merchantId;
        this.environment = environment;
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

    public CieloLioEnvironmentEnum getEnvironment() {
        return environment;
    }
}