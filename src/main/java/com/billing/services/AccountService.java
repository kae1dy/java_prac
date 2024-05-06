package com.billing.services;

import com.billing.dao.AccountDAO;
import com.billing.models.Account;

import java.math.BigDecimal;

public class AccountService extends CommonService<Account, Integer, AccountDAO> {
    public AccountService() {
        super(new AccountDAO());
    }

    public Account addTrans(Account account, BigDecimal amount) {
        return dao.addTrans(account, amount);
    }
}