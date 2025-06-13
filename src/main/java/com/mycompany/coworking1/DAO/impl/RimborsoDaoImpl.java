/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.DAO.RimborsoDao;
import com.mycompany.coworking1.Model.entity.ERimborso;
import com.mycompany.coworking1.Model.entity.ESegnalazione;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author 39327
 */
public class RimborsoDaoImpl implements RimborsoDao{
    
      private final EntityManager em;

    public RimborsoDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public List<ERimborso> getReimbursementbyDb(String idUfficio){
        
        String jpql ="SELECT f FROM ERimborso f WHERE f.segnalazione.ufficio.id = :id";
        
         
        List<ERimborso> Reimbursement =  em.createQuery(jpql, ERimborso.class)
                .setParameter("id",idUfficio)
                .getResultList();
        
        return Reimbursement ;
    }
}
