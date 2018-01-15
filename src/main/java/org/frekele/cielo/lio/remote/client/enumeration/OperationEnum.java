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
public enum OperationEnum {

    PLACE("PLACE"),

    PAY("PAY"),

    CLOSE("CLOSE"),

    PAID("PAID");

    private String value;

    private OperationEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @XmlValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static OperationEnum fromValue(String value) {
        if (value != null && value.length() != 0) {
            for (OperationEnum obj : getAll()) {
                if (obj.value.equals(value)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public static List<OperationEnum> getAll() {
        return Arrays.asList(OperationEnum.values());
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
