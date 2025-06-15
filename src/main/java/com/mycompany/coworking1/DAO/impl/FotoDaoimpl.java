/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.DAO.FotoDao;
import com.mycompany.coworking1.Model.entity.EFoto;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author 39327
 */
public class FotoDaoimpl  implements FotoDao{
    private final EntityManager em;

    public FotoDaoimpl(EntityManager em) {
        this.em = em;
    }
    // take the foto from the db with the office id in input
    @Override
    public List<EFoto> getFotobyDb(String idUfficio){
         
         String jpql ="SELECT f FROM EFoto f WHERE f.ufficio.id = :id";
        
         
        List<EFoto>  photo =  em.createQuery(jpql, EFoto.class)
                .setParameter("id",idUfficio)
                .getResultList();
        
        return photo;
        
    }
    
     public EFoto getFirstPhotoByOffice(EUfficio ufficio) {
        List<EFoto> result = em.createQuery(
                "SELECT f FROM EFoto f WHERE f.ufficio = :ufficio ORDER BY f.id ASC", EFoto.class)
                .setParameter("ufficio", ufficio)
                .setMaxResults(1)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
  
}
