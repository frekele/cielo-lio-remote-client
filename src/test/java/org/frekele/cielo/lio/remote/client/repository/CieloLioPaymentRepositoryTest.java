package org.frekele.cielo.lio.remote.client.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.frekele.cielo.lio.remote.client.InvokedMethodListener;
import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.CieloLioEnvironmentEnum;
import org.frekele.cielo.lio.remote.client.model.OrderCieloEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemCieloEntity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Listeners(InvokedMethodListener.class)
public class CieloLioPaymentRepositoryTest {

    private CieloLioPaymentRepository repository;

    @BeforeClass
    public void init() {
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        CieloLioEnvironmentEnum environment = CieloLioEnvironmentEnum.SANDBOX;
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);
        ResteasyClient client = new ResteasyClientBuilder().build();
        repository = new CieloLioPaymentRepositoryImpl(client, auth);
    }

    @Test
    public void testCrud() throws Exception {
        OrderCieloEntity order = new OrderCieloEntity();
        order.setNumber("12345");
        order.setReference("PEDIDO #12345");
        order.setNotes("Cliente Fulano de Tal");
        order.setPrice(BigDecimal.valueOf(125.34));
        order.setItems(new ArrayList<>());
        OrderItemCieloEntity item = new OrderItemCieloEntity();
        item.setSku("RTG-234-AQF-6587-C57");
        item.setName("Mesa de Formica Branca");
        item.setQuantity((long) 1);
        item.setUnitOfMeasure("UN");
        item.setUnitPrice(BigDecimal.valueOf(125.34));
        order.getItems().add(item);
    }

    @Test
    public void testOrderGetAll() throws Exception {
        List<OrderCieloEntity> resultList = repository.orderGetAll();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList);
        System.out.println(jsonInString);
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
