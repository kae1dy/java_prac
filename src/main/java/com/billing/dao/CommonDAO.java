package com.billing.dao;

import com.billing.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class CommonDAO<T, PrimaryKey> {
    private final Class<T> entity;

    public CommonDAO(Class<T> entity){
        this.entity = entity;
    }

    public T findById(PrimaryKey id){
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            T res = session.get(entity, id);
            t.commit();
            return res;
        }
    }

    public List<T> findAll(){
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            List<T> res = session.createQuery("from " + entity.getSimpleName(), entity).getResultList();
            t.commit();
            return res;
        }
    }

    public void save(T obj){
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            try {
                session.persist(obj);
                t.commit();
            } catch (Exception exp) {
                t.rollback();
                throw exp;
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
                t.rollback();
                throw exp;
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
                t.rollback();
                throw exp;
            }
        }
    }

    public void deleteById(PrimaryKey id) {
        T obj = findById(id);
        if (obj != null) {
            try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
                Transaction t = session.beginTransaction();
                try {
                    session.remove(obj);
                    t.commit();
                } catch (Exception exp) {
                    t.rollback();
                    throw exp;
                }
            }
        }
    }
}