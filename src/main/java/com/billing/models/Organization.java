package com.billing.models;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Table(name = "organizations")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @GeneratedValue
    @Column(name = "org_id")
    private Long id;

    @Column(name = "org_name")
    private String name;

    @Column(name = "org_inn")
    private String inn;
}