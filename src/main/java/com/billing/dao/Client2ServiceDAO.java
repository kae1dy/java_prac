package com.billing.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

import com.billing.models.*;
import com.billing.HibernateSessionFactory;
import jakarta.persistence.TypedQuery;

public class Client2ServiceDAO extends CommonDAO<Client2Service>{

    public Client2ServiceDAO(){
        super(Client2Service.class);
    }

    public List<Client> filter(Service service, Date begin, Date end, boolean credit) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder hql = new StringBuilder(
                    "SELECT DISTINCT cl_serv.client FROM Client2Service cl_serv WHERE 1=1");

            if (service != null) hql.append(" AND service = :service");
            if (begin != null) hql.append(" AND contract_end >= :begin");
            if (end != null) hql.append(" AND contract_begin <= :end");
            if (credit) hql.append(" AND cl_serv.client.account.credit > 0");

            TypedQuery<Client> query = session.createQuery(hql.toString(), Client.class);

            if (service != null) query.setParameter("service", service);
            if (begin != null) query.setParameter("begin", begin);
            if (end != null) query.setParameter("end", begin);

            List<Client> res = query.getResultList();
            t.commit();
            return res;
        } catch (jakarta.persistence.NoResultException e) {
            System.out.println("Error: filter client.");
            return null;
        }
    }
}