package com.billing.dao;

import com.billing.models.User;
import com.billing.utils.HibernateSessionFactory;
import com.billing.models.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO extends CommonDAO<User, Integer>{

    public UserDAO(){
        super(User.class);
    }

    public User checkValid(String login, String password){
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            User b = session
                    .createQuery("from User where login = :login and password = :password", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            t.commit();
            return b;
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}