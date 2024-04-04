package com.billing.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_org", referencedColumnName = "org_id")
    private Organization org;

    @Column(name = "client_name")
    private String name;
//
//    @Column(name = "client_info")
//    @JdbcTypeCode(SqlTypes.JSON)
//    private ClientInfo info;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "client_service",
//            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "client_id"),
//            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"))
//    private List<Service> ClientService;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "client")
//    private List<Client2Service> ClientService;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "acc_id", unique = true)
    private Account account;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client that = (Client) o;

        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(org, that.org) &&
                Objects.equals(account, that.account);
    }
}