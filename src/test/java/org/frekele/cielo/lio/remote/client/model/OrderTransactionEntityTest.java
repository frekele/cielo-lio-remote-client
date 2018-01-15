package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.enumeration.CardBrandEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionStatusEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionTypeEnum;
import org.frekele.cielo.lio.remote.client.model.id.OrderTransactionId;
import org.frekele.cielo.lio.remote.client.testng.InvokedMethodListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.testng.Assert.*;

@Listeners(InvokedMethodListener.class)
public class OrderTransactionEntityTest {

    @Test
    public void testNewInstance() throws Exception {
        String transactionId = "af9658-c3dd-49fa-acf29c38-2aa7aa89a";
        OrderTransactionId id = new OrderTransactionId(transactionId);
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
        OrderPaymentProductEntity orderPaymentProduct = new OrderPaymentProductEntity();
        orderPaymentProduct.setPrimaryProductName(orderPaymentProductPrimaryProductName);
        orderPaymentProduct.setSecondaryProductName(orderPaymentProductSecondaryProductName);
        orderPaymentProduct.setNumberOfQuotas(orderPaymentProductNumberOfQuotas);

        String cardBrand = CardBrandEnum.VISA.getValue();
        String cardMask = "************5487";
        OrderCardEntity card = new OrderCardEntity(cardBrand, cardMask);

        OrderTransactionEntity entity = new OrderTransactionEntity();
        entity.setId(id);
        entity.setUuid(uuid);
        entity.setExternalId(externalId);
        entity.setStatus(status);
        entity.setDescription(description);
        entity.setTerminalNumber(terminalNumber);
        entity.setAuthorizationCode(authorizationCode);
        entity.setNumber(number);
        entity.setAmount(amount);
        entity.setTransactionType(transactionType);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);
        entity.setOrderPaymentProduct(orderPaymentProduct);
        entity.setCard(card);

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals(transactionId, entity.getId().getId());
        assertEquals(uuid, entity.getUuid());
        assertEquals(externalId, entity.getExternalId());
        assertEquals(status, entity.getStatus());
        assertEquals(description, entity.getDescription());
        assertEquals(terminalNumber, entity.getTerminalNumber());
        assertEquals(authorizationCode, entity.getAuthorizationCode());
        assertEquals(number, entity.getNumber());
        assertEquals(amount, entity.getAmount());
        assertEquals(amount, entity.getAmount());
        assertEquals(transactionType, entity.getTransactionType());
        assertEquals(createdAt, entity.getCreatedAt());
        assertEquals(updatedAt, entity.getUpdatedAt());

        assertEquals(orderPaymentProduct, entity.getOrderPaymentProduct());
        assertEquals(orderPaymentProductPrimaryProductName, entity.getOrderPaymentProduct().getPrimaryProductName());
        assertEquals(orderPaymentProductSecondaryProductName, entity.getOrderPaymentProduct().getSecondaryProductName());
        assertEquals(orderPaymentProductNumberOfQuotas, entity.getOrderPaymentProduct().getNumberOfQuotas());

        assertEquals(card, entity.getCard());
        assertEquals(cardBrand, entity.getCard().getBrand());
        assertEquals(cardMask, entity.getCard().getMask());
    }
}
