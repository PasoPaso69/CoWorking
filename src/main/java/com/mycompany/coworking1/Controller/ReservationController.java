/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller;

import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.DAO.UfficioDao;
import com.mycompany.coworking1.DAO.impl.PrenotazioneDaoImpl;
import com.mycompany.coworking1.DAO.impl.UfficioDaoImpl;
import com.mycompany.coworking1.Model.entity.EPrenotazione;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.ESegnalazione;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Model.enums.FasciaOrariaEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.persistence.LockModeType;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
   @WebServlet("/home-utente/search/showoffice/detailsoffice/confirm")
        
public class ReservationController extends BaseController {
       private Configuration cfg;
     public void init() throws ServletException {
        

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
      @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         UfficioDao ufficioDao = new UfficioDaoImpl(em);
    PrenotazioneDao prenotazioneDao = new PrenotazioneDaoImpl(em);
      String idufficio = request.getParameter("idufficio");
            String dateStr = request.getParameter("date");
            String slot = request.getParameter("slot");

        try {
            
           FasciaOrariaEnum slotEnum = FasciaOrariaEnum.valueOf(slot.toUpperCase());
             LocalDate date = LocalDate.parse(dateStr);
             LocalDateTime endOfDay = date.atTime(23, 59, 59);
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
                
               em.getTransaction().begin();
         
          
            EUfficio office = em.find(EUfficio.class, idufficio,LockModeType.PESSIMISTIC_WRITE);
             
            long prenotazioni = prenotazioneDao.getActiveReservationsByOfficeDateSlot(office, date, slot);
               data.put("nome", nome);
                data.put("cognome", cognome);
                data.put("isloggedin",isLoggedIn);
                data.put("ctx", request.getContextPath());
             if (prenotazioni >= office.getNumeroPostazioni()){
                 Template template = cfg.getTemplate("confirm/PlaceNotAvaible.ftl");
        // Imposta la risposta
        response.setContentType("text/html;charset=UTF-8");
            try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
             }else{
                 
             
                EPrenotazione prenotazione = new EPrenotazione();
                prenotazione.setData(endOfDay);
                prenotazione.setUfficio(office);
                prenotazione.setFascia(slotEnum);
                prenotazione.setUtente(user);

            em.persist(prenotazione);
              em.getTransaction().commit();
            
             
            
Template template = cfg.getTemplate("confirm/confirmReservation.ftl");
        // Imposta la risposta
        response.setContentType("text/html;charset=UTF-8");
            try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
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
