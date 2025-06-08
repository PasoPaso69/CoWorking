/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.Model.entity.EPrenotazione;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Model.enums.FasciaOrariaEnum;
import com.mycompany.coworking1.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author 39327
 */
public class PrenotazioneDaoImpl implements PrenotazioneDao {
    
    private final EntityManager em;

    public PrenotazioneDaoImpl(EntityManager em) {
        this.em = em;
    }
     @Override
    public long getActiveReservationsByOfficeDateSlot(EUfficio office, LocalDate date, String fascia) {
        
        FasciaOrariaEnum fasciaEnum = FasciaOrariaEnum.valueOf(fascia.toUpperCase());
        String jpql = "SELECT COUNT(r.id) FROM EPrenotazione r " +
                      "WHERE FUNCTION('DATE',r.data ) = :date AND r.ufficio = :office AND r.fascia = :fascia";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("date", date)
                .setParameter("office", office)
                .setParameter("fascia", fasciaEnum)
                .getSingleResult();

        return count != null ? count : 0L;
    }
    
    @Override
      public List<EPrenotazione> getReservationbyUser(EProfilo user) {
    return em.createQuery(
        "SELECT DISTINCT p FROM EPrenotazione p " +
        "JOIN FETCH p.ufficio u " +
        "LEFT JOIN FETCH u.foto " +  // 👈 carica le foto dell’ufficio
        "WHERE p.utente = :utente", EPrenotazione.class)
        .setParameter("utente", user)
        .getResultList();
}
}
