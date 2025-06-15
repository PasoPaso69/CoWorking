/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
/**
 *
 * @author 39327
 */
public class EntityManagerUtil {


    private static final EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
    try {
        return Persistence.createEntityManagerFactory("CoWirking1PU");
    } catch (Throwable ex) {
        ex.printStackTrace();  // Stampa completa dell'errore
        throw new ExceptionInInitializerError(ex);
    }
}

    public static EntityManager getEntityManager() {
        // Restituisce un nuovo EntityManager da usare per le transazioni
        return emf.createEntityManager();
    }

    public static void close() {
        // Chiude la factory all'arresto dell'applicazione
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}


