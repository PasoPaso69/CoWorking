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
import javax.servlet.http.HttpSession;

/**
 *
 * @author 39327
 */
//this servlet allow to the admin to reactivate office if is delete
@WebServlet("/admin/office/restore")
public class adminRestoreOfficeController extends BaseController {
    
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session = request.getSession(false);
    if (session == null) {
             response.sendRedirect(request.getContextPath() + "/login");
         }
        
        EntityManager em = (EntityManager) request.getAttribute("em");
        
        String idOffice = request.getParameter("id");
        
        EUfficio office =em.find(EUfficio.class , idOffice);
        
        em.getTransaction().begin();
        
        office.setDataCancellazione(null);
        
        office.setHidden(false);
        
        em.getTransaction().commit();
        
        response.sendRedirect(request.getContextPath() + "/home-admin");
    }
}
