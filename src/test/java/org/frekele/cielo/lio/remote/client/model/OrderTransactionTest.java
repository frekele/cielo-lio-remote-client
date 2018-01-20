package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.enumeration.CardBrandEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionStatusEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionTypeEnum;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderTransactionTest {

    @Test
    public void testNewInstance() throws Exception {
        String idOrderTransaction = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";
        String uuid = "cf29c38-2aaf9658-c3d7aaad-49fa-a89a";
        String externalId = "49fa-a89ac38-2aaf29c58-c37aaf-9e6ad";
        TransactionStatusEnum status = TransactionStatusEnum.PENDING;
        String description = "xpto 12345";
        Long terminalNumber = 387856L;
        Long authorizationCode = 8947593845754L;
        Long number = 675983475L;
        BigDecimal amount = BigDecimal.valueOf(634.45);
        TransactionTypeEnum transactionType = TransactionTypeEnum.PAYMENT;
        OffsetDateTime createdAt = OffsetDateTime.now();
        OffsetDateTime updatedAt = OffsetDateTime.now().plusHours(4);

        String orderPaymentProductPrimaryProductName = "CREDITO";
        String orderPaymentProductSecondaryProductName = "PARCELADO_LOJA";
        Integer orderPaymentProductNumberOfQuotas = 1;
        OrderPaymentProduct orderPaymentProduct = new OrderPaymentProduct();
        orderPaymentProduct.setPrimaryProductName(orderPaymentProductPrimaryProductName);
        orderPaymentProduct.setSecondaryProductName(orderPaymentProductSecondaryProductName);
        orderPaymentProduct.setNumberOfQuotas(orderPaymentProductNumberOfQuotas);

        String cardBrand = CardBrandEnum.VISA.getValue();
        String cardMask = "************5487";
        OrderCard card = new OrderCard(cardBrand, cardMask);

        OrderTransaction orderTransaction = new OrderTransaction();
        orderTransaction.setId(idOrderTransaction);
        orderTransaction.setUuid(uuid);
        orderTransaction.setExternalId(externalId);
        orderTransaction.setStatus(status);
        orderTransaction.setDescription(description);
        orderTransaction.setTerminalNumber(terminalNumber);
        orderTransaction.setAuthorizationCode(authorizationCode);
        orderTransaction.setNumber(number);
        orderTransaction.setAmount(amount);
        orderTransaction.setTransactionType(transactionType);
        orderTransaction.setCreatedAt(createdAt);
        orderTransaction.setUpdatedAt(updatedAt);
        orderTransaction.setOrderPaymentProduct(orderPaymentProduct);
        orderTransaction.setCard(card);

        assertNotNull(orderTransaction);
        assertEquals(idOrderTransaction, orderTransaction.getId());
        assertEquals(uuid, orderTransaction.getUuid());
        assertEquals(externalId, orderTransaction.getExternalId());
        assertEquals(status, orderTransaction.getStatus());
        assertEquals(description, orderTransaction.getDescription());
        assertEquals(terminalNumber, orderTransaction.getTerminalNumber());
        assertEquals(authorizationCode, orderTransaction.getAuthorizationCode());
        assertEquals(number, orderTransaction.getNumber());
        assertEquals(amount, orderTransaction.getAmount());
        assertEquals(transactionType, orderTransaction.getTransactionType());
        assertEquals(createdAt, orderTransaction.getCreatedAt());
        assertEquals(updatedAt, orderTransaction.getUpdatedAt());

        assertEquals(orderPaymentProduct, orderTransaction.getOrderPaymentProduct());
        assertEquals(orderPaymentProductPrimaryProductName, orderTransaction.getOrderPaymentProduct().getPrimaryProductName());
        assertEquals(orderPaymentProductSecondaryProductName, orderTransaction.getOrderPaymentProduct().getSecondaryProductName());
        assertEquals(orderPaymentProductNumberOfQuotas, orderTransaction.getOrderPaymentProduct().getNumberOfQuotas());

        assertEquals(card, orderTransaction.getCard());
        assertEquals(cardBrand, orderTransaction.getCard().getBrand());
        assertEquals(cardMask, orderTransaction.getCard().getMask());

        orderTransaction = OrderTransaction.newBuilder()
            .withId(idOrderTransaction)
            .withUuid(uuid)
            .withExternalId(externalId)
            .withStatus(status)
            .withDescription(description)
            .withTerminalNumber(terminalNumber)
            .withAuthorizationCode(authorizationCode)
            .withNumber(number)
            .withAmount(amount)
            .withTransactionType(transactionType)
            .withCreatedAt(createdAt)
            .withUpdatedAt(updatedAt)
            .withOrderPaymentProduct(orderPaymentProduct)
            .withCard(card)
            .build();

        assertNotNull(orderTransaction);
        assertEquals(idOrderTransaction, orderTransaction.getId());
        assertEquals(uuid, orderTransaction.getUuid());
        assertEquals(externalId, orderTransaction.getExternalId());
        assertEquals(status, orderTransaction.getStatus());
        assertEquals(description, orderTransaction.getDescription());
        assertEquals(terminalNumber, orderTransaction.getTerminalNumber());
        assertEquals(authorizationCode, orderTransaction.getAuthorizationCode());
        assertEquals(number, orderTransaction.getNumber());
        assertEquals(amount, orderTransaction.getAmount());
        assertEquals(transactionType, orderTransaction.getTransactionType());
        assertEquals(createdAt, orderTransaction.getCreatedAt());
        assertEquals(updatedAt, orderTransaction.getUpdatedAt());

        assertEquals(orderPaymentProduct, orderTransaction.getOrderPaymentProduct());
        assertEquals(orderPaymentProductPrimaryProductName, orderTransaction.getOrderPaymentProduct().getPrimaryProductName());
        assertEquals(orderPaymentProductSecondaryProductName, orderTransaction.getOrderPaymentProduct().getSecondaryProductName());
        assertEquals(orderPaymentProductNumberOfQuotas, orderTransaction.getOrderPaymentProduct().getNumberOfQuotas());

        assertEquals(card, orderTransaction.getCard());
        assertEquals(cardBrand, orderTransaction.getCard().getBrand());
        assertEquals(cardMask, orderTransaction.getCard().getMask());
    }
}
