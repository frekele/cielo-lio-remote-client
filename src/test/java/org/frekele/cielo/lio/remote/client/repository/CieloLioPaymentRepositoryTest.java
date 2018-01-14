package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.CieloLioEnvironmentEnum;
import org.frekele.cielo.lio.remote.client.junit.LifecycleLogger;
import org.frekele.cielo.lio.remote.client.junit.MockitoExtension;
import org.frekele.cielo.lio.remote.client.junit.TimeExecutionLogger;
import org.frekele.cielo.lio.remote.client.model.OrderCieloEntity;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.List;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@ExtendWith(MockitoExtension.class)
public class CieloLioPaymentRepositoryTest implements LifecycleLogger, TimeExecutionLogger {

    private static final Logger logger = Logger.getLogger(CieloLioPaymentRepositoryTest.class.getName());

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init(@Mock CieloLioPaymentRepositoryImpl repository, TestInfo testInfo) {
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        CieloLioEnvironmentEnum environment = CieloLioEnvironmentEnum.SANDBOX;
        when(repository.getAuth()).thenReturn(new CieloLioAuth(clientId, accessToken, merchantId, environment));
        when(repository.getClient()).thenReturn(new ResteasyClientBuilder().build());
    }

    @Test
    public void testOrderGetAll(@Mock CieloLioPaymentRepositoryImpl repository) throws Exception {
        List<OrderCieloEntity> resultList = repository.orderGetAll();
        System.out.println(repository);
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
