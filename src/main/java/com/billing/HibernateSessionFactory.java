package com.billing;

import com.billing.models.*;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@NoArgsConstructor
public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
            try {
                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClasses(
                                Account.class,
                                Client.class,
                                Client2Service.class,
                                Organization.class,
                                Service.class
                        )
                        .buildMetadata()
                        .buildSessionFactory();
            } catch (Exception exp) {
                System.out.println("Hibernate Error");
                throw exp;
            }
        }

        return sessionFactory;
    }
}
