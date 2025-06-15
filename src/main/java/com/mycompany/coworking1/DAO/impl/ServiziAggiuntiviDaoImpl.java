package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.Model.entity.EServiziAggiuntivi;
import com.mycompany.coworking1.Model.entity.EUfficio;

import javax.persistence.EntityManager;
import java.util.List;

public class ServiziAggiuntiviDaoImpl {

    private final EntityManager em;

    public ServiziAggiuntiviDaoImpl(EntityManager em) {
        this.em = em;
    }

    public List<EServiziAggiuntivi> getServiziByOffice(EUfficio ufficio) {
        return em.createQuery(
                "SELECT s FROM EServiziAggiuntivi s WHERE s.ufficio = :ufficio", EServiziAggiuntivi.class)
                .setParameter("ufficio", ufficio)
                .getResultList();
    }
}
