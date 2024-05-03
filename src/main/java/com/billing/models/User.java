package com.billing.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
//import java.sql.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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