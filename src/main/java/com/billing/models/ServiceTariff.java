package com.billing.models;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTariff implements Serializable {

    private BigDecimal tariff;
    private BigDecimal extra_min;
    private BigDecimal extra_sms;
    private BigDecimal extra_internet;

    public static List<String> getFields(){
        List<String> fields = new ArrayList<>();
        fields.add("tariff");
        fields.add("extra_min");
        fields.add("extra_sms");
        fields.add("extra_internet");
        return fields;
    }
}