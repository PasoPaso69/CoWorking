/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.coworking1.DAO;
import com.mycompany.coworking1.Model.entity.EProfilo;

/**
 *
 * @author 39327
 */
public interface ProfiloDao {
    //find the user by the username
    EProfilo findbyUsername(String username);
    
    // save the user
    void save(EProfilo user);
}
