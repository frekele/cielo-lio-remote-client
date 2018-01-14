package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.CieloLioEnvironmentEnum;
import org.frekele.cielo.lio.remote.client.model.OrderCieloEntity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class CieloLioPaymentRepositoryTest {

    private CieloLioPaymentRepository repository;

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        CieloLioEnvironmentEnum environment = CieloLioEnvironmentEnum.SANDBOX;
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);
        ResteasyClient client = new ResteasyClientBuilder().build();
        repository = new CieloLioPaymentRepositoryImpl(client, auth);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOrderGetAll() throws Exception {
        List<OrderCieloEntity> resultList = repository.orderGetAll();
    }

    @Test
    public void testOrderGetByNumber() throws Exception {
    }

    @Test
    public void testOrderGetByReference() throws Exception {
    }

    @Test
    public void testOrderGetByStatus() throws Exception {
    }

    @Test
    public void testOrderGet() throws Exception {
    }

    @Test
    public void testOrderPost() throws Exception {
    }

    @Test
    public void testOrderPut() throws Exception {
    }

    @Test
    public void testOrderPutOperation() throws Exception {
    }

    @Test
    public void testOrderDelete() throws Exception {
    }

    @Test
    public void testOrderGetItems() throws Exception {
    }

    @Test
    public void testOrderGetItem() throws Exception {
    }

    @Test
    public void testOrderPostItem() throws Exception {
    }

    @Test
    public void testOrderPutItem() throws Exception {
    }

    @Test
    public void testOrderDeleteItem() throws Exception {
    }

    @Test
    public void testOrderGetTransactions() throws Exception {
    }

    @Test
    public void testOrderGetTransactionById() throws Exception {
    }

    @Test
    public void testOrderPostTransaction() throws Exception {
    }
}
