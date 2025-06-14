/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller.admin;

import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.Model.entity.EUfficio;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 39327
 */
//this servlet allow to the admin to reactivate office if is delete
@WebServlet("/admin/office/restore")
public class adminRestoreOfficeController extends BaseController {
    
     
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        EntityManager em = (EntityManager) req.getAttribute("em");
        
       String idOffice = req.getParameter("id");
        
        EUfficio office =em.find(EUfficio.class , idOffice);
        
        em.getTransaction().begin();
        
        office.setDataCancellazione(null);
        
        office.setHidden(false);
        
        em.getTransaction().commit();
        
        resp.sendRedirect(req.getContextPath() + "/home-admin");
    }
}
