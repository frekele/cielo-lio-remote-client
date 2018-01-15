package org.frekele.cielo.lio.remote.client.util;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.exception.CieloLioException;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
import org.frekele.cielo.lio.remote.client.model.id.OrderItemId;
import org.frekele.cielo.lio.remote.client.model.id.OrderTransactionId;

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

    public static void throwOrderId(OrderId orderId) {
        if (orderId == null) {
            throw new CieloLioException("OrderId can not be Null!");
        }
        if (orderId.getId() == null) {
            throw new CieloLioException("OrderId.id can not be Null!");
        }
        if (orderId.getId().trim().isEmpty()) {
            throw new CieloLioException("OrderId.id can not be Empty!");
        }
    }

    public static void throwOrderItemId(OrderItemId orderItemId) {
        if (orderItemId == null) {
            throw new CieloLioException("OrderItemId can not be Null!");
        }
        if (orderItemId.getId() == null) {
            throw new CieloLioException("OrderItemId.id can not be Null!");
        }
        if (orderItemId.getId().trim().isEmpty()) {
            throw new CieloLioException("OrderItemId.id can not be Empty!");
        }
    }

    public static void throwOrderTransactionId(OrderTransactionId orderTransactionId) {
        if (orderTransactionId == null) {
            throw new CieloLioException("OrderTransactionId can not be Null!");
        }
        if (orderTransactionId.getId() == null) {
            throw new CieloLioException("OrderTransactionId.id can not be Null!");
        }
        if (orderTransactionId.getId().trim().isEmpty()) {
            throw new CieloLioException("OrderTransactionId.id can not be Empty!");
        }
    }

    public static void throwObject(Object obj, String objectName) {
        if (obj == null || obj.toString().trim().isEmpty()) {
            throw new CieloLioException("" + objectName + " can not be Null!");
        }
    }
}
