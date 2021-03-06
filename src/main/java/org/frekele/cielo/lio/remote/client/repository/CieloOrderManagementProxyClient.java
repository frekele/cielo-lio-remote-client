package org.frekele.cielo.lio.remote.client.repository;

import org.frekele.cielo.lio.remote.client.enumeration.OperationEnum;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;
import org.frekele.cielo.lio.remote.client.model.Order;
import org.frekele.cielo.lio.remote.client.model.OrderItem;
import org.frekele.cielo.lio.remote.client.model.OrderTransaction;
import org.frekele.cielo.lio.remote.client.model.id.ResponseId;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Path("/order-management/v1")
@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
@Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
interface CieloOrderManagementProxyClient extends Serializable {

    /**
     * GET - Consultar todos os Pedidos
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders
     */
    @GET
    @Path("orders")
    List<Order> findOrders(@HeaderParam("Client-Id") String clientId,
                           @HeaderParam("Access-Token") String accessToken,
                           @HeaderParam("Merchant-Id") String merchantId);

    /**
     * GET - Consultar todos os Pedidos pelo id(UUID) do pedido
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager com filtro por number.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders?number=1234567890
     */
    @GET
    @Path("orders")
    List<Order> findOrdersByNumber(@HeaderParam("Client-Id") String clientId,
                                   @HeaderParam("Access-Token") String accessToken,
                                   @HeaderParam("Merchant-Id") String merchantId,
                                   @QueryParam("number") String number);

    /**
     * GET - Consultar todos os Pedidos pela Referência do pedido
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager com filtro por reference.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders?reference=384769536
     */
    @GET
    @Path("orders")
    List<Order> findOrdersByReference(@HeaderParam("Client-Id") String clientId,
                                      @HeaderParam("Access-Token") String accessToken,
                                      @HeaderParam("Merchant-Id") String merchantId,
                                      @QueryParam("reference") String reference);

    /**
     * GET - Consultar todos os Pedidos pela Status do pedido
     * Esse recurso é utilizado para obter a lista e as informações de todos os pedidos cadastrados no Order Manager com filtro por status.
     * (DRAFT, ENTERED, RE-ENTERED, PAID, CANCELED e CLOSED).
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders?status=PAID
     */
    @GET
    @Path("orders")
    List<Order> findOrdersByStatus(@HeaderParam("Client-Id") String clientId,
                                   @HeaderParam("Access-Token") String accessToken,
                                   @HeaderParam("Merchant-Id") String merchantId,
                                   @QueryParam("status") OrderStatusEnum status);

    /**
     * GET - Consultar pedido
     * Esse recurso é utilizado para obter informações sobre um pedido específico. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f
     */
    @GET
    @Path("orders/{idOrder}")
    Order findOrder(@HeaderParam("Client-Id") String clientId,
                    @HeaderParam("Access-Token") String accessToken,
                    @HeaderParam("Merchant-Id") String merchantId,
                    @PathParam("idOrder") String idOrder);

    /**
     * POST - Criar um pedido
     * Esse recurso realiza a criação de um pedido no servidor do Order Manager.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders
     */
    @POST
    @Path("orders")
    ResponseId createOrder(@HeaderParam("Client-Id") String clientId,
                           @HeaderParam("Access-Token") String accessToken,
                           @HeaderParam("Merchant-Id") String merchantId,
                           Order order);

    /**
     * PUT - Altera um pedido
     * Esse recurso realiza a alteracao de um pedido no servidor do Order Manager.
     * Exemplo de requisição: PUT https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f
     */
    @PUT
    @Path("orders/{idOrder}")
    void updateOrder(@HeaderParam("Client-Id") String clientId,
                     @HeaderParam("Access-Token") String accessToken,
                     @HeaderParam("Merchant-Id") String merchantId,
                     @PathParam("idOrder") String idOrder,
                     Order order);

    /**
     * PUT - Alterar status de um pedido
     * Esse recurso realiza a alteração do status de um pedido criado. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: PUT https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f?operation=PLACE
     * As possíveis alterações de status são:
     * PLACE (Liberar um pedido para pagamento, exibir pedido na Cielo LIO)
     * PAY (Alterar um pedido para pago)
     * CLOSE (Fechar um pedido)
     */
    @PUT
    @Path("orders/{idOrder}")
    void updateOrderStatus(@HeaderParam("Client-Id") String clientId,
                           @HeaderParam("Access-Token") String accessToken,
                           @HeaderParam("Merchant-Id") String merchantId,
                           @PathParam("idOrder") String idOrder,
                           @QueryParam("operation") OperationEnum operation);

    /**
     * DELETE - Excluir um pedido
     * Esse recurso é utilizado para excluir um pedido do servidor do Order Manager. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: DELETE https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f
     */
    @DELETE
    @Path("orders/{idOrder}")
    void deleteOrder(@HeaderParam("Client-Id") String clientId,
                     @HeaderParam("Access-Token") String accessToken,
                     @HeaderParam("Merchant-Id") String merchantId,
                     @PathParam("idOrder") String idOrder);

    /**
     * GET - Consultar os itens de um pedido
     * Esse recurso é utilizado para consultar os itens presentes em um pedido. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items
     */
    @GET
    @Path("orders/{idOrder}/items")
    List<OrderItem> findOrderItems(@HeaderParam("Client-Id") String clientId,
                                   @HeaderParam("Access-Token") String accessToken,
                                   @HeaderParam("Merchant-Id") String merchantId,
                                   @PathParam("idOrder") String idOrder);

