/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller.User;

import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.Model.entity.EPrenotazione;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.ERecensione;
import com.mycompany.coworking1.Model.entity.ESegnalazione;
import com.mycompany.coworking1.Model.entity.EUfficio;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
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
@WebServlet("/home-utente/Reservation/Review")
public class ShowFormReview extends BaseController {
    

       private Configuration cfg;

    @Override
    public void init() throws ServletException {
        //start freemarker

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //call the entity manager
        EntityManager em = (EntityManager) request.getAttribute("em");
        //call the user in session
        HttpSession session = request.getSession(false);
        //take the parameter to find reservation
        String idreservation = request.getParameter("idreservation");
        String isLoggedIn = "notLoggedIn";
        String nome = "";
        String cognome = "";
        Map<String, Object> data = new HashMap<>();
        //check if the user is logged
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
            
            data.put("ctx",request.getContextPath());
            data.put("isloggedin",isLoggedIn);
            data.put("idreservation", idreservation);
            
            data.put("ctx", request.getContextPath());
            Template template = cfg.getTemplate("User/Review/SendReview.ftl");

        // call the template
        response.setContentType("text/html;charset=UTF-8");

        try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
         }else{response.sendRedirect(request.getContextPath() + "/login");}
    }
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
     EntityManager em = (EntityManager) request.getAttribute("em");

        try {
            //take parameter from the request
            String idreservation = request.getParameter("idreservation");
            String votoString = request.getParameter("voto");
            String commento = request.getParameter("review");
            int voto = Integer.parseInt(votoString);
          

            // take comment from the form
           
             String isLoggedIn="notIsloggedin";
             String nome=null;
             String cognome=null;
            
                Map<String, Object> data = new HashMap<>();
               // take the user in session and check if is logged
            HttpSession session = request.getSession(false);
         if (session != null) {
            Object userObj = session.getAttribute("user");
            
                isLoggedIn = "isLoggedIn";
                EProfilo user = (EProfilo) userObj;
                nome = user.getName();
                cognome = user.getSurname(); 
                data.put("nome", nome);
                data.put("cognome", cognome);
                data.put("isloggedin",isLoggedIn);
                data.put("ctx", request.getContextPath());
                //find the reservtion
                EPrenotazione reservation = em.find(EPrenotazione.class, idreservation);
                
                String idufficio =reservation.getUfficio().getId();
            
           
            em.getTransaction().begin();

            // take the office from the db
            EUfficio ufficio = em.find(EUfficio.class, idufficio);
            if (ufficio == null) {
                em.getTransaction().rollback();
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ufficio non trovato");
                return;
            }

            // create a review
            ERecensione report = new ERecensione();
            report.setPrenotazione(reservation);
            report.setValutazione(voto);
            report.setCommento(commento);

            em.persist(report);
            em.getTransaction().commit();
            
          //call the template
        Template template = cfg.getTemplate("User/confirm/ConfirmReview.ftl");
       
        response.setContentType("text/html;charset=UTF-8");
            try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }

        }else{response.sendRedirect(request.getContextPath() + "/login");}
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ServletException(e);
        }
    }
}
    

