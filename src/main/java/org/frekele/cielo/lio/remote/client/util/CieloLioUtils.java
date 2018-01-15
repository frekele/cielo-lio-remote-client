package org.frekele.cielo.lio.remote.client.util;

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
}
