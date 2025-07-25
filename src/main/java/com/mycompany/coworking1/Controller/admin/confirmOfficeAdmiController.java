/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller.admin;

import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.DAO.SegnalazioniDao;
import com.mycompany.coworking1.DAO.impl.SegnalazioniDaoImpl;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.ESegnalazione;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Model.enums.StatoUfficioEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
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

@WebServlet("/actionOffice")
public class confirmOfficeAdmiController extends BaseController {
    private Configuration cfg;
//start freemarker
    @Override
    public void init() throws ServletException {
        

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    //this post approved and office
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        //take the id of office and a action to do 
        String officeId = request.getParameter("id");
        
        String action = request.getParameter("action");
        
        // if action is "reject" -> the office is deleted or if the action is "approvato" -> the office is approved
        EUfficio office = em.find(EUfficio.class,officeId );
        em.getTransaction().begin();
        switch (action){
            case "reject":
                String reason = request.getParameter("reason");
                office.setMotivoRifiuto(reason);
                office.setStato(StatoUfficioEnum.NonApprovato);
                office.setDataRifiuto(LocalDateTime.now());
                break;
            case "approvato":
                office.setMotivoRifiuto(null);
                office.setStato(StatoUfficioEnum.Approvato);
                office.setDataRifiuto(null);
                office.setDataApprovazione(LocalDateTime.now());
                break;
            default:
                em.getTransaction().rollback();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Azione non valida.");
                return;    
                
        }
         em.getTransaction().commit();
        
        
        response.sendRedirect(request.getContextPath() + "/home-admin");
      }
      //this getmethod call all the reports for office id and show them
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
    }
        EntityManager em = (EntityManager) request.getAttribute("em");
        try{
        SegnalazioniDao  reportDao = new SegnalazioniDaoImpl(em);
        
        String officeId = request.getParameter("id");
        
        EUfficio office = em.find(EUfficio.class, officeId);
        //create a list of report
       List<ESegnalazione> reports = reportDao.getReportbyDb(officeId);
       
       
       Map<String, Object> data = new HashMap<>();
       
       data.put("office", office);
       
       data.put("reports", reports);
       
        data.put("ctx", request.getContextPath());
            Template template = cfg.getTemplate("admin/ReportOffice.ftl");
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
