package com.billing;

import com.billing.models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AccountTests {
    @Test
    public void test(){
        Account acc = new Account();

        acc.setId(1);
        acc.setBalance(BigDecimal.valueOf(100.0));
        acc.setCredit(BigDecimal.valueOf(0.0));

        Assertions.assertEquals(acc.getId(), 1);
        Assertions.assertEquals(acc.getBalance(), BigDecimal.valueOf(100.0));
        Assertions.assertEquals(acc.getCredit(), BigDecimal.valueOf(0.0));
    }
}