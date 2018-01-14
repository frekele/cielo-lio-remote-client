package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.CieloLioEnvironmentEnum;
import org.frekele.cielo.lio.remote.client.model.IdCieloEntity;
import org.frekele.cielo.lio.remote.client.model.OrderCieloEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemCieloEntity;
import org.frekele.cielo.lio.remote.client.model.OrderTransactionCieloEntity;
import org.frekele.cielo.lio.remote.client.utils.CieloLioUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.inject.Inject;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class CieloLioPaymentRepositoryImpl implements CieloLioPaymentRepository {

    private static final long serialVersionUID = 1L;

    private static final String targetUrl = "https://api.cielo.com.br";

    private final ResteasyClient client;

    private final CieloLioAuth auth;

    @Inject
    public CieloLioPaymentRepositoryImpl(ResteasyClient client, CieloLioAuth auth) {
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
        ResteasyWebTarget webTarget;
        if (CieloLioEnvironmentEnum.PRODUCTION.equals(this.getAuth().getEnvironment())) {
            webTarget = client.target(targetUrl);
        } else {
            webTarget = client.target(targetUrl + "/sandbox-lio");
        }
        return webTarget.proxy(CieloOrderManagementProxyClient.class);
    }

    @Override
    public List<OrderCieloEntity> orderGetAll() {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetAll(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId());
    }

    @Override
    public List<OrderCieloEntity> orderGetByNumber(String number) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByNumber(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), number);
    }

    @Override
    public List<OrderCieloEntity> orderGetByReference(String reference) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByReference(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), reference);
    }

    @Override
    public List<OrderCieloEntity> orderGetByStatus(String status) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByStatus(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), status);
    }

    @Override
    public OrderCieloEntity orderGet(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGet(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public IdCieloEntity orderPost(OrderCieloEntity order) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPost(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), order);
    }

    @Override
    public void orderPut(String orderId, OrderCieloEntity order) {
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
    public List<OrderItemCieloEntity> orderGetItems(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItems(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public OrderItemCieloEntity orderGetItem(String orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idItem);
    }

    @Override
    public IdCieloEntity orderPostItem(String orderId, OrderItemCieloEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, orderItem);
    }

    @Override
    public void orderPutItem(String orderId, String idItem, OrderItemCieloEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idItem, orderItem);
    }

    @Override
    public void orderDeleteItem(String orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDeleteItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idItem);
    }

    @Override
    public List<OrderTransactionCieloEntity> orderGetTransactions(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactions(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public OrderTransactionCieloEntity orderGetTransactionById(String orderId, String idTransaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactionById(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idTransaction);
    }

    @Override
    public IdCieloEntity orderPostTransaction(String orderId, OrderTransactionCieloEntity transaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, transaction);
    }
}
