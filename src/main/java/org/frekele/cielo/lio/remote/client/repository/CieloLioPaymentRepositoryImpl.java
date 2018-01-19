package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.core.CieloLio;
import org.frekele.cielo.lio.remote.client.enumeration.OperationEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.model.Order;
import org.frekele.cielo.lio.remote.client.model.OrderItem;
import org.frekele.cielo.lio.remote.client.model.OrderTransaction;
import org.frekele.cielo.lio.remote.client.model.id.ResponseId;
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
    public List<Order> orderGetAll() {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetAll(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId());
    }

    @Override
    public List<Order> orderGetByNumber(String number) {
        CieloLioUtils.throwObject(number, "number");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByNumber(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            number);
    }

    @Override
    public List<Order> orderGetByReference(String reference) {
        CieloLioUtils.throwObject(reference, "reference");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByReference(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            reference);
    }

    @Override
    public List<Order> orderGetByStatus(OrderStatusEnum status) {
        CieloLioUtils.throwObject(status, "OrderStatusEnum");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByStatus(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            status);
    }

    @Override
    public Order orderGet(String idOrder) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGet(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder);
    }

    @Override
    public String orderPost(Order order) {
        CieloLioUtils.throwObject(order, "Order");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        ResponseId responseId = proxyClient.orderPost(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            order);
        return CieloLioUtils.responseIdToId(responseId);
    }

    @Override
    public void orderPut(String idOrder, Order order) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(order, "Order");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPut(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, order);
    }

    @Override
    public void orderPutOperation(String idOrder, OperationEnum operation) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(operation, "OperationEnum");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutOperation(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, operation);
    }

    @Override
    public void orderDelete(String idOrder) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDelete(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder);
    }

    @Override
    public List<OrderItem> orderGetItems(String idOrder) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItems(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder);
    }

    @Override
    public OrderItem orderGetItem(String idOrder, String idOrderItem) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(idOrderItem, "idOrderItem");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, idOrderItem);
    }

    @Override
    public String orderPostItem(String idOrder, OrderItem orderItem) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(orderItem, "OrderItem");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        ResponseId responseId = proxyClient.orderPostItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, orderItem);
        return CieloLioUtils.responseIdToId(responseId);
    }

    @Override
    public void orderPutItem(String idOrder, String idOrderItem, OrderItem orderItem) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(idOrderItem, "idOrderItem");
        CieloLioUtils.throwObject(orderItem, "OrderItem");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, idOrderItem, orderItem);
    }

    @Override
    public void orderDeleteItem(String idOrder, String idOrderItem) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(idOrderItem, "idOrderItem");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDeleteItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, idOrderItem);
    }

    @Override
    public List<OrderTransaction> orderGetTransactions(String idOrder) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactions(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder);
    }

    @Override
    public OrderTransaction orderGetTransaction(String idOrder, String idOrderTransaction) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(idOrderTransaction, "idOrderTransaction");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, idOrderTransaction);
    }

    @Override
    public String orderPostTransaction(String idOrder, OrderTransaction orderTransaction) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(orderTransaction, "OrderTransaction");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        ResponseId responseId = proxyClient.orderPostTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, orderTransaction);
        return CieloLioUtils.responseIdToId(responseId);
    }
}
