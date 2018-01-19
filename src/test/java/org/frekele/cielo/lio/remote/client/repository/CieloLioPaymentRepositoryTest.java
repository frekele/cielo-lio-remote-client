package org.frekele.cielo.lio.remote.client.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.EnvironmentCieloLioEnum;
import org.frekele.cielo.lio.remote.client.enumeration.CardBrandEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OperationEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionStatusEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionTypeEnum;
import org.frekele.cielo.lio.remote.client.model.OrderCard;
import org.frekele.cielo.lio.remote.client.model.Order;
import org.frekele.cielo.lio.remote.client.model.OrderItem;
import org.frekele.cielo.lio.remote.client.model.OrderPaymentProduct;
import org.frekele.cielo.lio.remote.client.model.OrderTransaction;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
import org.frekele.cielo.lio.remote.client.model.id.OrderItemId;
import org.frekele.cielo.lio.remote.client.model.id.OrderTransactionId;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
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

    private OrderId orderId;

    private Order order;

    private OrderItemId orderItemId2;

    private OrderItem orderItem2;

    OrderTransaction orderTransaction;

    OrderTransactionId orderTransactionId;

    @BeforeClass
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        EnvironmentCieloLioEnum environment = EnvironmentCieloLioEnum.SANDBOX;
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);
        ResteasyClient client = new ResteasyClientBuilder().build();
        repository = new CieloLioPaymentRepositoryImpl(client, auth);

        order = new Order();
        order.setStatus(OrderStatusEnum.DRAFT);
        order.setNumber("12345");
        order.setReference("PEDIDO #12345");
        order.setNotes("Cliente Fulano de Tal");
        order.setPrice(BigDecimal.valueOf(325.34));
        order.setItems(new ArrayList<>());
        OrderItem item = new OrderItem();
        item.setSku("RTG-234-AQF-6587-C57");
        item.setName("Mesa de Formica Branca");
        item.setQuantity(1);
        item.setUnitOfMeasure("UN");
        item.setUnitPrice(BigDecimal.valueOf(325.34));
        order.getItems().add(item);

        System.out.println("new OrderCielo");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order));
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        //After Method Sleep 1 seg, for prevent (HTTP 429 Unknown Code).
        this.sleep(1);
    }

    @Test
    public void testOrderPost() throws Exception {
        orderId = repository.orderPost(order);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderId));
    }

    @Test(dependsOnMethods = "testOrderPost")
    public void testOrderPut() throws Exception {
        order.setNotes("Joao Da Silva");
        repository.orderPut(orderId, order);
    }

    @Test(dependsOnMethods = "testOrderPut")
    public void testOrderPostItem() throws Exception {
        orderItem2 = new OrderItem();
        orderItem2.setSku("XPT-456-564-34554-3453");
        orderItem2.setName("Cadeira de Madeira Branca");
        orderItem2.setQuantity(4);
        orderItem2.setUnitOfMeasure("UN");
        orderItem2.setUnitPrice(BigDecimal.valueOf(103.10));
        order.getItems().add(orderItem2);
        BigDecimal orderPrice = orderItem2.getUnitPrice().multiply(BigDecimal.valueOf(orderItem2.getQuantity())).add(order.getPrice());
        order.setPrice(orderPrice);
        System.out.println("new OrderItem");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderItem2));

        orderItemId2 = repository.orderPostItem(orderId, orderItem2);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderId));
    }

    @Test(dependsOnMethods = "testOrderPostItem")
    public void testOrderPutItem() throws Exception {
        orderItem2.setUnitOfMeasure("CX");
        repository.orderPutItem(orderId, orderItemId2, orderItem2);
    }

    @Test(dependsOnMethods = "testOrderPutItem")
    public void testOrderGetItem() throws Exception {
        OrderItem orderItemResult = repository.orderGetItem(orderId, orderItemId2);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderItemResult));
    }

    @Test(dependsOnMethods = "testOrderGetItem")
    public void testOrderGetItems() throws Exception {
        List<OrderItem> resultList = repository.orderGetItems(orderId);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetItems")
    public void testOrderDeleteItem() throws Exception {
        repository.orderDeleteItem(orderId, orderItemId2);
    }

    @Test(dependsOnMethods = "testOrderDeleteItem")
    public void testOrderPutOperation() throws Exception {
        repository.orderPutOperation(orderId, OperationEnum.PLACE);
    }

    @Test(dependsOnMethods = "testOrderPutOperation")
    public void testOrderGet() throws Exception {
        Order orderResult = repository.orderGet(orderId);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderResult));
    }

    @Test(dependsOnMethods = "testOrderGet")
    public void testOrderPostTransaction() throws Exception {
        orderTransaction = new OrderTransaction();
        orderTransaction.setStatus(TransactionStatusEnum.CONFIRMED);
        orderTransaction.setTerminalNumber((long) 12345678);
        orderTransaction.setAuthorizationCode((long) 3458619);
        orderTransaction.setNumber((long) 672836);
        orderTransaction.setAmount(BigDecimal.valueOf(325.34));
        orderTransaction.setTransactionType(TransactionTypeEnum.PAYMENT);

        OrderPaymentProduct orderPaymentProduct = new OrderPaymentProduct();
        orderPaymentProduct.setPrimaryProductName("CREDITO");
        orderPaymentProduct.setSecondaryProductName("A VISTA");
        orderPaymentProduct.setNumberOfQuotas(0);
        orderTransaction.setOrderPaymentProduct(orderPaymentProduct);

        OrderCard orderCard = new OrderCard();
        orderCard.setBrand(CardBrandEnum.VISA.getValue());
        orderCard.setMask("************5487");
        orderTransaction.setCard(orderCard);

        System.out.println("new OrderTransaction");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderTransaction));

        orderTransactionId = repository.orderPostTransaction(orderId, orderTransaction);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderTransactionId));
    }

    @Test(dependsOnMethods = "testOrderPostTransaction")
    public void testOrderGetTransaction() throws Exception {
        OrderTransaction orderTransaction = repository.orderGetTransaction(orderId, orderTransactionId);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderTransaction));
    }

    @Test(dependsOnMethods = "testOrderGetTransaction")
    public void testOrderGetTransactions() throws Exception {
        List<OrderTransaction> resultList = repository.orderGetTransactions(orderId);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetTransactions")
    public void testOrderGetByNumber() throws Exception {
        List<Order> resultList = repository.orderGetByNumber("12345");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByNumber")
    public void testOrderPutOperationPay() throws Exception {
        repository.orderPutOperation(orderId, OperationEnum.PAY);
    }

    @Test(dependsOnMethods = "testOrderPutOperationPay")
    public void testOrderPutOperationClose() throws Exception {
        repository.orderPutOperation(orderId, OperationEnum.CLOSE);
    }

    @Test(dependsOnMethods = "testOrderPutOperationClose")
    public void testOrderPutOperationReOpenPay() throws Exception {
        repository.orderPutOperation(orderId, OperationEnum.PAY);
    }

    @Test(dependsOnMethods = "testOrderPutOperationReOpenPay")
    public void testOrderPutOperationCloseAgain() throws Exception {
        repository.orderPutOperation(orderId, OperationEnum.CLOSE);
    }

    @Test(dependsOnMethods = "testOrderPutOperationCloseAgain")
    public void testOrderGetByReference() throws Exception {
        List<Order> resultList = repository.orderGetByReference("PEDIDO #12345");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByReference")
    public void testOrderGetByStatus() throws Exception {
        List<Order> resultList = repository.orderGetByStatus(OrderStatusEnum.ENTERED);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByStatus")
    public void testOrderGetAll() throws Exception {
        List<Order> resultList = repository.orderGetAll();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetAll")
    public void testOrderDelete() throws Exception {
        repository.orderDelete(orderId);
    }

    private void sleep(long seconds) {
        try {
            long millis = seconds * 1000;
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            // Restore interrupted state...
            Thread.currentThread().interrupt();
        }
    }
}
