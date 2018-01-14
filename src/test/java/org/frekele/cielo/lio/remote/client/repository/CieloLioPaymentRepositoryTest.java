package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.CieloLioEnvironmentEnum;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CieloLioPaymentRepositoryTest {

    private CieloLioAuth auth;

    private CieloLioPaymentRepository cieloLioPaymentRepository;

    @BeforeClass
    public void beforeClass() throws Exception {
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        CieloLioEnvironmentEnum environment = CieloLioEnvironmentEnum.SANDBOX;
        auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);
    }

    @AfterClass
    public void afterClass() throws Exception {

    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void afterMethod() throws Exception {

    }

    @Test
    public void testOrderGetAll() throws Exception {
        cieloLioPaymentRepository.orderGetAll();
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
