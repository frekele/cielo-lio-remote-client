package org.frekele.cielo.lio.remote.client.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.EnvironmentCieloLioEnum;
import org.frekele.cielo.lio.remote.client.enumeration.CardBrandEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OperationEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionStatusEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionTypeEnum;
import org.frekele.cielo.lio.remote.client.model.Order;
import org.frekele.cielo.lio.remote.client.model.OrderCard;
import org.frekele.cielo.lio.remote.client.model.OrderItem;
import org.frekele.cielo.lio.remote.client.model.OrderPaymentProduct;
import org.frekele.cielo.lio.remote.client.model.OrderTransaction;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Listeners(InvokedMethodListener.class)
public class CieloLioPaymentRepositoryIT {

    private CieloLioPaymentRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    String numberOrder;

    String referenceOrder;

    private String idOrder;

    private Order order;

    private String idOrderItem2;

    private OrderItem orderItem2;

    private OrderTransaction orderTransaction;

    private String idOorderTransaction;

    @BeforeClass
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        EnvironmentCieloLioEnum environment = EnvironmentCieloLioEnum.SANDBOX;
        CieloLioAuth auth = CieloLioAuth.newBuilder()
            .withClientId(clientId)
            .withAccessToken(accessToken)
            .withMerchantId(merchantId)
            .withEnvironment(environment)
            .build();
        ResteasyClient client = new ResteasyClientBuilder().build();
        repository = new CieloLioPaymentRepositoryImpl(client, auth);

        numberOrder = "" + new Random().nextInt(6);
        referenceOrder = "Order #" + numberOrder;
        OrderItem item = OrderItem.newBuilder()
            .withSku("RTG-234-AQF-6587-C57")
            .withName("White Dining Table")
            .withQuantity(1)
            .withUnitOfMeasure("EACH")
            .withUnitPrice(BigDecimal.valueOf(325.34))
            .build();

        order = Order.newBuilder()
            .withStatus(OrderStatusEnum.DRAFT)
            .withNumber(numberOrder)
            .withReference(referenceOrder)
            .withNotes("Consumer Jim Jonson")
            .withPrice(BigDecimal.valueOf(325.34))
            .addItem(item)
            .build();

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
        idOrder = repository.orderPost(order);
        System.out.println("idOrder:" + idOrder);
    }

    @Test(dependsOnMethods = "testOrderPost")
    public void testOrderPut() throws Exception {
        order.setNotes("Consumer Edward Anthony");
        repository.orderPut(idOrder, order);
    }

    @Test(dependsOnMethods = "testOrderPut")
    public void testOrderPostItem() throws Exception {
        orderItem2 = OrderItem.newBuilder()
            .withSku("XPT-456-564-34554-3453")
            .withName("White Wood Chair")
            .withQuantity(4)
            .withUnitOfMeasure("EACH")
            .withUnitPrice(BigDecimal.valueOf(103.10))
            .build();

        order.getItems().add(orderItem2);
        BigDecimal orderPrice = orderItem2.getUnitPrice().multiply(BigDecimal.valueOf(orderItem2.getQuantity())).add(order.getPrice());
        order.setPrice(orderPrice);
        System.out.println("new OrderItem");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderItem2));

        idOrderItem2 = repository.orderPostItem(idOrder, orderItem2);
        System.out.println("idOrder:" + idOrderItem2);
    }

    @Test(dependsOnMethods = "testOrderPostItem")
    public void testOrderPutItem() throws Exception {
        orderItem2.setUnitOfMeasure("CX");
        repository.orderPutItem(idOrder, idOrderItem2, orderItem2);
    }

    @Test(dependsOnMethods = "testOrderPutItem")
    public void testOrderGetItem() throws Exception {
        OrderItem orderItemResult = repository.orderGetItem(idOrder, idOrderItem2);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderItemResult));
    }

    @Test(dependsOnMethods = "testOrderGetItem")
    public void testOrderGetItems() throws Exception {
        List<OrderItem> resultList = repository.orderGetItems(idOrder);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetItems")
    public void testOrderDeleteItem() throws Exception {
        repository.orderDeleteItem(idOrder, idOrderItem2);
    }

    @Test(dependsOnMethods = "testOrderDeleteItem")
    public void testOrderPutOperation() throws Exception {
        repository.orderPutOperation(idOrder, OperationEnum.PLACE);
    }

    @Test(dependsOnMethods = "testOrderPutOperation")
    public void testOrderGet() throws Exception {
        Order orderResult = repository.orderGet(idOrder);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderResult));
    }

    @Test(dependsOnMethods = "testOrderGet")
    public void testOrderPostTransaction() throws Exception {

        OrderPaymentProduct orderPaymentProduct = OrderPaymentProduct.newBuilder()
            .withPrimaryProductName("CREDITO")
            .withSecondaryProductName("A VISTA")
            .withNumberOfQuotas(0)
            .build();

        OrderCard orderCard = OrderCard.newBuilder()
            .withBrand(CardBrandEnum.VISA.getValue())
            .withMask("************5487")
            .build();

        OrderTransaction orderTransaction = OrderTransaction.newBuilder()
            .withStatus(TransactionStatusEnum.CONFIRMED)
            .withTerminalNumber((long) 12345678)
            .withAuthorizationCode((long) 3458619)
            .withNumber((long) 672836)
            .withAmount(BigDecimal.valueOf(325.34))
            .withTransactionType(TransactionTypeEnum.PAYMENT)
            .withPaymentProduct(orderPaymentProduct)
            .withCard(orderCard)
            .build();

        System.out.println("new OrderTransaction");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderTransaction));

        idOorderTransaction = repository.orderPostTransaction(idOrder, orderTransaction);
        System.out.println("idOrder:" + idOorderTransaction);
    }

    @Test(dependsOnMethods = "testOrderPostTransaction")
    public void testOrderGetTransaction() throws Exception {
        OrderTransaction orderTransaction = repository.orderGetTransaction(idOrder, idOorderTransaction);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderTransaction));
    }

    @Test(dependsOnMethods = "testOrderGetTransaction")
    public void testOrderGetTransactions() throws Exception {
        List<OrderTransaction> resultList = repository.orderGetTransactions(idOrder);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetTransactions")
    public void testOrderGetByNumber() throws Exception {
        List<Order> resultList = repository.orderGetByNumber(numberOrder);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testOrderGetByNumber")
    public void testOrderPutOperationPay() throws Exception {
        repository.orderPutOperation(idOrder, OperationEnum.PAY);
    }

    @Test(dependsOnMethods = "testOrderPutOperationPay")
    public void testOrderPutOperationClose() throws Exception {
        repository.orderPutOperation(idOrder, OperationEnum.CLOSE);
    }

    @Test(dependsOnMethods = "testOrderPutOperationClose")
    public void testOrderPutOperationReOpenPay() throws Exception {
        repository.orderPutOperation(idOrder, OperationEnum.PAY);
    }

    @Test(dependsOnMethods = "testOrderPutOperationReOpenPay")
    public void testOrderPutOperationCloseAgain() throws Exception {
        repository.orderPutOperation(idOrder, OperationEnum.CLOSE);
    }

    @Test(dependsOnMethods = "testOrderPutOperationCloseAgain")
    public void testOrderGetByReference() throws Exception {
        List<Order> resultList = repository.orderGetByReference(referenceOrder);
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
        repository.orderDelete(idOrder);
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
