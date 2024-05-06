package com.billing.dao;

import com.billing.models.Service;
import com.billing.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServiceDAO extends CommonDAO<Service, Integer> {
    public ServiceDAO() {
        super(Service.class);
    }

    public Service findByName(String name) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Service b = session
                    .createQuery("from Service where name = :name", Service.class)
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