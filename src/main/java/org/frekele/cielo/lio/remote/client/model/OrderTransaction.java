package org.frekele.cielo.lio.remote.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.deserialize.BigDecimalJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.deserialize.OffsetDateTimeJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.serialize.BigDecimalJsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.serialize.OffsetDateTimeJsonSerialize;
import org.frekele.cielo.lio.remote.client.core.CieloLioModel;
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
public class OrderTransaction implements CieloLioModel {

    private static final long serialVersionUID = 1L;

    private String id;

    //return in push notification.
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

    @JsonProperty("created_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime updatedAt;

    @JsonProperty("payment_fields")
    private OrderPaymentProduct orderPaymentProduct;

    private OrderCard card;

    public OrderTransaction() {
        super();
    }

    private OrderTransaction(Builder builder) {
        setId(builder.id);
        setUuid(builder.uuid);
        setExternalId(builder.externalId);
        setStatus(builder.status);
        setDescription(builder.description);
        setTerminalNumber(builder.terminalNumber);
        setAuthorizationCode(builder.authorizationCode);
        setNumber(builder.number);
        setAmount(builder.amount);
        setTransactionType(builder.transactionType);
        setCreatedAt(builder.createdAt);
        setUpdatedAt(builder.updatedAt);
        setOrderPaymentProduct(builder.orderPaymentProduct);
        setCard(builder.card);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public OrderPaymentProduct getOrderPaymentProduct() {
        return orderPaymentProduct;
    }

    public void setOrderPaymentProduct(OrderPaymentProduct orderPaymentProduct) {
        this.orderPaymentProduct = orderPaymentProduct;
    }

    public OrderCard getCard() {
        return card;
    }

    public void setCard(OrderCard card) {
        this.card = card;
    }

    public static final class Builder {

        private String id;

        private String uuid;

        private String externalId;

        private TransactionStatusEnum status;

        private String description;

        private Long terminalNumber;

        private Long authorizationCode;

        private Long number;

        private BigDecimal amount;

        private TransactionTypeEnum transactionType;

        private OffsetDateTime createdAt;

        private OffsetDateTime updatedAt;

        private OrderPaymentProduct orderPaymentProduct;

        private OrderCard card;

        private Builder() {
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public Builder withUuid(String val) {
            uuid = val;
            return this;
        }

        public Builder withExternalId(String val) {
            externalId = val;
            return this;
        }

        public Builder withStatus(TransactionStatusEnum val) {
            status = val;
            return this;
        }

        public Builder withDescription(String val) {
            description = val;
            return this;
        }

        public Builder withTerminalNumber(Long val) {
            terminalNumber = val;
            return this;
        }

        public Builder withAuthorizationCode(Long val) {
            authorizationCode = val;
            return this;
        }

        public Builder withNumber(Long val) {
            number = val;
            return this;
        }

        public Builder withAmount(BigDecimal val) {
            amount = val;
            return this;
        }

        public Builder withTransactionType(TransactionTypeEnum val) {
            transactionType = val;
            return this;
        }

        public Builder withCreatedAt(OffsetDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder withUpdatedAt(OffsetDateTime val) {
            updatedAt = val;
            return this;
        }

        public Builder withOrderPaymentProduct(OrderPaymentProduct val) {
            orderPaymentProduct = val;
            return this;
        }

        public Builder withCard(OrderCard val) {
            card = val;
            return this;
        }

        public OrderTransaction build() {
            return new OrderTransaction(this);
        }
    }
}
