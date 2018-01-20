package org.frekele.cielo.lio.remote.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.frekele.cielo.lio.remote.client.core.CieloLioModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderPaymentProduct implements CieloLioModel {

    private static final long serialVersionUID = 1L;

    @JsonProperty("primary_product_name")
    private String primaryProductName;

    @JsonProperty("secondary_product_name")
    private String secondaryProductName;

    @JsonProperty("number_of_quotas")
    private Integer numberOfQuotas;

    public OrderPaymentProduct() {
        super();
    }

    private OrderPaymentProduct(Builder builder) {
        setPrimaryProductName(builder.primaryProductName);
        setSecondaryProductName(builder.secondaryProductName);
        setNumberOfQuotas(builder.numberOfQuotas);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getPrimaryProductName() {
        return primaryProductName;
    }

    public void setPrimaryProductName(String primaryProductName) {
        this.primaryProductName = primaryProductName;
    }

    public String getSecondaryProductName() {
        return secondaryProductName;
    }

    public void setSecondaryProductName(String secondaryProductName) {
        this.secondaryProductName = secondaryProductName;
    }

    public Integer getNumberOfQuotas() {
        return numberOfQuotas;
    }

    public void setNumberOfQuotas(Integer numberOfQuotas) {
        this.numberOfQuotas = numberOfQuotas;
    }

    public static final class Builder {

        private String primaryProductName;

        private String secondaryProductName;

        private Integer numberOfQuotas;

        private Builder() {
        }

        public Builder withPrimaryProductName(String val) {
            primaryProductName = val;
            return this;
        }

        public Builder withSecondaryProductName(String val) {
            secondaryProductName = val;
            return this;
        }

        public Builder withNumberOfQuotas(Integer val) {
            numberOfQuotas = val;
            return this;
        }

        public OrderPaymentProduct build() {
            return new OrderPaymentProduct(this);
        }
    }
}
