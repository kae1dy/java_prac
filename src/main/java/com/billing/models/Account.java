package com.billing.models;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "acc_id")
    private Long id;

    @Column(name = "acc_balance")
    private java.math.BigDecimal balance;

    @Column(name = "acc_history")
    @JdbcTypeCode(SqlTypes.JSON)
    private AccountHistory history;

    @Column(name = "acc_credit")
    private java.math.BigDecimal credit;

    @Column(name = "acc_terms")
    private java.sql.Date terms;
}