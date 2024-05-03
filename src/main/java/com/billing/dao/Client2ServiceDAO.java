package com.billing.dao;

import com.billing.utils.HibernateSessionFactory;
import com.billing.models.Client2Service;
import com.billing.models.Client2ServiceId;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class Client2ServiceDAO extends CommonDAO<Client2Service, Client2ServiceId>{

    public Client2ServiceDAO(){
        super(Client2Service.class);
    }

    public List<Client2Service> filter(String service_name, Date begin, Date end, boolean credit) {
        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            StringBuilder hql = new StringBuilder(
                    "SELECT DISTINCT cl_serv FROM Client2Service cl_serv WHERE 1=1");

            if (service_name != null) hql.append(" AND cl_serv.service.name = :service_name");
            if (begin != null) hql.append(" AND cl_serv.contract_begin >= :begin");
            if (end != null) hql.append(" AND (cl_serv.contract_end is NULL OR cl_serv.contract_end <= :end)");
            if (credit) hql.append(" AND cl_serv.client.account.credit > 0");

            TypedQuery<Client2Service> query = session.createQuery(hql.toString(), Client2Service.class);

            if (service_name != null) query.setParameter("service_name", service_name);
            if (begin != null) query.setParameter("begin", begin);
            if (end != null) query.setParameter("end", begin);

            List<Client2Service> res = query.getResultList();
            t.commit();
            return res;
        }
    }
}