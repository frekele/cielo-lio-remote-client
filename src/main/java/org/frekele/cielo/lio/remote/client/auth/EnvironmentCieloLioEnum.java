package org.frekele.cielo.lio.remote.client.auth;

import java.util.Arrays;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public enum EnvironmentCieloLioEnum {

    PRODUCTION("https://api.cielo.com.br"),
    SANDBOX("https://api.cielo.com.br/sandbox-lio");

    private String targetUrl;

    EnvironmentCieloLioEnum(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public static List<EnvironmentCieloLioEnum> getAll() {
        return Arrays.asList(EnvironmentCieloLioEnum.values());
    }

}