package org.frekele.cielo.lio.remote.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.deserialize.BigDecimalJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.deserialize.OffsetDateTimeJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.serialize.BigDecimalJsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.serialize.OffsetDateTimeJsonSerialize;
import org.frekele.cielo.lio.remote.client.core.CieloLioModel;

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
public class OrderItem implements CieloLioModel {

    private static final long serialVersionUID = 1L;

    private String id;

    //return in push notification.
    private String uuid;

    private String sku;

    private String name;

    private String description;

    @JsonProperty("unit_price")
    @JsonDeserialize(using = BigDecimalJsonDeserialize.class)
    @JsonSerialize(using = BigDecimalJsonSerialize.class)
    private BigDecimal unitPrice;

    private Integer quantity;

    @JsonProperty("unit_of_measure")
    private String unitOfMeasure;

    private String details;

    private String reference;

    @JsonProperty("created_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime updatedAt;

    public OrderItem() {
        super();
    }

    private OrderItem(Builder builder) {
        setId(builder.id);
        setUuid(builder.uuid);
        setSku(builder.sku);
        setName(builder.name);
        setDescription(builder.description);
        setUnitPrice(builder.unitPrice);
        setQuantity(builder.quantity);
        setUnitOfMeasure(builder.unitOfMeasure);
        setDetails(builder.details);
        setReference(builder.reference);
        setCreatedAt(builder.createdAt);
        setUpdatedAt(builder.updatedAt);
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public static final class Builder {

        private String id;

        private String uuid;

        private String sku;

        private String name;

        private String description;

        private BigDecimal unitPrice;

        private Integer quantity;

        private String unitOfMeasure;

        private String details;

        private String reference;

        private OffsetDateTime createdAt;

        private OffsetDateTime updatedAt;

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

        public Builder withSku(String val) {
            sku = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withDescription(String val) {
            description = val;
            return this;
        }

        public Builder withUnitPrice(BigDecimal val) {
            unitPrice = val;
            return this;
        }

        public Builder withQuantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder withUnitOfMeasure(String val) {
            unitOfMeasure = val;
            return this;
        }

        public Builder withDetails(String val) {
            details = val;
            return this;
        }

        public Builder withReference(String val) {
            reference = val;
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

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
