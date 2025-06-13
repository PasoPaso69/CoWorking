/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.DAO.SegnalazioniDao;

import com.mycompany.coworking1.Model.entity.ESegnalazione;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author 39327
 */
public class SegnalazioniDaoImpl implements SegnalazioniDao {
    
     private final EntityManager em;

    public SegnalazioniDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public List<ESegnalazione> getReportbyDb(String idUfficio){
        
        String jpql ="SELECT f FROM ESegnalazione f WHERE f.ufficio.id = :id AND f.solved = false";
        
         
        List<ESegnalazione> Report =  em.createQuery(jpql, ESegnalazione.class)
                .setParameter("id",idUfficio)
                .getResultList();
        
        return Report ;
    }
    
}
