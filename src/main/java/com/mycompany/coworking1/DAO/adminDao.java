/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.coworking1.DAO;

import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Model.enums.StatoUfficioEnum;
import java.util.List;

/**
 *
 * @author 39327
 */
public interface adminDao {
    
     public List<EUfficio> findByStato(StatoUfficioEnum stato);
     
     public List<EUfficio> findByHiddenTrue();
    
}
