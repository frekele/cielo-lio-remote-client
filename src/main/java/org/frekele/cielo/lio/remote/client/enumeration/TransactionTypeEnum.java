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
public enum TransactionTypeEnum {

    PAYMENT("PAYMENT"),

    CANCELLATION("CANCELLATION");

    private String value;

    private TransactionTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @XmlValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static TransactionTypeEnum fromValue(String value) {
        if (value != null) {
            for (TransactionTypeEnum obj : getAll()) {
                if (obj.value.equals(value)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public static List<TransactionTypeEnum> getAll() {
        return Arrays.asList(TransactionTypeEnum.values());
    }
}
