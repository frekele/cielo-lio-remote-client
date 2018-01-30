package org.frekele.cielo.lio.remote.client.exception;

import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Listeners(InvokedMethodListener.class)
public class CieloLioExceptionTest {

    String msg = "Error Message";

    IOException cause = new IOException("IO error");

    @Test(expectedExceptions = CieloLioException.class)
    public void testConstructorWithMessage() throws Exception {
        throw new CieloLioException(msg);
    }

    @Test(expectedExceptions = CieloLioException.class)
    public void testConstructorWithCause() throws Exception {
        throw new CieloLioException(cause);
    }

    @Test(expectedExceptions = CieloLioException.class)
    public void testConstructorWithMessageAndCause() throws Exception {
        throw new CieloLioException(msg, cause);
    }

    @Test(expectedExceptions = CieloLioException.class)
    public void testConstructorWithMessageAndCauseAndMore() throws Exception {
        throw new CieloLioException(msg, cause, true, true);
    }
}
