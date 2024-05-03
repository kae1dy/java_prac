package com.billing.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "organizations")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Integer id;

    @Column(name = "org_name")
    private String name;

    @Column(name = "org_inn")
    private String inn;
}