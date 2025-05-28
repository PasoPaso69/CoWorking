package com.mycompany.coworking;

import com.mycompany.coworking.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestMain {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.close();
        HibernateUtil.shutdown();
        System.out.println("Hibernate avviato con successo.");
    }
}
