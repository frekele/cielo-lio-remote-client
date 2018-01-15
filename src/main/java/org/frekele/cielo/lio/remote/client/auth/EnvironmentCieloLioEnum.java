package org.frekele.cielo.lio.remote.client.auth;

import java.util.Arrays;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public enum EnvironmentCieloLioEnum {

    PRODUCTION("PRODUCTION", "https://api.cielo.com.br"),
    SANDBOX("SANDBOX", "https://api.cielo.com.br/sandbox-lio");

    private String value;

    private String targetUrl;

    EnvironmentCieloLioEnum(String value, String targetUrl) {
        this.value = value;
        this.targetUrl = targetUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getValue() {
        return value;
    }

    public static EnvironmentCieloLioEnum fromValue(String value) {
        if (value != null && value.length() != 0) {
            for (EnvironmentCieloLioEnum obj : getAll()) {
                if (obj.getValue().equals(value)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public static List<EnvironmentCieloLioEnum> getAll() {
        return Arrays.asList(EnvironmentCieloLioEnum.values());
    }

}
