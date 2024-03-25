package com.billing.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

import com.billing.models.*;

public class AccountDAO extends CommonDAO<Account> {
    public AccountDAO(Class<Account> entity) {
        super(entity);
    }
}