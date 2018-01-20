# Cielo LIO Payment Remote Java Client (Open Source)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.frekele.cielo/cielo-lio-remote-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.frekele.cielo/cielo-lio-remote-client)
[![Javadocs](http://www.javadoc.io/badge/org.frekele.cielo/cielo-lio-remote-client.svg?color=blue)](http://www.javadoc.io/doc/org.frekele.cielo/cielo-lio-remote-client)
[![Build Status](https://travis-ci.org/frekele/cielo-lio-remote-client.svg?branch=master)](https://travis-ci.org/frekele/cielo-lio-remote-client)
[![Coverage](https://codecov.io/gh/frekele/cielo-lio-remote-client/branch/master/graph/badge.svg)](https://codecov.io/gh/frekele/cielo-lio-remote-client)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8e49f3317817425abe7f67b02c590f1b)](https://www.codacy.com/app/frekele/cielo-lio-remote-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=frekele/cielo-lio-remote-client&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/8e49f3317817425abe7f67b02c590f1b)](https://www.codacy.com/app/frekele/cielo-lio-remote-client?utm_source=github.com&utm_medium=referral&utm_content=frekele/cielo-lio-remote-client&utm_campaign=Badge_Coverage)

### Remote integration with Cielo LIO Payment Order Management

![Cielo Lio V1 V2](https://raw.githubusercontent.com/frekele/cielo-lio-remote-client/master/docs/img/lio-v1-v2.png?v=2)

Built-based on the documentation: [https://developercielo.github.io/manual/cielo-lio](https://developercielo.github.io/manual/cielo-lio)

Project built with RESTEasy 3.1.x for use in Desktop and Web Applications.


#### Supported Java Versions:
- requires Java 8 (or higher) at runtime.


#### Maven dependency:
```xml
<dependency>
    <groupId>org.frekele.cielo</groupId>
    <artifactId>cielo-lio-remote-client</artifactId>
    <version>1.1.0</version>
</dependency>
```

#### Gradle dependency:
```gradle
compile 'org.frekele.cielo:cielo-lio-remote-client:1.1.0'
```

#### Usage

```java
public class MyService {
    
    public void call() {
        //First create CieloLioAuth
        Properties prop = // read Properties
        String clientId = prop.getProperty("clientId");
        String accessToken = prop.getProperty("accessToken");
        String merchantId = prop.getProperty("merchantId");
        String environment = prop.getProperty("environment");
        CieloLioAuth auth = CieloLioAuth.newBuilder()
            .withClientId(clientId)
            .withAccessToken(accessToken)
            .withMerchantId(merchantId)
            .withEnvironment(environment)
            .build();

        //Build one client per thread, or use CDI Injection.
        ResteasyClient client = new ResteasyClientBuilder().build();
        CieloLioPaymentRepository repository = new CieloLioPaymentRepositoryImpl(client, auth);

        List<Order> resultList = repository.orderGetAll();
        Order order = repository.orderGet("5f182dec98-1866-47b0-b69d-471448911f");

        //Is important to close in end, or use CDI.
        client.close();
    }
}
```

#### Usage with CDI (Contexts and Dependency Injection)

```java
public class CieloLioProducer {

    @Produces
    @CieloLio
    public CieloLioAuth producesCieloLioAuth() {
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        String environment = System.getenv("CIELO_LIO_ENVIRONMENT");
        return CieloLioAuth.newBuilder()
            .withClientId(clientId)
            .withAccessToken(accessToken)
            .withMerchantId(merchantId)
            .withEnvironment(environment)
            .build();
    }

    @Produces
    @CieloLio
    public ResteasyClient producesResteasyClient() {
        ResteasyClient client = new ResteasyClientBuilder()
                //Add proxy
                //.defaultProxy("192.168.56.67", 3456)
                //Change connection Pool size.
                //.connectionPoolSize(3)
                //Change connection TTL.
                //.connectionTTL(30, TimeUnit.MINUTES)
                .build();
        return client;
    }

    public void closeResteasyClient(@Disposes @CieloLio ResteasyClient client) {
        client.close();
    }
}

//Then you just need to @inject.
public class MyService {

    @Inject
    @CieloLio
    private CieloLioPaymentRepository repository;

    public void call() {
        List<Order> resultList = repository.orderGetAll();
        Order order = repository.orderGet("5f182dec98-1866-47b0-b69d-471448911f");
    }
}

```

### Example usage

#### Post Order
```java
//Build item
OrderItem item = OrderItem.newBuilder()
     .withSku("RTG-234-AQF-6587-C57")
     .withName("White Dining Table")
     .withQuantity(1)
     .withUnitOfMeasure("EACH")
     .withUnitPrice(BigDecimal.valueOf(325.34))
     .build();

//Build order and add item.
Order order = Order.newBuilder()
    .withStatus(OrderStatusEnum.DRAFT)
    .withNumber("12345")
    .withReference("Order #12345")
    .withNotes("Consumer Jim Jonson")
    .withPrice(BigDecimal.valueOf(325.34))
    .addItem(item)
    .build();

//Post
String idOrder = repository.orderPost(order);
```

#### Put Order
```java
//Change or ad more info.
order.setNotes("Consumer Edward Anthony");

//Put
repository.orderPut(idOrder, order);
```

#### Delete Order
```java
repository.orderDelete(idOrder);
```

#### Get Order
```java
Order orderResult = repository.orderGet(idOrder);
```

#### Get Orders By Number
```java
List<Order> resultList = repository.orderGetByNumber("12345");
```

#### Get Orders By Reference
```java
List<Order> resultList = repository.orderGetByReference("Order #12345");
```

#### Get Orders By Status
```java
List<Order> resultList = repository.orderGetByStatus(OrderStatusEnum.ENTERED);
```

#### Get All Orders
```java
List<Order> resultList = repository.orderGetAll();
```


#### Post Order Item
```java
//Build item
OrderItem orderItem = OrderItem.newBuilder()
     .withSku("XPT-456-564-34554-3453")
     .withName("White Wood Chair")
     .withQuantity(4)
     .withUnitOfMeasure("EACH")
     .withUnitPrice(BigDecimal.valueOf(103.10))
     .build();

//Post
String idOrderItem = repository.orderPostItem(idOrder, orderItem);
```

#### Put Order Item
```java
//Change or add more info.
orderItem.setName("Black Dining Table");
orderItem.setDescription("Black dining table for sharing meals and being together!");

//Put
repository.orderPutItem(idOrder, idOrderItem, orderItem);
```

#### Delete Order Item
```java
repository.orderDeleteItem(idOrder, idOrderItem);
```

#### Get Order Item
```java
Order orderResult = repository.orderGetItem(idOrder, idOrderItem);
```

#### Get Order Items
```java
Order orderResult = repository.orderGetItems(idOrder);
```


#### Post Order Transaction (Only in SandBox)
```java
//Build paymentProduct
OrderPaymentProduct orderPaymentProduct = OrderPaymentProduct.newBuilder()
    .withPrimaryProductName("CREDITO")
    .withSecondaryProductName("A VISTA")
    .withNumberOfQuotas(0)
    .build();

//Build card
OrderCard orderCard = OrderCard.newBuilder()
    .withBrand(CardBrandEnum.VISA.getValue())
    .withMask("************5487")
    .build();

//Build transaction
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

//Post
String idOrderTransaction = repository.orderPostTransaction(idOrder, orderTransaction);
```

#### Get Order Transaction
```java
OrderTransaction orderTransaction = repository.orderGetTransaction(idOrder, idOrderTransaction);
```

#### Get Order Transactions
```java
List<OrderTransaction> resultList = repository.orderGetTransactions(idOrder);
```


#### Change status of an Order
```java
//PLACE
repository.orderPutOperation(idOrder, OperationEnum.PLACE);

//PAY
repository.orderPutOperation(idOrder, OperationEnum.PAY);

//CLOSE
repository.orderPutOperation(idOrder, OperationEnum.CLOSE);
```


### Order Status Lifecycle
![Order Status Lifecycle](https://raw.githubusercontent.com/frekele/cielo-lio-remote-client/master/docs/img/lifecycle.png)



### CieloLioPaymentRepository Interface.

```java
public interface CieloLioPaymentRepository extends Serializable {

    /**
     * GET - Consultar todos os Pedidos
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders
     */
    List<Order> orderGetAll();

    /**
     * GET - Consultar todos os Pedidos pelo id(UUID) do pedido
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager com filtro por number.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders?number=1234567890
     */
    List<Order> orderGetByNumber(String number);

    /**
     * GET - Consultar todos os Pedidos pela Referência do pedido
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager com filtro por reference.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders?reference=384769536
     */
    List<Order> orderGetByReference(String reference);

    /**
     * GET - Consultar todos os Pedidos pela Status do pedido
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager com filtro por status.
     * (DRAFT, ENTERED, RE-ENTERED, PAID, CANCELED e CLOSED).
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders?status=PAID
     */
    List<Order> orderGetByStatus(OrderStatusEnum status);

    /**
     * GET - Consultar pedido
     * Esse recurso é utilizado para obter informações sobre um pedido específico. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f
     */
    Order orderGet(String idOrder);

    /**
     * POST - Criar um pedido
     * Esse recurso realiza a criação de um pedido no servidor do Order Manager.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders
     */
    String orderPost(Order order);

    /**
     * PUT - Altera um pedido
     * Esse recurso realiza a alteracao de um pedido no servidor do Order Manager.
     * Exemplo de requisição: PUT https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f
     */
    void orderPut(String idOrder, Order order);

    /**
     * PUT - Alterar status de um pedido
     * Esse recurso realiza a alteração do status de um pedido criado. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: PUT https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f?operation=PLACE
     * As possíveis alterações de status são:
     * PLACE (Liberar um pedido para pagamento, exibir pedido na Cielo LIO)
     * PAY (Alterar um pedido para pago)
     * CLOSE (Fechar um pedido)
     */
    void orderPutOperation(String idOrder, OperationEnum operation);

    /**
     * DELETE - Excluir um pedido
     * Esse recurso é utilizado para excluir um pedido do servidor do Order Manager. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: DELETE https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f
     */
    void orderDelete(String idOrder);

    /**
     * GET - Consultar os itens de um pedido
     * Esse recurso é utilizado para consultar os itens presentes em um pedido. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items
     */
    List<OrderItem> orderGetItems(String idOrder);

    /**
     * GET - Consultar o item de um pedido
     * Esse recurso é utilizado para consultar um item presente em um pedido. O id do pedido e o id_item são utilizados para realizar a chamada.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items/dce7def3-72b2-40a0-91a1-096eed18eab3
     */
    public OrderItem orderGetItem(String idOrder, String idOrderItem);

    /**
     * POST - Adicionar Item/Itens em um Pedido
     * Esse recurso é utilizado para adicionar um ou mais itens em um pedido já criado. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items
     */
    String orderPostItem(String idOrder, OrderItem orderItem);

    /**
     * PUT - Alterar um item em um pedido
     * Esse recurso permite alterar informações de um item de um pedido. O id do pedido e o id_item são utilizados para realizar a chamada.
     * Exemplo de requisição: PUT https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items/dce7def3-72b2-40a0-91a1-096eed18eab3
     */
    void orderPutItem(String idOrder, String idOrderItem, OrderItem orderItem);

    /**
     * DELETE - Excluir Item de um pedido
     * Esse recurso é utilizado para excluir um item presente em um pedido. O id do pedido e o id_item são utilizados para realizar a chamada.
     * Exemplo de requisição: DELETE https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items/dce7def3-72b2-40a0-91a1-096eed18eab3
     */
    void orderDeleteItem(String idOrder, String idOrderItem);

    /**
     * GET - Consultar as transações de um pedido
     * Esse recurso é utilizado para obter as informações de todas as transações realizadas em um pedido.
     * O id do pedido é utilizado para realizar a chamada. Em ambiente de produção, uma vez que um pagamento for realizado na Cielo LIO
     * a transactions será adicionada automaticamente no pedido e então, será possível obter as informações do pagamento realizado a partir da chamada deste recurso.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions
     */
    List<OrderTransaction> orderGetTransactions(String idOrder);

    /**
     * GET - Consultar a transação de um pedido
     * Esse recurso é utilizado para obter as informações de uma transação realizada em um pedido.
     * O id do pedido e o id_transaction são utilizados para realizar a chamada. Em ambiente de produção, uma vez que um pagamento for realizado na Cielo LIO
     * a transactions será adicionada automaticamente no pedido e então, será possível obter as informações do pagamento realizado a partir da chamada deste recurso.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions/362d1e9e-ae99-4d79-87d6-add9aad2795c
     */
    OrderTransaction orderGetTransaction(String idOrder, String idOrderTransaction);

    /**
     * POST - Adicionar uma Transação (Recurso utilizado somente para Ambiente de Sandbox)
     * Esse recurso permite que o desenvolvedor simule as transações financeiras, adicionando-as manualmente, sendo possível entender o funcionamento em uma Order.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions
     */
    String orderPostTransaction(String idOrder, OrderTransaction orderTransaction);
}
```

### Cielo Lio Order Management - Travis CI Tests - Push Notification (Captured HTTP requests)
- https://hookbin.com/bin/Z8abPwXo


#### Compile with Maven:
```
mvn clean install -Dgpg.skip
```

#### Compile with integration Tests:
You need to add the environment variables to run the integration tests.
 - CIELO_LIO_CLIENT_ID    = //Your Client-Id.
 - CIELO_LIO_ACCESS_TOKEN = //Your Access-Token
 - CIELO_LIO_MERCHANT_ID  = //Your Merchant-Id
```
mvn clean install -Dgpg.skip -DskipITs=false
```


frekele/cielo-lio-remote-client is **licensed** under the **[MIT License]**. The terms of the license are as follows:

    MIT License
    
    Copyright (c) 2018 @frekele<Leandro Kersting de Freitas>
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
