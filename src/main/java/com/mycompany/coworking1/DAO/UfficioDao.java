/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.coworking1.DAO;

import com.mycompany.coworking1.Model.entity.EUfficio;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author 39327
 */
public interface UfficioDao {
    
    public List<EUfficio> findByThree(String queryString, LocalDate data, String fascia);
    
    public List<EUfficio> getUfficiByLocatoreId(Long idLocatore);
    
}
