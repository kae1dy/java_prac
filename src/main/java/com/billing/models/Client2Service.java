package com.billing.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "client_service")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client2Service {
    @EmbeddedId
    private Client2ServiceId id;

    @Column(name = "contract_num")
    private String contract_num;

    @Column(name = "contract_begin")
    private Date contract_begin;

    @Column(name = "contract_end")
    private Date contract_end;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    @NonNull
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    @NonNull
    private Service service;

    public Client2Service(String contract_num, @NonNull Client client, @NonNull Service service, @NonNull Date contract_begin, Date contract_end) {
        this.contract_num = contract_num;
        this.id = new Client2ServiceId(client.getId(), service.getId());
        this.client = client;
        this.service = service;
        this.contract_begin = contract_begin;
        this.contract_end = contract_end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client2Service that = (Client2Service) o;

        return id == that.id &&
                Objects.equals(contract_num, that.contract_num) &&
                Objects.equals(contract_begin, that.contract_begin) &&
                Objects.equals(contract_end, that.contract_end);
    }
}


