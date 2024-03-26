package com.billing.models;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "client_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_org", referencedColumnName = "org_id")
    private Organization org;

    @Column(name = "client_name")
    private String name;

    @Column(name = "client_info")
    @JdbcTypeCode(SqlTypes.JSON)
    private ClientInfo info;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "client_service",
//            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "client_id"),
//            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"))
//    private List<Service> ClientService;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "client_id")
    private List<Client2Service> ClientService;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "acc_id", unique = true)
    private Account account;
}