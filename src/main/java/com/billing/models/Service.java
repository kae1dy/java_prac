package com.billing.models;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "services")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    @Id
    @GeneratedValue
    @Column(name = "service_id")
    private Long id;

    @Column(name = "service_name")
    private String name;

    @ManyToMany(mappedBy = "ClientService", fetch = FetchType.EAGER)
    private List<Client> ServiceClient;

    @Column(name = "service_package")
    @JdbcTypeCode(SqlTypes.JSON)
    private ServicePackage pack;

    @Column(name = "service_tariff")
    @JdbcTypeCode(SqlTypes.JSON)
    private ServiceTariff tariff;
}
