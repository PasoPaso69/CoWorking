package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.Model.entity.EIntervalliDisponibilita;
import com.mycompany.coworking1.Model.entity.EUfficio;

import javax.persistence.EntityManager;
import java.util.List;

public class IntervalloDisponibillitaDaoImpl {

    private final EntityManager em;

    public IntervalloDisponibillitaDaoImpl(EntityManager em) {
        this.em = em;
    }

    public List<EIntervalliDisponibilita> getIntervalliByOffice(EUfficio ufficio) {
        return em.createQuery(
                "SELECT i FROM EIntervalliDisponibilita i WHERE i.ufficio = :ufficio", EIntervalliDisponibilita.class)
                .setParameter("ufficio", ufficio)
                .getResultList();
    }
}
