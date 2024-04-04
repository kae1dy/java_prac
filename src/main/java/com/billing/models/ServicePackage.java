package com.billing.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ServicePackage implements Serializable {

    private int min;
    private int sms;
    private int internet;
    private int max_members;

    public static List<String> getFields() {
        List<String> fields = new ArrayList<>();
        fields.add("min");
        fields.add("sms");
        fields.add("extra_sms");
        fields.add("internet");
        return fields;
    }
}