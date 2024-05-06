package com.billing.dao;

import com.billing.models.Organization;
import com.billing.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrganizationDAO extends CommonDAO<Organization, Integer> {
    public OrganizationDAO() {
        super(Organization.class);
    }

    public Organization findByName(String name) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Organization b = session
                    .createQuery("from Organization where name = :name", Organization.class)
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