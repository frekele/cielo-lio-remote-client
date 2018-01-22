package org.frekele.cielo.lio.remote.client.resteasy;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class LoggingFilter implements ClientResponseFilter, ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        System.out.println("------------------------------------------------------------------");
        System.out.println("--> Request Filter:");
        System.out.println("--> Request - Uri= " + requestContext.getUri());
        System.out.println("--> Request - Method= " + requestContext.getMethod());
        System.out.println("------------------------------------------------------------------");
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        System.out.println("------------------------------------------------------------------");
        System.out.println("<-- Response Filter:");
        System.out.println("--> Response - Status= " + responseContext.getStatus());
        System.out.println("------------------------------------------------------------------");
    }
}
