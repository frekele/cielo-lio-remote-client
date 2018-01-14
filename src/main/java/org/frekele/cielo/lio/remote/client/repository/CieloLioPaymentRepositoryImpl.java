package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.model.IdCieloEntity;
import org.frekele.cielo.lio.remote.client.model.OrderCieloEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemCieloEntity;
import org.frekele.cielo.lio.remote.client.model.OrderTransactionCieloEntity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class CieloLioPaymentRepositoryImpl implements CieloLioPaymentRepository {

    private static final long serialVersionUID = 1L;

    public CieloLioPaymentRepositoryImpl() {
    }

    private CieloOrderManagementProxyClient getProxyClient(CieloLioAuth auth) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("https://api.cielo.com.br/sandbox-lio");
        return target.proxy(CieloOrderManagementProxyClient.class);
    }

    @Override
    public List<OrderCieloEntity> orderGetAll(CieloLioAuth auth) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderGetAll(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId());
    }

    @Override
    public List<OrderCieloEntity> orderGetByNumber(CieloLioAuth auth, String number) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderGetByNumber(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), number);
    }

    @Override
    public List<OrderCieloEntity> orderGetByReference(CieloLioAuth auth, String reference) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderGetByReference(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), reference);
    }

    @Override
    public List<OrderCieloEntity> orderGetByStatus(CieloLioAuth auth, String status) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderGetByStatus(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), status);
    }

    @Override
    public OrderCieloEntity orderGet(CieloLioAuth auth, String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderGet(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId);
    }

    @Override
    public IdCieloEntity orderPost(CieloLioAuth auth, OrderCieloEntity order) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderPost(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), order);
    }

    @Override
    public void orderPut(CieloLioAuth auth, String orderId, OrderCieloEntity order) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        proxyClient.orderPut(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId, order);
    }

    @Override
    public void orderPutOperation(CieloLioAuth auth, String orderId, String operation) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        proxyClient.orderPutOperation(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId, operation);
    }

    @Override
    public void orderDelete(CieloLioAuth auth, String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        proxyClient.orderDelete(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId);
    }

    @Override
    public List<OrderItemCieloEntity> orderGetItems(CieloLioAuth auth, String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderGetItems(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId);
    }

    @Override
    public OrderItemCieloEntity orderGetItem(CieloLioAuth auth, String orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderGetItem(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId, idItem);
    }

    @Override
    public IdCieloEntity orderPostItem(CieloLioAuth auth, String orderId, OrderItemCieloEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderPostItem(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId, orderItem);
    }

    @Override
    public void orderPutItem(CieloLioAuth auth, String orderId, String idItem, OrderItemCieloEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        proxyClient.orderPutItem(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId, idItem, orderItem);
    }

    @Override
    public void orderDeleteItem(CieloLioAuth auth, String orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        proxyClient.orderDeleteItem(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId, idItem);
    }

    @Override
    public List<OrderTransactionCieloEntity> orderGetTransactions(CieloLioAuth auth, String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderGetTransactions(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId);
    }

    @Override
    public OrderTransactionCieloEntity orderGetTransactionById(CieloLioAuth auth, String orderId, String idTransaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderGetTransactionById(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId, idTransaction);
    }

    @Override
    public IdCieloEntity orderPostTransaction(CieloLioAuth auth, String orderId, OrderTransactionCieloEntity transaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient(auth);
        return proxyClient.orderPostTransaction(auth.getClientId(), auth.getAccessToken(), auth.getMerchantId(), orderId, transaction);
    }
}
