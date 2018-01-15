package org.frekele.cielo.lio.remote.client.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.util.Arrays;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@XmlType
@XmlEnum(String.class)
public enum CardBrandEnum {

    AURA("AURA"),
    AMERICAN_EXPRESS("AMEX"),
    CABAL("CABAL"),
    DINERS_CLUB("DINERS"),
    DISCOVERY("DISCOVER"),
    ELO("ELO"),
    HIPERCARD("HIPERCARD"),
    HIPER("HIPER"),
    JCB("JCB"),
    MASTERCARD("MASTERCARD"),
    SOROCRED("SOROCRED"),
    VISA("VISA");

    private String value;

    private CardBrandEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @XmlValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static CardBrandEnum fromValue(String value) {
        if (value != null && value.length() != 0) {
            for (CardBrandEnum obj : getAll()) {
                if (obj.value.equals(value)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public static List<CardBrandEnum> getAll() {
        return Arrays.asList(CardBrandEnum.values());
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
