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

    private final EnvironmentCieloLioEnum environment;

    public CieloLioAuth(String clientId, String accessToken, String merchantId, EnvironmentCieloLioEnum environment) {
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.merchantId = merchantId;
        this.environment = environment;
    }

    public CieloLioAuth(String clientId, String accessToken, String merchantId, String environment) {
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.merchantId = merchantId;
        this.environment = EnvironmentCieloLioEnum.fromValue(environment);
    }

    private CieloLioAuth(Builder builder) {
        clientId = builder.clientId;
        accessToken = builder.accessToken;
        merchantId = builder.merchantId;
        environment = builder.environment;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public EnvironmentCieloLioEnum getEnvironment() {
        return environment;
    }

    public static final class Builder {

        private String clientId;

        private String accessToken;

        private String merchantId;

        private EnvironmentCieloLioEnum environment;

        private Builder() {
        }

        public Builder withClientId(String val) {
            clientId = val;
            return this;
        }

        public Builder withAccessToken(String val) {
            accessToken = val;
            return this;
        }

        public Builder withMerchantId(String val) {
            merchantId = val;
            return this;
        }

        public Builder withEnvironment(EnvironmentCieloLioEnum val) {
            environment = val;
            return this;
        }

        public Builder withEnvironment(String val) {
            return withEnvironment(EnvironmentCieloLioEnum.fromValue(val));
        }

        public CieloLioAuth build() {
            return new CieloLioAuth(this);
        }
    }
}
