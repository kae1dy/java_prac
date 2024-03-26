package com.billing.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

import com.billing.models.*;
import com.billing.HibernateSessionFactory;
import jakarta.persistence.TypedQuery;

public class ServiceDAO extends CommonDAO<Service> {
    public ServiceDAO(Class<Service> entity) {
        super(entity);
    }

    public Service findByName(String name){
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Service b = session
                    .createQuery("from Service where name = :name", Service.class)
                    .setParameter("name", name)
                    .getSingleResult();
            t.commit();
            return b;
        } catch (jakarta.persistence.NoResultException e){
            System.out.println("Error: service " + name + " not found.");
            return null;
        }
    }
}