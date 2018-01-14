package org.frekele.cielo.lio.remote.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.frekele.cielo.lio.remote.client.core.CieloLioEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderPaymentProductCieloEntity implements CieloLioEntity {

    private static final long serialVersionUID = 1L;

    @JsonProperty("primary_product_name")
    private String primaryProductName;

    @JsonProperty("secondary_product_name")
    private String secondaryProductName;

    @JsonProperty("number_of_quotas")
    private String numberOfQuotas;

    public OrderPaymentProductCieloEntity() {
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

    public String getNumberOfQuotas() {
        return numberOfQuotas;
    }

    public void setNumberOfQuotas(String numberOfQuotas) {
        this.numberOfQuotas = numberOfQuotas;
    }
}