    /**
     * GET - Consultar o item de um pedido
     * Esse recurso é utilizado para consultar um item presente em um pedido. O id do pedido e o id_item são utilizados para realizar a chamada.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items/dce7def3-72b2-40a0-91a1-096eed18eab3
     */
    @GET
    @Path("orders/{idOrder}/items/{idOrderItem}")
    OrderItem findOrderItem(@HeaderParam("Client-Id") String clientId,
                            @HeaderParam("Access-Token") String accessToken,
                            @HeaderParam("Merchant-Id") String merchantId,
                            @PathParam("idOrder") String idOrder,
                            @PathParam("idOrderItem") String idOrderItem);

    /**
     * POST - Adicionar Item/Itens em um Pedido
     * Esse recurso é utilizado para adicionar um ou mais itens em um pedido já criado. O id do pedido é utilizado para realizar a chamada.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items
     */
    @POST
    @Path("orders/{idOrder}/items")
    ResponseId createOrderItem(@HeaderParam("Client-Id") String clientId,
                               @HeaderParam("Access-Token") String accessToken,
                               @HeaderParam("Merchant-Id") String merchantId,
                               @PathParam("idOrder") String idOrder,
                               OrderItem orderItem);

    /**
     * PUT - Alterar um item em um pedido
     * Esse recurso permite alterar informações de um item de um pedido. O id do pedido e o id_item são utilizados para realizar a chamada.
     * Exemplo de requisição: PUT https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items/dce7def3-72b2-40a0-91a1-096eed18eab3
     */
    @PUT
    @Path("orders/{idOrder}/items/{idOrderItem}")
    void updateOrderItem(@HeaderParam("Client-Id") String clientId,
                         @HeaderParam("Access-Token") String accessToken,
                         @HeaderParam("Merchant-Id") String merchantId,
                         @PathParam("idOrder") String idOrder,
                         @PathParam("idOrderItem") String idOrderItem,
                         OrderItem orderItem);

    /**
     * DELETE - Excluir Item de um pedido
     * Esse recurso é utilizado para excluir um item presente em um pedido. O id do pedido e o id_item são utilizados para realizar a chamada.
     * Exemplo de requisição: DELETE https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/items/dce7def3-72b2-40a0-91a1-096eed18eab3
     */
    @DELETE
    @Path("orders/{idOrder}/items/{idOrderItem}")
    void deleteOrderItem(@HeaderParam("Client-Id") String clientId,
                         @HeaderParam("Access-Token") String accessToken,
                         @HeaderParam("Merchant-Id") String merchantId,
                         @PathParam("idOrder") String idOrder,
                         @PathParam("idOrderItem") String idOrderItem);

    /**
     * GET - Consultar as transações de um pedido
     * Esse recurso é utilizado para obter as informações de todas as transações realizadas em um pedido.
     * O id do pedido é utilizado para realizar a chamada. Em ambiente de produção, uma vez que um pagamento for realizado na Cielo LIO
     * a transactions será adicionada automaticamente no pedido e então, será possível obter as informações do pagamento realizado a partir da chamada deste recurso.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions
     */
    @GET
    @Path("orders/{idOrder}/transactions")
    List<OrderTransaction> findOrderTransactions(@HeaderParam("Client-Id") String clientId,
                                                 @HeaderParam("Access-Token") String accessToken,
                                                 @HeaderParam("Merchant-Id") String merchantId,
                                                 @PathParam("idOrder") String idOrder);

    /**
     * GET - Consultar a transação de um pedido
     * Esse recurso é utilizado para obter as informações de uma transação realizada em um pedido.
     * O id do pedido e o id_transaction são utilizados para realizar a chamada. Em ambiente de produção, uma vez que um pagamento for realizado na Cielo LIO
     * a transactions será adicionada automaticamente no pedido e então, será possível obter as informações do pagamento realizado a partir da chamada deste recurso.
     * Exemplo de requisição: GET https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions/362d1e9e-ae99-4d79-87d6-add9aad2795c
     */
    @GET
    @Path("orders/{idOrder}/transactions/{idOrderTransaction}")
    OrderTransaction findOrderTransaction(@HeaderParam("Client-Id") String clientId,
                                          @HeaderParam("Access-Token") String accessToken,
                                          @HeaderParam("Merchant-Id") String merchantId,
                                          @PathParam("idOrder") String idOrder,
                                          @PathParam("idOrderTransaction") String idOrderTransaction);

    /**
     * POST - Adicionar uma Transação (Recurso utilizado somente para Ambiente de Sandbox)
     * Esse recurso permite que o desenvolvedor simule as transações financeiras, adicionando-as manualmente, sendo possível entender o funcionamento em uma Order.
     * Exemplo de requisição: POST https://api.cielo.com.br/order-management/v1/orders/c393ce9f-3741-413f-8ad5-2f142eaed51f/transactions
     */
    @POST
    @Path("orders/{idOrder}/transactions")
    ResponseId createOrderTransaction(@HeaderParam("Client-Id") String clientId,
                                      @HeaderParam("Access-Token") String accessToken,
                                      @HeaderParam("Merchant-Id") String merchantId,
                                      @PathParam("idOrder") String idOrder,
                                      OrderTransaction orderTransaction);
}
