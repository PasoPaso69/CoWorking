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

/**
 *
 * @author 39327
 */
@WebServlet("/admin/office/reimbursement")
public class reimbursementController extends BaseController {
    private Configuration cfg;

    @Override
    public void init() throws ServletException {
        

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    
    
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        EntityManager em = (EntityManager) req.getAttribute("em");
        
       String idReport = req.getParameter("idReport");
       
       String idoffice = req.getParameter("id");
        
        ESegnalazione Report =em.find(ESegnalazione.class , idReport);
        
        EUfficio office = em.find(EUfficio.class, idoffice);
        
        em.getTransaction().begin();
        
        ERimborso reimbursement = new ERimborso();
        
        reimbursement.setImporto(office.getPrezzo());
        
        reimbursement.setSegnalazione(Report);
        
        em.persist(reimbursement);
        
        em.getTransaction().commit();
        
        resp.sendRedirect(req.getContextPath() + "/home-admin");
    }
     
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManager em = (EntityManager) request.getAttribute("em");
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
