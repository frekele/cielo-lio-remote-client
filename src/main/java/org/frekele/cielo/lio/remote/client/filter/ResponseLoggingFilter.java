package org.frekele.cielo.lio.remote.client.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.frekele.cielo.lio.remote.client.util.CieloLioUtils;
import org.jboss.logging.Logger;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */

public class ResponseLoggingFilter implements ClientResponseFilter {

    private Logger logger = Logger.getLogger(ResponseLoggingFilter.class.getName());

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("------------------------------------------------------------------");
        sb.append("\n");
        sb.append("<-- Response Filter:");
        sb.append("\n");
        sb.append("--> Request Filter:");
        sb.append("\n");
        sb.append("--> Request - Method = " + requestContext.getMethod());
        sb.append("\n");
        sb.append("--> Request - Uri = " + requestContext.getUri());
        sb.append("\n");
        sb.append("<-- Response - Status = " + responseContext.getStatus());
        sb.append("\n");
        if (requestContext.hasEntity()) {
            String body = CieloLioUtils.responseBodyToString(responseContext);
            if (body != null && !body.trim().isEmpty()) {
                JsonNode jsonNode = this.getMapper().readTree(body);
                body = this.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                sb.append(body);
                sb.append("\n");
            }
        }
        sb.append("------------------------------------------------------------------");
        this.getLogger().info(sb.toString());
    }

    public Logger getLogger() {
        return logger;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
