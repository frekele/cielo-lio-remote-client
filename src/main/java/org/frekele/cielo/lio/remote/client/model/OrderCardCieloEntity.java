package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.core.CieloLioEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author leandro.freitas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderCardCieloEntity implements CieloLioEntity {

    private static final long serialVersionUID = 1L;

    private String brand;

    private String mask;

    public OrderCardCieloEntity() {
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
}
