package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.CieloLioEnvironmentEnum;
import org.frekele.cielo.lio.remote.client.core.CieloLio;
import org.frekele.cielo.lio.remote.client.enumeration.StatusEnum;
import org.frekele.cielo.lio.remote.client.model.IdCieloLioEntity;
import org.frekele.cielo.lio.remote.client.model.OrderCieloLioEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemCieloLioEntity;
import org.frekele.cielo.lio.remote.client.model.OrderTransactionCieloLioEntity;
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

    private static final String targetUrl = "https://api.cielo.com.br";

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
        ResteasyWebTarget webTarget;
        if (CieloLioEnvironmentEnum.PRODUCTION.equals(this.getAuth().getEnvironment())) {
            webTarget = client.target(targetUrl);
        } else {
            webTarget = client.target(targetUrl + "/sandbox-lio");
        }
        return webTarget.proxy(CieloOrderManagementProxyClient.class);
    }

    @Override
    public List<OrderCieloLioEntity> orderGetAll() {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetAll(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId());
    }

    @Override
    public List<OrderCieloLioEntity> orderGetByNumber(String number) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByNumber(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), number);
    }

    @Override
    public List<OrderCieloLioEntity> orderGetByReference(String reference) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByReference(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), reference);
    }

    @Override
    public List<OrderCieloLioEntity> orderGetByStatus(StatusEnum status) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetByStatus(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), status);
    }

    @Override
    public OrderCieloLioEntity orderGet(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGet(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public IdCieloLioEntity orderPost(OrderCieloLioEntity order) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPost(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), order);
    }

    @Override
    public void orderPut(String orderId, OrderCieloLioEntity order) {
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
    public List<OrderItemCieloLioEntity> orderGetItems(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItems(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public OrderItemCieloLioEntity orderGetItem(String orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idItem);
    }

    @Override
    public IdCieloLioEntity orderPostItem(String orderId, OrderItemCieloLioEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, orderItem);
    }

    @Override
    public void orderPutItem(String orderId, String idItem, OrderItemCieloLioEntity orderItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderPutItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idItem, orderItem);
    }

    @Override
    public void orderDeleteItem(String orderId, String idItem) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        proxyClient.orderDeleteItem(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idItem);
    }

    @Override
    public List<OrderTransactionCieloLioEntity> orderGetTransactions(String orderId) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactions(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId);
    }

    @Override
    public OrderTransactionCieloLioEntity orderGetTransactionById(String orderId, String idTransaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderGetTransactionById(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, idTransaction);
    }

    @Override
    public IdCieloLioEntity orderPostTransaction(String orderId, OrderTransactionCieloLioEntity transaction) {
        CieloOrderManagementProxyClient proxyClient = this.getProxyClient();
        return proxyClient.orderPostTransaction(this.getAuth().getClientId(), this.getAuth().getAccessToken(), this.getAuth().getMerchantId(), orderId, transaction);
    }
}
