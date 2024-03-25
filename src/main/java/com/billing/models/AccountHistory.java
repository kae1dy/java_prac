package com.billing.models;
import java.sql.Date;
import java.math.BigDecimal;
import java.util.*;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AccountHistory implements Serializable {
    private List<Date> dates;
    private List<BigDecimal> transactions;
}