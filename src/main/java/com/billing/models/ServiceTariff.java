package com.billing.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getFields() {
        List<String> fields = new ArrayList<>();
        fields.add("tariff");
        fields.add("extra_min");
        fields.add("extra_sms");
        fields.add("extra_internet");
        return fields;
    }
}