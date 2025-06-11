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
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    LocalDateTime startOfDay = date.atStartOfDay();
    LocalDateTime endOfDay = date.atTime(23, 59, 59);

    String jpql = "SELECT COUNT(r.id) FROM EPrenotazione r " +
                  "WHERE r.data BETWEEN :start AND :end " +
                  "AND r.ufficio = :office AND r.fascia = :fascia";

    Long count = em.createQuery(jpql, Long.class)
            .setParameter("start", startOfDay)
            .setParameter("end", endOfDay)
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
        "LEFT JOIN FETCH u.foto " +  
        "WHERE p.utente = :utente", EPrenotazione.class)
        .setParameter("utente", user)
        .getResultList();
}
       @Override
      public List<EPrenotazione> getReservationbyoffice(EUfficio office) {
    return em.createQuery(
        "SELECT DISTINCT p FROM EPrenotazione p " +
        "WHERE p.ufficio = :ufficio", EPrenotazione.class)
        .setParameter("ufficio", office)
        .getResultList();
}         
      @Override
         public List<LocalDate> findReservationDatesByOfficeId(String officeId, int year) {
    String jpql = "SELECT b.data FROM EPrenotazione b WHERE b.ufficio.id = :officeId AND YEAR(b.data) = :year";
    
    List<LocalDateTime> dateTimes = em.createQuery(jpql, LocalDateTime.class)
                                      .setParameter("officeId", officeId)
                                      .setParameter("year", year)
                                      .getResultList();

    // Conversione in LocalDate
    return dateTimes.stream()
                    .map(LocalDateTime::toLocalDate)
                    .collect(Collectors.toList());
}

}
