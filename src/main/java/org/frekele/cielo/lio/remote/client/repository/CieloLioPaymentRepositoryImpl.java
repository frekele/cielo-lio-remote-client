package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.core.CieloLio;
import org.frekele.cielo.lio.remote.client.enumeration.OperationEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.model.OrderEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemEntity;
import org.frekele.cielo.lio.remote.client.model.OrderTransactionEntity;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
import org.frekele.cielo.lio.remote.client.model.id.OrderItemId;
import org.frekele.cielo.lio.remote.client.model.id.OrderTransactionId;
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
        CieloLioUtils.throwAuth(auth);
        this.client = client;
        this.auth = auth;
    }

    public ResteasyClient getClient() {
        return client;
    }

    public CieloLioAuth getAuth() {
        return auth;
    }

    public CieloOrderManagementProxyClient getProxyClient() {
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
        CieloLioUtils.throwObject(number, "number");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByNumber(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            number);
    }

    @Override
    public List<OrderEntity> orderGetByReference(String reference) {
        CieloLioUtils.throwObject(reference, "reference");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByReference(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            reference);
    }

    @Override
    public List<OrderEntity> orderGetByStatus(OrderStatusEnum status) {
        CieloLioUtils.throwObject(status, "OrderStatusEnum");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByStatus(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            status);
    }

    @Override
    public OrderEntity orderGet(OrderId orderId) {
        CieloLioUtils.throwOrderId(orderId);
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGet(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId());
    }

    @Override
    public OrderId orderPost(OrderEntity order) {
        CieloLioUtils.throwObject(order, "OrderEntity");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPost(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            order);
    }

    @Override
    public void orderPut(OrderId orderId, OrderEntity order) {
        CieloLioUtils.throwOrderId(orderId);
        CieloLioUtils.throwObject(order, "OrderEntity");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPut(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), order);
    }

    @Override
    public void orderPutOperation(OrderId orderId, OperationEnum operation) {
        CieloLioUtils.throwOrderId(orderId);
        CieloLioUtils.throwObject(operation, "OperationEnum");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutOperation(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), operation);
    }

    @Override
    public void orderDelete(OrderId orderId) {
        CieloLioUtils.throwOrderId(orderId);
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDelete(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId());
    }

    @Override
    public List<OrderItemEntity> orderGetItems(OrderId orderId) {
        CieloLioUtils.throwOrderId(orderId);
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItems(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId());
    }

    @Override
    public OrderItemEntity orderGetItem(OrderId orderId, OrderItemId idItem) {
        CieloLioUtils.throwOrderId(orderId);
        CieloLioUtils.throwOrderItemId(idItem);
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), idItem.getId());
    }

    @Override
    public OrderItemId orderPostItem(OrderId orderId, OrderItemEntity orderItem) {
        CieloLioUtils.throwOrderId(orderId);
        CieloLioUtils.throwObject(orderItem, "OrderItemEntity");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), orderItem);
    }

    @Override
    public void orderPutItem(OrderId orderId, OrderItemId idItem, OrderItemEntity orderItem) {
        CieloLioUtils.throwOrderId(orderId);
        CieloLioUtils.throwOrderItemId(idItem);
        CieloLioUtils.throwObject(orderItem, "OrderItemEntity");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), idItem.getId(), orderItem);
    }

    @Override
    public void orderDeleteItem(OrderId orderId, OrderItemId idItem) {
        CieloLioUtils.throwOrderId(orderId);
        CieloLioUtils.throwOrderItemId(idItem);
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDeleteItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), idItem.getId());
    }

    @Override
    public List<OrderTransactionEntity> orderGetTransactions(OrderId orderId) {
        CieloLioUtils.throwOrderId(orderId);
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactions(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId());
    }

    @Override
    public OrderTransactionEntity orderGetTransaction(OrderId orderId, OrderTransactionId transactionId) {
        CieloLioUtils.throwOrderId(orderId);
        CieloLioUtils.throwOrderTransactionId(transactionId);
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), transactionId.getId());
    }

    @Override
    public OrderTransactionId orderPostTransaction(OrderId orderId, OrderTransactionEntity transaction) {
        CieloLioUtils.throwOrderId(orderId);
        CieloLioUtils.throwObject(transaction, "OrderTransactionEntity");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            orderId.getId(), transaction);
    }
}
