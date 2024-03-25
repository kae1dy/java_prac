package com.prospher.ras.models;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "client_service")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Person2Place {

    @Id
    @GeneratedValue
    @Column(nname = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person")
    @ToString.Exclude
    @NonNull
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place")
    @ToString.Exclude
    @NonNull
    private Service service;
}


