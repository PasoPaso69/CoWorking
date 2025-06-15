/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller.admin;

import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.DAO.RimborsoDao;
import com.mycompany.coworking1.DAO.SegnalazioniDao;
import com.mycompany.coworking1.DAO.impl.RimborsoDaoImpl;
import com.mycompany.coworking1.DAO.impl.SegnalazioniDaoImpl;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.ERimborso;
import com.mycompany.coworking1.Model.entity.ESegnalazione;
import com.mycompany.coworking1.Model.entity.EUfficio;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 39327
 */

@WebServlet("/admin/office/reimbursement")
public class reimbursementController extends BaseController {
    private Configuration cfg;

    //start freemarker
    @Override
    public void init() throws ServletException {
        

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
   // with this post yhe admin can create a reimbursement to answer a report
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //check the login of admin and if is an admin(check for the root)
           HttpSession session = request.getSession(false);
    if (session == null) {
             response.sendRedirect(request.getContextPath() + "/login");
         }else{
        Object userObj = session.getAttribute("user");
        EProfilo user = (EProfilo) userObj;
        if(user.isAdmin()==false){
           response.sendRedirect(request.getContextPath() + "/logout"); 
        }
    }
        
        EntityManager em = (EntityManager) request.getAttribute("em");
        
       String idReport = request.getParameter("idReport");
       
       String idoffice = request.getParameter("id");
        
        ESegnalazione Report =em.find(ESegnalazione.class , idReport);
        
        EUfficio office = em.find(EUfficio.class, idoffice);
        
        em.getTransaction().begin();
        
        ERimborso reimbursement = new ERimborso();
        
        reimbursement.setImporto(office.getPrezzo());
        
        reimbursement.setSegnalazione(Report);
        
        Report.setSolved(true);
        
        em.persist(reimbursement);
        
        em.persist(Report);
        
        em.getTransaction().commit();
        
        response.sendRedirect(request.getContextPath() + "/home-admin");
    }
     //permette di mostrare tutti i template
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         //check the login of admin and if is an admin(check for the root)
        HttpSession session = request.getSession(false);
    if (session == null) {
             response.sendRedirect(request.getContextPath() + "/login");
         }else{
        Object userObj = session.getAttribute("user");
        EProfilo user = (EProfilo) userObj;
        if(user.isAdmin()==false){
           response.sendRedirect(request.getContextPath() + "/logout"); 
        }
    }        EntityManager em = (EntityManager) request.getAttribute("em");
        try{
        RimborsoDao  reimbursementDao = new RimborsoDaoImpl(em);
        
        String officeId = request.getParameter("id");
        
        EUfficio office = em.find(EUfficio.class, officeId);
        
       List<ERimborso> reimbursements = reimbursementDao.getReimbursementbyDb(officeId);
       
       
       Map<String, Object> data = new HashMap<>();
       
       data.put("office", office);
       
       data.put("reimbursements", reimbursements);
      
        data.put("ctx", request.getContextPath());
            Template template = cfg.getTemplate("admin/ReimbusamentOffice.ftl");
            response.setContentType("text/html;charset=UTF-8");

        try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
        
        }catch (Exception e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: " + e.getMessage());
    } 
       }
}
