package com.billing.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.sql.Date;


import jakarta.persistence.TypedQuery;
import com.billing.HibernateSessionFactory;

public abstract class CommonDAO<T> {
    private final Class<T> entity;

    public CommonDAO(Class<T> entity){
        this.entity = entity;
    }

    public T findById(Long id){
        return HibernateSessionFactory.getSessionFactory().getCurrentSession().get(entity, id);
    }

    public List<T> findAll(){
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        return session.createQuery("from " + entity.getSimpleName(), entity).getResultList();
    }

    public void save(T obj){
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            try {
                session.persist(obj);
                t.commit();
            } catch (Exception exp) {
                System.out.println("Save Error: " + exp);
                t.rollback();
            }
        }
    }

    public void update(T obj){
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            try {
                session.merge(obj);
                t.commit();
            } catch (Exception exp) {
                System.out.println("Update Error:" + exp);
                t.rollback();
            }
        }
    }

    public void delete(T obj) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            try {
                session.remove(obj);
                t.commit();
            } catch (Exception exp) {
                System.out.println("Delete Error:  " + exp);
                t.rollback();
            }
        }
    }

    public void deleteById(Long id) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.remove(findById(id));
        t.commit();
    }
}