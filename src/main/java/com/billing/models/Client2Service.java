package com.billing.models;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "client_service")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Client2ServiceId.class)
public class Client2Service {

    @Id
    private Long client_id;
    @Id
    private Long service_id;

    @Column(name = "contract_num")
    private String contract_num;

    @Column(name = "contract_begin")
    private String contract_begin;

    @Column(name = "contract_end")
    private String contract_end;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @NonNull
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    @NonNull
    private Service service;
}


