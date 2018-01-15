package org.frekele.cielo.lio.remote.client.util;

import org.frekele.cielo.lio.remote.client.exception.CieloLioException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public final class CieloLioUtils {

    public static void throwInjection(Object... objects) {
        String msg = "Parameters in the constructor were not injected!";
        if (objects != null) {
            for (Object obj : objects) {
                if (obj == null) {
                    throw new CieloLioException(msg);
                }
            }
        }
    }
}
