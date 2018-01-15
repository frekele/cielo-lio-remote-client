package org.frekele.cielo.lio.remote.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.BigDecimalJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.BigDecimalJsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.OffsetDateTimeJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.OffsetDateTimeJsonSerialize;
import org.frekele.cielo.lio.remote.client.core.CieloLioEntity;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionStatusEnum;
import org.frekele.cielo.lio.remote.client.enumeration.TransactionTypeEnum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderTransactionEntity implements CieloLioEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    //vem no callback
    private String uuid;

    @JsonProperty("external_id")
    private String externalId;

    private TransactionStatusEnum status;

    private String description;

    @JsonProperty("terminal_number")
    private Long terminalNumber;

    @JsonProperty("authorization_code")
    private Long authorizationCode;

    private Long number;

    @JsonDeserialize(using = BigDecimalJsonDeserialize.class)
    @JsonSerialize(using = BigDecimalJsonSerialize.class)
    private BigDecimal amount;

    @JsonProperty("transaction_type")
    private TransactionTypeEnum transactionType;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("created_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime updatedAt;

    @JsonProperty("payment_fields")
    private OrderPaymentProductEntity orderPaymentProduct;

    private OrderCardEntity card;

    public OrderTransactionEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public TransactionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransactionStatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(Long terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public Long getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(Long authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OrderPaymentProductEntity getOrderPaymentProduct() {
        return orderPaymentProduct;
    }

    public void setOrderPaymentProduct(OrderPaymentProductEntity orderPaymentProduct) {
        this.orderPaymentProduct = orderPaymentProduct;
    }

    public OrderCardEntity getCard() {
        return card;
    }

    public void setCard(OrderCardEntity card) {
        this.card = card;
    }
}
