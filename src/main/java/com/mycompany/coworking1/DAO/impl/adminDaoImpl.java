/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.DAO.adminDao;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Model.enums.StatoUfficioEnum;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author 39327
 */
public class adminDaoImpl implements adminDao {
    
   
         private final EntityManager em;

    public adminDaoImpl(EntityManager em) {
        this.em = em;
    }
    //find the office by the state
    @Override 
    public List<EUfficio> findByStato(StatoUfficioEnum stato) {
    return em.createQuery(
        "SELECT u FROM EUfficio u WHERE u.stato = :stato AND u.isHidden = false", EUfficio.class)
        .setParameter("stato", stato)
        .getResultList();
}
    //find the office that is hidden
    @Override
public List<EUfficio> findByHiddenTrue() {
    return em.createQuery("SELECT u FROM EUfficio u WHERE u.isHidden = true", EUfficio.class)
             .getResultList();
}
    
}
