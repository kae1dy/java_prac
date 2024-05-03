package com.billing.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Client2ServiceId implements Serializable {
    private int client_id;
    private int service_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client2ServiceId that = (Client2ServiceId) o;

        return client_id == that.client_id && service_id == that.service_id;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + client_id;
        result = 31 * result + service_id;
        return result;
    }
}