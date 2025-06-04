
package com.mycompany.coworking1.Model.entity;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;




public class main {
    public static void main(String[] args) {
        // Crea EntityManagerFactory -> trigger Hibernate schema creation
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoWirking1PU");

        // Crea EntityManager (apre una sessione con il DB)
        EntityManager em = emf.createEntityManager();

        // Chiudi subito per forzare la creazione tabelle
        em.close();
        emf.close();

        System.out.println("Tabelle create correttamente.");
    }
}
