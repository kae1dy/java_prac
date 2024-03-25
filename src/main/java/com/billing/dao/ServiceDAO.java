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
}