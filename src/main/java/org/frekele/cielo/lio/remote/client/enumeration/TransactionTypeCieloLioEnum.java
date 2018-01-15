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
public enum TransactionTypeCieloLioEnum {

    PAYMENT("PAYMENT"),

    CANCELLATION("CANCELLATION");

    private String value;

    private TransactionTypeCieloLioEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @XmlValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static TransactionTypeCieloLioEnum fromValue(String value) {
        if (value != null && value.length() != 0) {
            for (TransactionTypeCieloLioEnum obj : getAll()) {
                if (obj.value.equals(value)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public static List<TransactionTypeCieloLioEnum> getAll() {
        return Arrays.asList(TransactionTypeCieloLioEnum.values());
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
