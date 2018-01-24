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
public class CieloLioRemoteRepositoryImpl implements CieloLioRemoteRepository {

    private static final long serialVersionUID = 1L;

    private final ResteasyClient client;

    private final CieloLioAuth auth;

    @Inject
    public CieloLioRemoteRepositoryImpl(@CieloLio ResteasyClient client, @CieloLio CieloLioAuth auth) {
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
    public List<Order> findOrders() {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.findOrders(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId());
    }

    @Override
    public List<Order> findOrdersByNumber(String number) {
        CieloLioUtils.throwObject(number, "number");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.findOrdersByNumber(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            number);
    }

    @Override
    public List<Order> findOrdersByReference(String reference) {
        CieloLioUtils.throwObject(reference, "reference");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.findOrdersByReference(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            reference);
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatusEnum status) {
        CieloLioUtils.throwObject(status, "OrderStatusEnum");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.findOrdersByStatus(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            status);
    }

    @Override
    public Order findOrder(String idOrder) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.findOrder(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder);
    }

    @Override
    public String createOrder(Order order) {
        CieloLioUtils.throwObject(order, "Order");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        ResponseId responseId = proxyClient.createOrder(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            order);
        return CieloLioUtils.responseIdToId(responseId);
    }

    @Override
    public void updateOrder(String idOrder, Order order) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(order, "Order");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.updateOrder(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, order);
    }

    @Override
    public void updateOrderStatus(String idOrder, OperationEnum operation) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(operation, "OperationEnum");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.updateOrderStatus(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, operation);
    }

    @Override
    public void deleteOrder(String idOrder) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.deleteOrder(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder);
    }

    @Override
    public List<OrderItem> findOrderItems(String idOrder) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.findOrderItems(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder);
    }

    @Override
    public OrderItem findOrderItem(String idOrder, String idOrderItem) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(idOrderItem, "idOrderItem");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.findOrderItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, idOrderItem);
    }

    @Override
    public String createOrderItem(String idOrder, OrderItem orderItem) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(orderItem, "OrderItem");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        ResponseId responseId = proxyClient.createOrderItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, orderItem);
        return CieloLioUtils.responseIdToId(responseId);
    }

    @Override
    public void updateOrderItem(String idOrder, String idOrderItem, OrderItem orderItem) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(idOrderItem, "idOrderItem");
        CieloLioUtils.throwObject(orderItem, "OrderItem");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.updateOrderItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, idOrderItem, orderItem);
    }

    @Override
    public void deleteOrderItem(String idOrder, String idOrderItem) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(idOrderItem, "idOrderItem");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.deleteOrderItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, idOrderItem);
    }

    @Override
    public List<OrderTransaction> findOrderTransactions(String idOrder) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.findOrderTransactions(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder);
    }

    @Override
    public OrderTransaction findOrderTransaction(String idOrder, String idOrderTransaction) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(idOrderTransaction, "idOrderTransaction");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.findOrderTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, idOrderTransaction);
    }

    @Override
    public String createOrderTransaction(String idOrder, OrderTransaction orderTransaction) {
        CieloLioUtils.throwObject(idOrder, "idOrder");
        CieloLioUtils.throwObject(orderTransaction, "OrderTransaction");
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        ResponseId responseId = proxyClient.createOrderTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(),
            idOrder, orderTransaction);
        return CieloLioUtils.responseIdToId(responseId);
    }
}
