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
public class ClientInfo implements Serializable {

    private String phone;
    private String email;
    private String address;

    public static List<String> getFields() {
        List<String> fields = new ArrayList<>();
        fields.add("phone");
        fields.add("email");
        fields.add("address");
        return fields;
    }
}