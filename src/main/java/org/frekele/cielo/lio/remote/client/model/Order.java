package org.frekele.cielo.lio.remote.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.deserialize.BigDecimalJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.deserialize.OffsetDateTimeJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.serialize.BigDecimalJsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.serialize.OffsetDateTimeJsonSerialize;
import org.frekele.cielo.lio.remote.client.core.CieloLioModel;
import org.frekele.cielo.lio.remote.client.enumeration.OrderStatusEnum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements CieloLioModel {

    private static final long serialVersionUID = 1L;

    private String id;

    private String number;

    private String reference;

    private OrderStatusEnum status;

    private String notes;

    @JsonDeserialize(using = BigDecimalJsonDeserialize.class)
    @JsonSerialize(using = BigDecimalJsonSerialize.class)
    private BigDecimal price;

    @JsonDeserialize(using = BigDecimalJsonDeserialize.class)
    @JsonSerialize(using = BigDecimalJsonSerialize.class)
    private BigDecimal remaining;

    @JsonProperty("order_type")
    private String orderType;

    private String merchant;

    @JsonProperty("source_id")
    private String sourceId;

    @JsonProperty("created_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime updatedAt;

    private List<OrderItem> items;

    private List<OrderTransaction> transactions;

    public Order() {
        super();
    }

    private Order(Builder builder) {
        setId(builder.id);
        setNumber(builder.number);
        setReference(builder.reference);
        setStatus(builder.status);
        setNotes(builder.notes);
        setPrice(builder.price);
        setRemaining(builder.remaining);
        setOrderType(builder.orderType);
        setMerchant(builder.merchant);
        setSourceId(builder.sourceId);
        setCreatedAt(builder.createdAt);
        setUpdatedAt(builder.updatedAt);
        setItems(builder.items);
        setTransactions(builder.transactions);
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public List<OrderTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<OrderTransaction> transactions) {
        this.transactions = transactions;
    }

    public static final class Builder {

        private String id;

        private String number;

        private String reference;

        private OrderStatusEnum status;

        private String notes;

        private BigDecimal price;

        private BigDecimal remaining;

        private String orderType;

        private String merchant;

        private String sourceId;

        private OffsetDateTime createdAt;

        private OffsetDateTime updatedAt;

        private List<OrderItem> items;

        private List<OrderTransaction> transactions;

        private Builder() {
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public Builder withNumber(String val) {
            number = val;
            return this;
        }

        public Builder withReference(String val) {
            reference = val;
            return this;
        }

        public Builder withStatus(OrderStatusEnum val) {
            status = val;
            return this;
        }

        public Builder withNotes(String val) {
            notes = val;
            return this;
        }

        public Builder withPrice(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder withRemaining(BigDecimal val) {
            remaining = val;
            return this;
        }

        public Builder withOrderType(String val) {
            orderType = val;
            return this;
        }

        public Builder withMerchant(String val) {
            merchant = val;
            return this;
        }

        public Builder withSourceId(String val) {
            sourceId = val;
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

        public Builder withItems(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder withTransactions(List<OrderTransaction> val) {
            transactions = val;
            return this;
        }

        public Builder addItems(List<OrderItem> val) {
            this.initItens();
            items.addAll(val);
            return this;
        }

        public Builder addItem(OrderItem val) {
            this.initItens();
            items.add(val);
            return this;
        }

        public Builder addTransactions(List<OrderTransaction> val) {
            this.initTransactions();
            transactions.addAll(val);
            return this;
        }

        public Builder addTransaction(OrderTransaction val) {
            this.initTransactions();
            transactions.add(val);
            return this;
        }

        private void initItens() {
            if (items == null) {
                items = new ArrayList<>();
            }
        }

        private void initTransactions() {
            if (transactions == null) {
                transactions = new ArrayList<>();
            }
        }

        public Order build() {
            return new Order(this);
        }
    }
}
