package org.frekele.cielo.lio.remote.client.util;

import org.apache.commons.io.IOUtils;
import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.exception.CieloLioException;
import org.frekele.cielo.lio.remote.client.model.id.ResponseId;

import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

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

    public static void throwAuth(CieloLioAuth auth) {
        if (auth == null) {
            throw new CieloLioException("CieloLioAuth can not be Null!");
        }
        if (auth.getClientId() == null) {
            throw new CieloLioException("ClientId can not be Null!");
        }
        if (auth.getClientId().trim().isEmpty()) {
            throw new CieloLioException("ClientId can not be Empty!");
        }
        if (auth.getAccessToken() == null) {
            throw new CieloLioException("AccessToken can not be Null!");
        }
        if (auth.getAccessToken().trim().isEmpty()) {
            throw new CieloLioException("AccessToken can not be Empty!");
        }
        if (auth.getMerchantId() == null) {
            throw new CieloLioException("MerchantId can not be Null!");
        }
        if (auth.getMerchantId().trim().isEmpty()) {
            throw new CieloLioException("MerchantId can not be Empty!");
        }
        if (auth.getEnvironment() == null) {
            throw new CieloLioException("Environment can not be Null!");
        }
    }

    public static void throwObject(Object obj, String objectName) {
        if (obj == null || obj.toString().trim().isEmpty()) {
            throw new CieloLioException("" + objectName + " can not be Null!");
        }
    }

    public static String responseIdToId(ResponseId responseId) {
        return responseId == null ? null : responseId.getId();
    }

    public static String responseBodyToString(ClientResponseContext responseContext) throws IOException {
        String body = null;
        if (responseContext.hasEntity()) {
            //EntityStream can not be closed, resteasy will close automatically.
            InputStream entityStream = responseContext.getEntityStream();
            Charset charset = null;
            MediaType mediaType = responseContext.getMediaType();
            if (mediaType != null) {
                String charsetName = mediaType.getParameters().get("charset");
                if (charsetName != null) {
                    charset = Charset.forName(charsetName);
                }
            }
            if (charset == null) {
                charset = Charset.defaultCharset();
            }
            body = IOUtils.toString(entityStream, charset);
            //Original EntityStream is closed, add InputStream again for Security.
            responseContext.setEntityStream(IOUtils.toInputStream(body, charset));

        }
        return body;
    }
}
