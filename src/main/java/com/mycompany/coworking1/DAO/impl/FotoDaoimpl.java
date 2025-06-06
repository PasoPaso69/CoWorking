/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.DAO.impl;

import com.mycompany.coworking1.DAO.FotoDao;
import com.mycompany.coworking1.Model.entity.EFoto;
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
    
    @Override
    public List<EFoto> getFotobyDb(String idUfficio){
         
         String jpql ="SELECT f FROM EFoto f WHERE f.ufficio.id = :id";
        
         
        List<EFoto>  photo =  em.createQuery(jpql, EFoto.class)
                .setParameter("id",idUfficio)
                .getResultList();
        
        return photo;
        
    }
    
  
}
