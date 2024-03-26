package com.billing.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

import com.billing.models.*;
import com.billing.HibernateSessionFactory;
import jakarta.persistence.TypedQuery;

public class ClientDAO extends CommonDAO<Account>{

    public ClientDAO(){
        super(Account.class);
    }


    public Client findByName(String name){
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Client b = session
                    .createQuery("from Client where name = :name", Client.class)
                    .setParameter("name", name)
                    .getSingleResult();
            t.commit();
            return b;
        } catch (jakarta.persistence.NoResultException e){
            System.out.println("Error: client " + name + " not found.");
            return null;
        }
    }
}