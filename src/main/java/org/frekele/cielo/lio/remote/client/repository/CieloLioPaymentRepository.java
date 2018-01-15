package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.enumeration.OperationEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.model.id.OrderId;
import org.frekele.cielo.lio.remote.client.model.OrderEntity;
import org.frekele.cielo.lio.remote.client.model.OrderItemEntity;
import org.frekele.cielo.lio.remote.client.model.OrderTransactionEntity;
import org.frekele.cielo.lio.remote.client.model.id.OrderItemId;

import java.io.Serializable;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
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
    OrderTransactionEntity orderGetTransactionById(OrderId orderId, String idTransaction);

    /**
     * POST - Adicionar uma Transação (Recurso utilizado somente para Ambiente de Sandbox)
     * Esse recurso permite que o desenvolvedor simule as transações financeiras, adicionando-as manualmente, sendo possível entender o funcionamento em uma Order.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions
     */
    OrderId orderPostTransaction(OrderId orderId, OrderTransactionEntity transaction);
}
