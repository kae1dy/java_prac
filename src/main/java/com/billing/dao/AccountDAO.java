package com.billing.dao;

import com.billing.models.Account;
import com.billing.models.Client2Service;
import com.billing.utils.HibernateSessionFactory;
import com.billing.models.Client;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class AccountDAO extends CommonDAO<Account, Integer>{
    public AccountDAO(){
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
//        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
//
//            Map<Date, BigDecimal> history = account.getHistory();
//            java.util.Date date = new java.util.Date();
//            history.put(date, amount);
//
//            System.out.println(account);
//            Transaction t = session.beginTransaction();
//            StringBuilder hql = new StringBuilder(
//                    "UPDATE Account acc SET acc.balance = acc.balance + :amount, acc.history = :history WHERE acc = :account");
//            Query query = session.createQuery(hql.toString());
//
//            query.setParameter("account", account);
//            query.setParameter("amount", amount);
//            query.setParameter("history", history);
//            return query.executeUpdate();
//        } catch (Exception ex) {
//            System.out.println("all bad");
//        }
//        return 0;
    }
}