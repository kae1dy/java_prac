package com.billing.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "services")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    public Service(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer id;

    @Column(name = "service_name")
    private String name;

    @Column(name = "service_package")
    @JdbcTypeCode(SqlTypes.JSON)
    private ServicePackage pack;

    @Column(name = "service_tariff")
    @JdbcTypeCode(SqlTypes.JSON)
    private ServiceTariff tariff;

    public Service(String name, ServicePackage pack, ServiceTariff tariff) {
        this.name = name;
        this.pack = pack;
        this.tariff = tariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service that = (Service) o;

        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(pack, that.pack) &&
                Objects.equals(tariff, that.tariff);
    }
}
