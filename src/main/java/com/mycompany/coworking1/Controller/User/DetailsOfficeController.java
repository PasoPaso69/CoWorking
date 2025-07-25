/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller.User;

import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.DAO.FotoDao;
import com.mycompany.coworking1.DAO.impl.FotoDaoimpl;
import com.mycompany.coworking1.Model.entity.EFoto;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.EUfficio;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
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
@WebServlet("/home-utente/search/Detailsoffice")
public class DetailsOfficeController extends BaseController {
     private Configuration cfg;
     //start freemarker
    @Override
    public void init() throws ServletException {
     
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    //this is one of the most important servlet and show the details of office when a user search them
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManager em = (EntityManager) request.getAttribute("em");
           FotoDao fotoDao = new FotoDaoimpl(em);
           //take the parameter
           String idufficio = request.getParameter("idufficio");
           String dateStr = request.getParameter("date");
           String slot = request.getParameter("slot");
           
           //find the office
           EUfficio office = em.find(EUfficio.class,idufficio);
           //take the photo by DB
            List<EFoto> foto = fotoDao.getFotobyDb(office.getId());
            
            List<String> photoUrlList = new ArrayList<>();
            try {
            for (EFoto u : foto){
                //create the context path to show foto in template
                 String photoUrl = (u != null) ? request.getContextPath()+"/photo?id=" + u.getId()
                        : null;
                photoUrlList.add(photoUrl);
                
            }  //call the sesssion of utente but with false don't create another session
                HttpSession session = request.getSession(false);

        String isLoggedIn = "notLoggedIn";
        String nome = "";
        String cognome = "";
        Map<String, Object> data = new HashMap<>();
        
         if (session != null) {
            Object userObj = session.getAttribute("user");
            if (userObj != null && userObj instanceof EProfilo) {
                isLoggedIn = "isLoggedIn";
                EProfilo user = (EProfilo) userObj;
                nome = user.getName();
                cognome = user.getSurname(); 
                data.put("nome", nome);
              data.put("cognome", cognome);
            }
            }
            data.put("isloggedin",isLoggedIn);
            data.put("ufficio", office);
            data.put("foto", photoUrlList);
            data.put("date",dateStr);
            data.put("slot",slot);
            data.put("ctx", request.getContextPath());
            Template template = cfg.getTemplate("User/office/DetailsOffice.ftl");

        // Imposta la risposta
        response.setContentType("text/html;charset=UTF-8");
         
        try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }

            }catch (Exception e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: " + e.getMessage());
    } finally {
        em.close();
    }
       
}
}
