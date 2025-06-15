/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.DAO.RecensioniDao;
import com.mycompany.coworking1.Model.entity.ERecensione;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author 39327
 */
public class RecensioniDaoImpl implements RecensioniDao {
     private final EntityManager em;

    public RecensioniDaoImpl(EntityManager em) {
        this.em = em;
    }
    //take all the review from the db
    @Override
    public List<ERecensione> getReviewbyDb(String idUfficio){
        
        String jpql ="SELECT f FROM ERecensione f WHERE f.prenotazione.ufficio.id = :id";
        
         
        List<ERecensione> Review =  em.createQuery(jpql, ERecensione.class)
                .setParameter("id",idUfficio)
                .getResultList();
        
        return Review;
    }
    
}
