package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.core.CieloLio;
import org.frekele.cielo.lio.remote.client.enumeration.OperationEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.model.OrderEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemEntity;
import org.frekele.cielo.lio.remote.client.model.OrderTransactionEntity;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
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
        return proxyClient.orderGetByNumber(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            number);
    }

    @Override
    public List<OrderEntity> orderGetByReference(String reference) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByReference(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            reference);
    }

    @Override
    public List<OrderEntity> orderGetByStatus(OrderStatusEnum status) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByStatus(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            status);
    }

    @Override
    public OrderEntity orderGet(OrderId orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGet(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId());
    }

    @Override
    public OrderId orderPost(OrderEntity order) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPost(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            order);
    }

    @Override
    public void orderPut(OrderId orderId, OrderEntity order) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPut(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), order);
    }

    @Override
    public void orderPutOperation(OrderId orderId, OperationEnum operation) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutOperation(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), operation);
    }

    @Override
    public void orderDelete(OrderId orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDelete(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId());
    }

    @Override
    public List<OrderItemEntity> orderGetItems(OrderId orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItems(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId());
    }

    @Override
    public OrderItemEntity orderGetItem(OrderId orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), idItem);
    }

    @Override
    public OrderId orderPostItem(OrderId orderId, OrderItemEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), orderItem);
    }

    @Override
    public void orderPutItem(OrderId orderId, String idItem, OrderItemEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), idItem, orderItem);
    }

    @Override
    public void orderDeleteItem(OrderId orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDeleteItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), idItem);
    }

    @Override
    public List<OrderTransactionEntity> orderGetTransactions(OrderId orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactions(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId());
    }

    @Override
    public OrderTransactionEntity orderGetTransactionById(OrderId orderId, String idTransaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactionById(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), idTransaction);
    }

    @Override
    public OrderId orderPostTransaction(OrderId orderId, OrderTransactionEntity transaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), transaction);
    }
}
