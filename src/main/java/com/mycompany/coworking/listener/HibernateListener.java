package com.mycompany.coworking.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mycompany.coworking.util.HibernateUtil;

@WebListener
public class HibernateListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Ottieni la SessionFactory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            // Apri e chiudi subito una sessione per inizializzare Hibernate (carica configurazioni e mapping)
            Session session = sessionFactory.openSession();
            session.close();

            System.out.println("Hibernate SessionFactory inizializzata correttamente.");
        } catch (Throwable ex) {
            System.err.println("Errore durante l'inizializzazione di Hibernate: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Chiudi la SessionFactory all'arresto dell'applicazione
        try {
            HibernateUtil.shutdown();
            System.out.println("Hibernate SessionFactory chiusa correttamente.");
        } catch (Exception ex) {
            System.err.println("Errore durante la chiusura di Hibernate: " + ex);
        }
    }
}
