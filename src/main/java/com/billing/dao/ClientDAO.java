package com.billing.dao;

import com.billing.HibernateSessionFactory;
import com.billing.models.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDAO extends CommonDAO<Client, Integer>{

    public ClientDAO(){
        super(Client.class);
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
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}