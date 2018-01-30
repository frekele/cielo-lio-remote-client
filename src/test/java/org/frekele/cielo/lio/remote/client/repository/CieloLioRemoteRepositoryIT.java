package org.frekele.cielo.lio.remote.client.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.frekele.cielo.lio.remote.client.auth.CieloLioAuth;
import org.frekele.cielo.lio.remote.client.auth.EnvironmentCieloLioEnum;
import org.frekele.cielo.lio.remote.client.enumeration.CardBrandEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OperationEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionStatusEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionTypeEnum;
import org.frekele.cielo.lio.remote.client.filter.RequestLoggingFilter;
import org.frekele.cielo.lio.remote.client.filter.ResponseLoggingFilter;
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
public class CieloLioRemoteRepositoryIT {

    private CieloLioRemoteRepository repository;

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
        ResteasyClient client = new ResteasyClientBuilder()
            .register(RequestLoggingFilter.class)
            .register(ResponseLoggingFilter.class)
            .build();
        repository = new CieloLioRemoteRepositoryImpl(client, auth);

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
    public void testCreateOrder() throws Exception {
        idOrder = repository.createOrder(order);
        System.out.println("idOrder:" + idOrder);
    }

    @Test(dependsOnMethods = "testCreateOrder")
    public void testUpdateOrder() throws Exception {
        order.setNotes("Consumer Edward Anthony");
        repository.updateOrder(idOrder, order);
    }

    @Test(dependsOnMethods = "testUpdateOrder")
    public void testCreateOrderItem() throws Exception {
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

        idOrderItem2 = repository.createOrderItem(idOrder, orderItem2);
        System.out.println("idOrder:" + idOrderItem2);
    }

    @Test(dependsOnMethods = "testCreateOrderItem")
    public void testUpdateOrderItem() throws Exception {
        orderItem2.setUnitOfMeasure("CX");
        repository.updateOrderItem(idOrder, idOrderItem2, orderItem2);
    }

    @Test(dependsOnMethods = "testUpdateOrderItem")
    public void testFindOrderItem() throws Exception {
        OrderItem orderItemResult = repository.findOrderItem(idOrder, idOrderItem2);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderItemResult));
    }

    @Test(dependsOnMethods = "testFindOrderItem")
    public void testFindOrderItems() throws Exception {
        List<OrderItem> resultList = repository.findOrderItems(idOrder);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testFindOrderItems")
    public void testDeleteOrderItem() throws Exception {
        repository.deleteOrderItem(idOrder, idOrderItem2);
    }

    @Test(dependsOnMethods = "testDeleteOrderItem")
    public void testUpdateOrderStatus() throws Exception {
        repository.updateOrderStatus(idOrder, OperationEnum.PLACE);
    }

    @Test(dependsOnMethods = "testUpdateOrderStatus")
    public void testFindOrder() throws Exception {
        Order orderResult = repository.findOrder(idOrder);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderResult));
    }

    @Test(dependsOnMethods = "testFindOrder")
    public void testCreateOrderTransaction() throws Exception {

        OrderPaymentProduct orderPaymentProduct = OrderPaymentProduct.newBuilder()
            .withPrimaryProductName("CREDITO")
            .withSecondaryProductName("A VISTA")
            .withNumberOfQuotas(0)
            .build();

        OrderCard orderCard = OrderCard.newBuilder()
            .withBrand(CardBrandEnum.VISA.getValue())
            .withMask("************5487")
            .build();

        orderTransaction = OrderTransaction.newBuilder()
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

        idOorderTransaction = repository.createOrderTransaction(idOrder, orderTransaction);
        System.out.println("idOrder:" + idOorderTransaction);
    }

    @Test(dependsOnMethods = "testCreateOrderTransaction")
    public void testFindOrderTransaction() throws Exception {
        OrderTransaction orderTransaction = repository.findOrderTransaction(idOrder, idOorderTransaction);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderTransaction));
    }

    @Test(dependsOnMethods = "testFindOrderTransaction")
    public void testFindOrderTransactions() throws Exception {
        List<OrderTransaction> resultList = repository.findOrderTransactions(idOrder);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testFindOrderTransactions")
    public void testFindOrdersByNumber() throws Exception {
        List<Order> resultList = repository.findOrdersByNumber(numberOrder);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testFindOrdersByNumber")
    public void testUpdateOrderStatusPay() throws Exception {
        repository.updateOrderStatus(idOrder, OperationEnum.PAY);
    }

    @Test(dependsOnMethods = "testUpdateOrderStatusPay")
    public void testUpdateOrderStatusClose() throws Exception {
        repository.updateOrderStatus(idOrder, OperationEnum.CLOSE);
    }

    @Test(dependsOnMethods = "testUpdateOrderStatusClose")
    public void testUpdateOrderStatusReOpenPlace() throws Exception {
        repository.updateOrderStatus(idOrder, OperationEnum.PLACE);
    }

    @Test(dependsOnMethods = "testUpdateOrderStatusReOpenPlace")
    public void testUpdateOrderStatusReOpenPay() throws Exception {
        repository.updateOrderStatus(idOrder, OperationEnum.PAY);
    }

    @Test(dependsOnMethods = "testUpdateOrderStatusReOpenPay")
    public void testUpdateOrderStatusCloseAgain() throws Exception {
        repository.updateOrderStatus(idOrder, OperationEnum.CLOSE);
    }

    @Test(dependsOnMethods = "testUpdateOrderStatusCloseAgain")
    public void testFindOrdersByReference() throws Exception {
        List<Order> resultList = repository.findOrdersByReference(referenceOrder);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testFindOrdersByReference")
    public void testFindOrdersByStatus() throws Exception {
        List<Order> resultList = repository.findOrdersByStatus(OrderStatusEnum.ENTERED);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testFindOrdersByStatus")
    public void testFindOrders() throws Exception {
        List<Order> resultList = repository.findOrders();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList));
    }

    @Test(dependsOnMethods = "testFindOrders")
    public void testDeleteOrder() throws Exception {
        repository.deleteOrder(idOrder);
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
