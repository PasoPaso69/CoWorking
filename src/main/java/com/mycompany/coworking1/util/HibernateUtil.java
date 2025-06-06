package com.mycompany.coworking1.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.mycompany.coworking1.Model.entity.EIndirizzo;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Crea registry
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            // Crea MetadataSources e aggiungi le entità
            MetadataSources sources = new MetadataSources(registry);
            sources.addAnnotatedClass(EIndirizzo.class);

            // Crea Metadata
            Metadata metadata = sources.getMetadataBuilder().build();

            // Crea SessionFactory
            return metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            System.err.println("Errore inizializzazione SessionFactory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}