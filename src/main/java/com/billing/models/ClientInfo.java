package com.billing.models;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import java.io.Serializable;

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