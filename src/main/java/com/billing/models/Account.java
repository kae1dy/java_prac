package com.billing.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
//import java.sql.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Integer id;

    @Column(name = "acc_balance")
    private java.math.BigDecimal balance;

    @JdbcTypeCode(SqlTypes.JSON)
    @Convert(converter = HistoryConverter.class)
    @Column(name = "acc_history")
    private Map<Date, BigDecimal> history;

    @Column(name = "acc_credit")
    private java.math.BigDecimal credit;

    @Column(name = "acc_terms")
    private java.sql.Date terms;
}