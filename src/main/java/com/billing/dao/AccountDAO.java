package com.billing.dao;

import com.billing.models.Account;
import com.billing.utils.HibernateSessionFactory;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class AccountDAO extends CommonDAO<Account, Integer> {
    public AccountDAO() {
        super(Account.class);
    }

    public Account addTrans(Account account, BigDecimal amount) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Map<Date, BigDecimal> history = account.getHistory();
            java.util.Date date = new java.util.Date();

            history.put(date, amount);
            account.setHistory(history);
            account.setBalance(account.getBalance().add(amount));
            System.out.println(history);
            update(account);
            return account;
        }
    }
}