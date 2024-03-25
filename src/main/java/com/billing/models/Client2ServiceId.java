package com.billing.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client2ServiceId implements Serializable {
    private long client_id;
    private long service_id;
}