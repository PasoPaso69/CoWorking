/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller.admin;

import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.DAO.adminDao;
import com.mycompany.coworking1.DAO.impl.adminDaoImpl;
import com.mycompany.coworking1.Model.entity.EUfficio;
import static com.mycompany.coworking1.Model.enums.StatoUfficioEnum.Approvato;
import static com.mycompany.coworking1.Model.enums.StatoUfficioEnum.NonApprovato;
import static com.mycompany.coworking1.Model.enums.StatoUfficioEnum.InAttesa;
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
@WebServlet("/home-admin")
public class homeAdminController extends BaseController {
     private Configuration cfg;
//inizializza freemarker
    @Override
    public void init() throws ServletException {
      
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    //this take the data on db of deleteoffice pending office, rejected office,approved office ,notapporved office
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
            //check the login because a user don't can reservate an office if not is logged
         if (session == null) {
             resp.sendRedirect(req.getContextPath() + "/login");
         }
        //take the entity manager
     EntityManager em = (EntityManager) req.getAttribute("em");
     resp.setContentType("text/html;charset=UTF-8");
     adminDao adminDao = new adminDaoImpl(em);
      try{
          //query to found office checking the state
      List<EUfficio> deletedOffice = adminDao.findByHiddenTrue();
      List<EUfficio> approvedOffice = adminDao.findByStato(Approvato);
      List<EUfficio> rejectedOffice = adminDao.findByStato(NonApprovato);
      List<EUfficio> pendingOffice = adminDao.findByStato(InAttesa);
        // si passano i dati
         Map<String, Object> data = new HashMap<>();
         data.put("deletedOffice", deletedOffice);
         data.put("approvedoffice", approvedOffice);
         data.put("rejectedOffice", rejectedOffice);
         data.put("pendingOffice", pendingOffice);
         data.put("ctx", req.getContextPath());
         
         //call the template
         
         Template template = cfg.getTemplate("admin/homeadmin.ftl");

      
        resp.setContentType("text/html;charset=UTF-8");

        try (Writer out = resp.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }

    } catch (Exception e) {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: " + e.getMessage());
    } finally {
        em.close();
    }
         
      
    }
    
}
