
package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.DAO.UfficioDao;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Model.enums.FasciaOrariaEnum;
import com.mycompany.coworking1.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 39327
 */
public class UfficioDaoImpl  implements UfficioDao{
    private EntityManager em;

    public UfficioDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    //this query found the office on three date query string that can is a place or name of office date and slot(morning or afternoon)
    @Override
    public List<EUfficio> findByThree(String queryString, LocalDate data, String fascia) {
        EntityManager entitymanager = EntityManagerUtil.getEntityManager();
        
         LocalDateTime startOfDay = data.atStartOfDay();
    LocalDateTime endOfDay = data.atTime(23, 59, 59);
    FasciaOrariaEnum fasciaEnum = FasciaOrariaEnum.valueOf(fascia.toUpperCase());
    
        String jpql = "SELECT e FROM EUfficio e " +
                "JOIN e.intervalliDisponibilita idisp " +
                "JOIN e.indirizzo indirizzo " +
                "WHERE (indirizzo.via = :query " +
                "OR indirizzo.citta = :query " +
                "OR indirizzo.provincia = :query " +
                "OR e.titolo = :query) " +
                "AND idisp.dataInizio <= :DateEnd " +
                "AND idisp.dataFine >= :DateStart  " +
                "AND idisp.fascia = :fascia";

        return entitymanager.createQuery(jpql, EUfficio.class)
                 .setParameter("query", queryString)
                 .setParameter("DateStart", startOfDay)
                 .setParameter("DateEnd",endOfDay)
                 .setParameter("fascia", fasciaEnum)
                 .getResultList();
    }
    
   @Override
    public List<EUfficio> getUfficiByLocatoreId(String idLocatore) {
    return em.createQuery(
        "SELECT u FROM EUfficio u WHERE u.locatore.id = :id AND u.isHidden = false", EUfficio.class)
        .setParameter("id", idLocatore)
        .getResultList();
}
    @Override
    public Long countByOffice(EUfficio ufficio) {
    String jpql = "SELECT COUNT(p.id) FROM EPrenotazione p WHERE p.ufficio = :ufficio";
    return em.createQuery(jpql, Long.class)
             .setParameter("ufficio", ufficio)
             .getSingleResult();
}


    
}

