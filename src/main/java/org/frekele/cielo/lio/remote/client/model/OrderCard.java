package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.core.CieloLioEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderCard implements CieloLioEntity {

    private static final long serialVersionUID = 1L;

    private String brand;

    private String mask;

    public OrderCard() {
        super();
    }

    public OrderCard(String brand, String mask) {
        this.brand = brand;
        this.mask = mask;
    }

    private OrderCard(Builder builder) {
        setBrand(builder.brand);
        setMask(builder.mask);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public static final class Builder {

        private String brand;

        private String mask;

        private Builder() {
        }

        public Builder withBrand(String val) {
            brand = val;
            return this;
        }

        public Builder withMask(String val) {
            mask = val;
            return this;
        }

        public OrderCard build() {
            return new OrderCard(this);
        }
    }
}
