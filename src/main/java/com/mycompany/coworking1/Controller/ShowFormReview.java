/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller;

import com.mycompany.coworking1.Model.entity.EPrenotazione;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.ERecensione;
import com.mycompany.coworking1.Model.entity.ESegnalazione;
import com.mycompany.coworking1.Model.entity.EUfficio;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
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
@WebServlet("/home-utente/reservation/Review")
public class ShowFormReview extends BaseController {
    

       private Configuration cfg;

    @Override
    public void init() throws ServletException {
        

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     
        HttpSession session = request.getSession(false);
String idreservation = request.getParameter("idreservation");
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
            
            data.put("ctx",request.getContextPath());
            data.put("isloggedin",isLoggedIn);
            data.put("idreservation", idreservation);
            
            data.put("ctx", request.getContextPath());
            Template template = cfg.getTemplate("Review/SendReview.ftl");

        // Imposta la risposta
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
        
     

        try {
            // Recupero parametro id dall'URL o dal form (ad esempio come parametro)
            String idreservation = request.getParameter("idreservation");
            String votoString = request.getParameter("voto");
            String commento = request.getParameter("review");
            int voto = Integer.parseInt(votoString);
          

            // Recupero commento dal form
           
             String isLoggedIn="notIsloggedin";
             String nome=null;
             String cognome=null;
            
                Map<String, Object> data = new HashMap<>();
            // Recupero utente dalla sessione
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
            EPrenotazione reservation = em.find(EPrenotazione.class, idreservation);
            String idufficio =reservation.getUfficio().getId();
            
            

            em.getTransaction().begin();

            // Recupero ufficio dal DB
            EUfficio ufficio = em.find(EUfficio.class, idufficio);
            if (ufficio == null) {
                em.getTransaction().rollback();
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ufficio non trovato");
                return;
            }

            // Creo nuova segnalazione
            ERecensione report = new ERecensione();
            report.setPrenotazione(reservation);
            report.setValutazione(voto);
            report.setCommento(commento);

            em.persist(report);
            em.getTransaction().commit();
          
Template template = cfg.getTemplate("confirm/ConfirmReview.ftl");
        // Imposta la risposta
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
    

