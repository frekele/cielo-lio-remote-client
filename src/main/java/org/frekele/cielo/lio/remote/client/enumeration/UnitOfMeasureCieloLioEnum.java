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
public enum UnitOfMeasureCieloLioEnum {

    EACH("EACH"),

    HOURS("HOURS"),

    DAYS("DAYS"),

    SECONDS("SECONDS"),

    CRATE_OF_12("CRATE_OF_12"),

    SIX_PACK("SIX_PACK"),

    GALLON("GALLON"),

    LITRE("LITRE");

    private String value;

    private UnitOfMeasureCieloLioEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @XmlValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static UnitOfMeasureCieloLioEnum fromValue(String value) {
        if (value != null && value.length() != 0) {
            for (UnitOfMeasureCieloLioEnum obj : getAll()) {
                if (obj.value.equals(value)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public static List<UnitOfMeasureCieloLioEnum> getAll() {
        return Arrays.asList(UnitOfMeasureCieloLioEnum.values());
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
