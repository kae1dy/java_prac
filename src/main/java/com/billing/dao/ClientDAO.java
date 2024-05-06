package com.billing.dao;

import com.billing.models.Client;
import com.billing.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDAO extends CommonDAO<Client, Integer> {

    public ClientDAO() {
        super(Client.class);
    }


    public Client findByName(String name) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Client b = session
                    .createQuery("from Client where name = :name", Client.class)
                    .setParameter("name", name)
                    .setMaxResults(1)
                    .getSingleResult();
            t.commit();
            return b;
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}