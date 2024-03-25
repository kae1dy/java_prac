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
    public List<Client> filter(Service service, Date begin, Date end, boolean credit) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder hql = new StringBuilder("SELECT DISTINCT cl FROM Client cl");

            if (credit != null) {
                // + client_sevice;
            }
            if ((service != null) || (begin != null) || (end != null)) {
                // hql.append()
            }
            hql.append(" WHERE 1=1");
            if (credit != null) {
                if (credit == true) hql.append(" AND account.credit > 0");
                else hql.append(" AND account.credit == 0");
            }
            if (service != null) hql.append(" AND aw.salary >= :minSalary");
            if (begin != null) hql.append(" AND aw.salary <= :begin");
            if (end != null) hql.append(" AND aw.salary <= :end");

            TypedQuery<Client> query = session.createQuery(hql.toString(), Client.class);
            if (service != null) query.setParameter("service", service);
            if (begin != null) query.setParameter("begin", begin);
            if (end != null) query.setParameter("end", begin);

            List<Client> res = query.getResultList();
            t.commit();
            return res;
        }
    }
}