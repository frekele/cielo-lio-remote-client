package org.frekele.cielo.lio.remote.client.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.frekele.cielo.lio.remote.client.InvokedMethodListener;
import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.EnvironmentCieloLioEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.model.IdEntity;
import org.frekele.cielo.lio.remote.client.model.OrderEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemEntity;
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

    private ObjectMapper mapper = new ObjectMapper();

    private OrderEntity order;

    private IdEntity idOrder;

    @BeforeClass
    public void init() throws Exception {
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        EnvironmentCieloLioEnum environment = EnvironmentCieloLioEnum.SANDBOX;
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);
        ResteasyClient client = new ResteasyClientBuilder().build();
        repository = new CieloLioPaymentRepositoryImpl(client, auth);

        order = new OrderEntity();
        order.setStatus(OrderStatusEnum.DRAFT);
        order.setNumber("12345");
        order.setReference("PEDIDO #12345");
        order.setNotes("Cliente Fulano de Tal");
        order.setPrice(BigDecimal.valueOf(125.34));
        order.setItems(new ArrayList<>());
        OrderItemEntity item = new OrderItemEntity();
        item.setSku("RTG-234-AQF-6587-C57");
        item.setName("Mesa de Formica Branca");
        item.setQuantity(1);
        item.setUnitOfMeasure("UN");
        item.setUnitPrice(BigDecimal.valueOf(125.34));
        order.getItems().add(item);

        System.out.println("new OrderCieloEntity");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order));
    }

    @Test
    public void testOrderPost() throws Exception {
        idOrder = repository.orderPost(order);
        System.out.println("before orderPost");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(idOrder));
    }

    @Test(dependsOnMethods = "testOrderPost")
    public void testOrderPut() throws Exception {
        order.setNotes("Joao Da Silva");
        repository.orderPut(idOrder.getId(), order);
    }

    @Test(dependsOnMethods = "testOrderPut")
    public void testOrderPutOperation() throws Exception {
        repository.orderPutOperation(idOrder.getId(), "PLACE");
    }

    @Test(dependsOnMethods = "testOrderPutOperation")
    public void testOrderGet() throws Exception {
        OrderEntity orderResult = repository.orderGet(idOrder.getId());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderResult));
    }

    @Test(dependsOnMethods = "testOrderGet")
    public void testOrderGetByNumber() throws Exception {
        List<OrderEntity> resultList = repository.orderGetByNumber("12345");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByNumber")
    public void testOrderGetByReference() throws Exception {
        List<OrderEntity> resultList = repository.orderGetByReference("PEDIDO #12345");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByReference")
    public void testOrderGetByStatus() throws Exception {
        List<OrderEntity> resultList = repository.orderGetByStatus(OrderStatusEnum.ENTERED);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByStatus")
    public void testOrderDelete() throws Exception {
        repository.orderDelete(idOrder.getId());
    }

    @Test(dependsOnMethods = "testOrderDelete")
    public void testOrderGetAll() throws Exception {
        List<OrderEntity> resultList = repository.orderGetAll();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

//    @Test
//    public void testOrderGetItems() throws Exception {
//    }
//
//    @Test
//    public void testOrderGetItem() throws Exception {
//    }
//
//    @Test
//    public void testOrderPostItem() throws Exception {
//    }
//
//    @Test
//    public void testOrderPutItem() throws Exception {
//    }
//
//    @Test
//    public void testOrderDeleteItem() throws Exception {
//    }
//
//    @Test
//    public void testOrderGetTransactions() throws Exception {
//    }
//
//    @Test
//    public void testOrderGetTransactionById() throws Exception {
//    }
//
//    @Test
//    public void testOrderPostTransaction() throws Exception {
//    }
}
