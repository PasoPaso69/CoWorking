/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.coworking1.DAO;

import com.mycompany.coworking1.DAO.impl.FotoDaoimpl;
import com.mycompany.coworking1.Model.entity.EFoto;
import com.mycompany.coworking1.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author 39327
 */
public interface FotoDao {
    
    

   
    

     public List<EFoto> getFotobyDb(String idUfficio);
}
