package org.frekele.cielo.lio.remote.client.utils;

import org.frekele.cielo.lio.remote.client.exception.CieloLioException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class CieloLioUtils {

    public static void throwInjection(Object... objects) {
        String msg = "Parameters in the constructor were not injected!";
        if (objects == null) {
            throw new CieloLioException(msg);
        } else {
            for (Object obj : objects) {
                if (obj == null) {
                    throw new CieloLioException(msg);
                }
            }
        }
    }
}
