package org.frekele.cielo.lio.remote.client.exception;

import java.io.Serializable;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class CieloLioException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public CieloLioException(String message) {
        super(message);
    }

    public CieloLioException(Throwable cause) {
        super(cause);
    }

    public CieloLioException(String message, Throwable cause) {
        super(message, cause);
    }

    public CieloLioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
