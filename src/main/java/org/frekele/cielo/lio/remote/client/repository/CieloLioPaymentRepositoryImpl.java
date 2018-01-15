package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.core.CieloLio;
import org.frekele.cielo.lio.remote.client.enumeration.StatusCieloLioEnum;
import org.frekele.cielo.lio.remote.client.model.IdEntity;
import org.frekele.cielo.lio.remote.client.model.OrderEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemEntity;
import org.frekele.cielo.lio.remote.client.model.OrderTransactionEntity;
import org.frekele.cielo.lio.remote.client.util.CieloLioUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.inject.Inject;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@CieloLio
public class CieloLioPaymentRepositoryImpl implements CieloLioPaymentRepository {

    private static final long serialVersionUID = 1L;

    private final ResteasyClient client;

    private final CieloLioAuth auth;

    @Inject
    public CieloLioPaymentRepositoryImpl(@CieloLio ResteasyClient client, @CieloLio CieloLioAuth auth) {
        CieloLioUtils.throwInjection(client, auth);
        this.client = client;
        this.auth = auth;
    }

    public ResteasyClient getClient() {
        return client;
    }

    public CieloLioAuth getAuth() {
        return auth;
    }

    private CieloOrderManagementProxyClient getProxyClient() {
        ResteasyClient client = this.getClient();
        ResteasyWebTarget webTarget = client.target(this.getAuth().getEnvironment().getTargetUrl());
        return webTarget.proxy(CieloOrderManagementProxyClient.class);
    }

    @Override
    public List<OrderEntity> orderGetAll() {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetAll(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId());
    }

    @Override
    public List<OrderEntity> orderGetByNumber(String number) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByNumber(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), number);
    }

    @Override
    public List<OrderEntity> orderGetByReference(String reference) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByReference(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), reference);
    }

    @Override
    public List<OrderEntity> orderGetByStatus(StatusCieloLioEnum status) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByStatus(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), status);
    }

    @Override
    public OrderEntity orderGet(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGet(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public IdEntity orderPost(OrderEntity order) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPost(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), order);
    }

    @Override
    public void orderPut(String orderId, OrderEntity order) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPut(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, order);
    }

    @Override
    public void orderPutOperation(String orderId, String operation) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutOperation(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, operation);
    }

    @Override
    public void orderDelete(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDelete(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public List<OrderItemEntity> orderGetItems(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItems(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public OrderItemEntity orderGetItem(String orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idItem);
    }

    @Override
    public IdEntity orderPostItem(String orderId, OrderItemEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, orderItem);
    }

    @Override
    public void orderPutItem(String orderId, String idItem, OrderItemEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idItem, orderItem);
    }

    @Override
    public void orderDeleteItem(String orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDeleteItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idItem);
    }

    @Override
    public List<OrderTransactionEntity> orderGetTransactions(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactions(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public OrderTransactionEntity orderGetTransactionById(String orderId, String idTransaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactionById(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idTransaction);
    }

    @Override
    public IdEntity orderPostTransaction(String orderId, OrderTransactionEntity transaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, transaction);
    }
}
