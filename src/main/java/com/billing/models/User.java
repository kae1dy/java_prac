package com.billing.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_login")
    private String login;

    @Column(name = "user_password")
    private String password;

//    @Column(name = "acc_history")
//    @Convert(converter = HistoryConverter.class)
//    private Map<Date, BigDecimal> history;
}