/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Service.impl;
import com.mycompany.coworking1.Service.ProfiloService;
import com.mycompany.coworking1.DAO.ProfiloDao;

import org.mindrot.jbcrypt.BCrypt;
import com.mycompany.coworking1.Model.entity.EProfilo;

/**
 *
 * @author 39327
 */
public class ProfiloServiceImpl implements ProfiloService{
    private ProfiloDao profilodao;
    
    public ProfiloServiceImpl(ProfiloDao profilodao){
        this.profilodao=profilodao;
    }
    
    @Override 
    public boolean isRegister(EProfilo user){
         String email = user.getEmail();
        if (profilodao.findbyUsername(email )!=null ){
            return false; //user is register
        } else {
            user.setPassword(hashPassword(user.getPassword()));
        profilodao.save(user);
        return true;
        }
    }
        
        @Override
        public EProfilo login(String username, String password) {
        EProfilo user = profilodao.findbyUsername(username);
        if (user != null && verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }
          private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    }
    
    
    
    

