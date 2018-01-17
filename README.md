# Cielo LIO Payment Remote Java Client (Open Source)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.frekele.cielo/cielo-lio-remote-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.frekele.cielo/cielo-lio-remote-client) 
[![Build Status](https://travis-ci.org/frekele/cielo-lio-remote-client.svg?branch=master)](https://travis-ci.org/frekele/cielo-lio-remote-client)
[![Coverage](https://codecov.io/gh/frekele/cielo-lio-remote-client/branch/master/graph/badge.svg)](https://codecov.io/gh/frekele/cielo-lio-remote-client)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8e49f3317817425abe7f67b02c590f1b)](https://www.codacy.com/app/frekele/cielo-lio-remote-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=frekele/cielo-lio-remote-client&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/8e49f3317817425abe7f67b02c590f1b)](https://www.codacy.com/app/frekele/cielo-lio-remote-client?utm_source=github.com&utm_medium=referral&utm_content=frekele/cielo-lio-remote-client&utm_campaign=Badge_Coverage)

### Remote integration with Cielo LIO Payment Order Management

Built-based on the documentation: [https://developercielo.github.io/manual/cielo-lio](https://developercielo.github.io/manual/cielo-lio)

Project built with RESTEasy 3.1.x for use in Desktop and Web Applications.


#### Supported Java Versions:
- requires Java 8 (or higher) at runtime.


#### Maven dependency:
```xml
<dependency>
    <groupId>org.frekele.cielo</groupId>
    <artifactId>cielo-lio-remote-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle dependency:
```gradle
compile 'org.frekele.cielo:cielo-lio-remote-client:1.0.0'
```

#### Usage

```java
public class MyService {
    
    public void call() {
        //First create CieloLioAuth
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        String environment = System.getenv("CIELO_LIO_ENVIRONMENT");
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);

        //Build one client per thread, or use CDI Injection.
        ResteasyClient client = new ResteasyClientBuilder().build();
        CieloLioPaymentRepository repository = new CieloLioPaymentRepositoryImpl(client, auth);

        List<OrderEntity> resultList = repository.orderGetAll();
        OrderEntity orderEntity = repository.orderGet(new OrderId("5f182dec98-1866-47b0-b69d-471448911f"));

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
        return new CieloLioAuth(clientId, accessToken, merchantId, environment);
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
        List<OrderEntity> resultList = repository.orderGetAll();
        OrderEntity orderEntity = repository.orderGet(new OrderId("5f182dec98-1866-47b0-b69d-471448911f"));
    }
}

```

### Example usage

#### Post Order
```java
OrderEntity order = new OrderEntity();
order.setStatus(OrderStatusEnum.DRAFT);
order.setNumber("12345");
order.setReference("Order #12345");
order.setNotes("Consumer Jim Jonson");
order.setPrice(BigDecimal.valueOf(325.34));
order.setItems(new ArrayList<>());
OrderItemEntity item = new OrderItemEntity();
item.setSku("RTG-234-AQF-6587-C57");
item.setName("White Dining Table");
item.setQuantity(1);
item.setUnitOfMeasure("EACH");
item.setUnitPrice(BigDecimal.valueOf(325.34));
order.getItems().add(item);

//Post
OrderItemId orderId = repository.orderPost(order);
```

#### Put Order
```java
//Change or ad more info.
order.setNotes("Consumer Edward Anthony");

//Put
repository.orderPut(orderId, order);
```

#### Delete Order
```java
repository.orderDelete(orderId);
```

#### Get Order
```java
OrderEntity orderResult = repository.orderGet(orderId);
```

#### Get Orders By Number
```java
List<OrderEntity> resultList = repository.orderGetByNumber("12345");
```

#### Get Orders By Reference
```java
List<OrderEntity> resultList = repository.orderGetByReference("Order #12345");
```

#### Get Orders By Status
```java
List<OrderEntity> resultList = repository.orderGetByStatus(OrderStatusEnum.ENTERED);
```

#### Get All Orders
```java
List<OrderEntity> resultList = repository.orderGetAll();
```


#### Post Order Item
```java
OrderItemEntity orderItem = new OrderItemEntity();
orderItem.setSku("XPT-456-564-34554-3453");
orderItem.setName("White Wood Chair");
orderItem.setQuantity(4);
orderItem.setUnitOfMeasure("EACH");
orderItem.setUnitPrice(BigDecimal.valueOf(103.10));

//Post
OrderItemId orderItemId = repository.orderPostItem(orderId, orderItem);
```

#### Put Order Item
```java
//Change or add more info.
orderItem.setName("Black Dining Table");
orderItem.setDescription("Black dining table for sharing meals and being together!");

//Put
repository.orderPutItem(orderId, orderItemId, orderItem);
```

#### Delete Order Item
```java
repository.orderDeleteItem(orderId, orderItemId);
```

#### Get Order Item
```java
OrderEntity orderResult = repository.orderGetItem(orderId, orderItemId);
```

#### Get Order Items
```java
OrderEntity orderResult = repository.orderGetItems(orderId);
```


#### Post Order Transaction (Only in SandBox)
```java
OrderTransactionEntity orderTransaction = new OrderTransactionEntity();
orderTransaction.setStatus(TransactionStatusEnum.CONFIRMED);
orderTransaction.setTerminalNumber((long) 12345678);
orderTransaction.setAuthorizationCode((long) 3458619);
orderTransaction.setNumber((long) 672836);
orderTransaction.setAmount(BigDecimal.valueOf(325.34));
orderTransaction.setTransactionType(TransactionTypeEnum.PAYMENT);

OrderPaymentProductEntity orderPaymentProduct = new OrderPaymentProductEntity();
orderPaymentProduct.setPrimaryProductName("CREDITO");
orderPaymentProduct.setSecondaryProductName("A VISTA");
orderPaymentProduct.setNumberOfQuotas(0);
orderTransaction.setOrderPaymentProduct(orderPaymentProduct);

OrderCardEntity orderCard = new OrderCardEntity();
orderCard.setBrand(CardBrandEnum.VISA.getValue());
orderCard.setMask("************5487");
orderTransaction.setCard(orderCard);

//Post
OrderTransactionId orderTransactionId = repository.orderPostTransaction(orderId, orderTransaction);
```

#### Get Order Transaction
```java
OrderTransactionEntity orderTransactionEntity = repository.orderGetTransaction(orderId, orderTransactionId);
```

#### Get Order Transactions
```java
List<OrderTransactionEntity> resultList = repository.orderGetTransactions(orderId);
```


#### Change status of an Order
```java
//PLACE
repository.orderPutOperation(orderId, OperationEnum.PLACE);

//PAY
repository.orderPutOperation(orderId, OperationEnum.PAY);

//CLOSE
repository.orderPutOperation(orderId, OperationEnum.CLOSE);
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
    List<OrderEntity> orderGetAll();

    /**
     * GET - Consultar todos os Pedidos pelo id(UUID) do pedido
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager com filtro por number.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders?number=1234567890
     */
    List<OrderEntity> orderGetByNumber(String number);

    /**
     * GET - Consultar todos os Pedidos pela Referência do pedido
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager com filtro por reference.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders?reference=384769536
     */
    List<OrderEntity> orderGetByReference(String reference);

    /**
     * GET - Consultar todos os Pedidos pela Status do pedido
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager com filtro por status.
     * (DRAFT, ENTERED, RE-ENTERED, PAID, CANCELED e CLOSED).
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders?status=PAID
     */
    List<OrderEntity> orderGetByStatus(OrderStatusEnum status);

    /**
     * GET - Consultar pedido
     * Esse recurso é utilizado para obter informações sobre um pedido específico. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f
     */
    OrderEntity orderGet(OrderId orderId);

    /**
     * POST - Criar um pedido
     * Esse recurso realiza a criação de um pedido no servidor do Order Manager.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders
     */
    OrderId orderPost(OrderEntity order);

    /**
     * PUT - Altera um pedido
     * Esse recurso realiza a alteracao de um pedido no servidor do Order Manager.
     * Exemplo de requisição: PUT https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f
     */
    void orderPut(OrderId orderId, OrderEntity order);

    /**
     * PUT - Alterar status de um pedido
     * Esse recurso realiza a alteração do status de um pedido criado. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: PUT https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f?operation=PLACE
     * As possíveis alterações de status são:
     * PLACE (Liberar um pedido para pagamento, exibir pedido na Cielo LIO)
     * PAY (Alterar um pedido para pago)
     * CLOSE (Fechar um pedido)
     */
    void orderPutOperation(OrderId orderId, OperationEnum operation);

    /**
     * DELETE - Excluir um pedido
     * Esse recurso é utilizado para excluir um pedido do servidor do Order Manager. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: DELETE https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f
     */
    void orderDelete(OrderId orderId);

    /**
     * GET - Consultar os itens de um pedido
     * Esse recurso é utilizado para consultar os itens presentes em um pedido. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items
     */
    List<OrderItemEntity> orderGetItems(OrderId orderId);

    /**
     * GET - Consultar o item de um pedido
     * Esse recurso é utilizado para consultar um item presente em um pedido. O id do pedido e o id_item são utilizados para realizar a chamada.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items/dce7def3-72b2-40a0-91a1-096eed18eab3
     */
    OrderItemEntity orderGetItem(OrderId orderId, OrderItemId idItem);

    /**
     * POST - Adicionar Item/Itens em um Pedido
     * Esse recurso é utilizado para adicionar um ou mais itens em um pedido já criado. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items
     */
    OrderItemId orderPostItem(OrderId orderId, OrderItemEntity orderItem);

    /**
     * PUT - Alterar um item em um pedido
     * Esse recurso permite alterar informações de um item de um pedido. O id do pedido e o id_item são utilizados para realizar a chamada.
     * Exemplo de requisição: PUT https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items/dce7def3-72b2-40a0-91a1-096eed18eab3
     */
    void orderPutItem(OrderId orderId, OrderItemId idItem, OrderItemEntity orderItem);

    /**
     * DELETE - Excluir Item de um pedido
     * Esse recurso é utilizado para excluir um item presente em um pedido. O id do pedido e o id_item são utilizados para realizar a chamada.
     * Exemplo de requisição: DELETE https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items/dce7def3-72b2-40a0-91a1-096eed18eab3
     */
    void orderDeleteItem(OrderId orderId, OrderItemId idItem);

    /**
     * GET - Consultar as transações de um pedido
     * Esse recurso é utilizado para obter as informações de todas as transações realizadas em um pedido.
     * O id do pedido é utilizado para realizar a chamada. Em ambiente de produção, uma vez que um pagamento for realizado na Cielo LIO
     * a transactions será adicionada automaticamente no pedido e então, será possível obter as informações do pagamento realizado a partir da chamada deste recurso.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions
     */
    List<OrderTransactionEntity> orderGetTransactions(OrderId orderId);

    /**
     * GET - Consultar a transação de um pedido
     * Esse recurso é utilizado para obter as informações de uma transação realizada em um pedido.
     * O id do pedido e o id_transaction são utilizados para realizar a chamada. Em ambiente de produção, uma vez que um pagamento for realizado na Cielo LIO
     * a transactions será adicionada automaticamente no pedido e então, será possível obter as informações do pagamento realizado a partir da chamada deste recurso.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions/362d1e9e-ae99-4d79-87d6-add9aad2795c
     */
    OrderTransactionEntity orderGetTransaction(OrderId orderId, OrderTransactionId transactionId);

    /**
     * POST - Adicionar uma Transação (Recurso utilizado somente para Ambiente de Sandbox)
     * Esse recurso permite que o desenvolvedor simule as transações financeiras, adicionando-as manualmente, sendo possível entender o funcionamento em uma Order.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions
     */
    OrderTransactionId orderPostTransaction(OrderId orderId, OrderTransactionEntity transaction);
}
```

### Cielo Lio Order Management - Travis CI Tests - Push Notification (Captured HTTP requests)
- https://hookbin.com/bin/Z8abPwXo


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
