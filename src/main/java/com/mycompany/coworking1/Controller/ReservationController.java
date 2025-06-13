/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller;

import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.DAO.UfficioDao;
import com.mycompany.coworking1.DAO.impl.PrenotazioneDaoImpl;
import com.mycompany.coworking1.DAO.impl.UfficioDaoImpl;
import com.mycompany.coworking1.Model.entity.EPagamento;
import com.mycompany.coworking1.Model.entity.EPrenotazione;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.ESegnalazione;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Model.enums.FasciaOrariaEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
        EntityManager em = (EntityManager) request.getAttribute("em");
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
         
          
            EUfficio office = em.find(EUfficio.class, idufficio);
             
            long prenotazioni = prenotazioneDao.getActiveReservationsByOfficeDateSlot(office, date, slot);
               data.put("nome", nome);
                data.put("cognome", cognome);
                data.put("isloggedin",isLoggedIn);
                data.put("ctx", request.getContextPath());
             if (prenotazioni >= office.getNumeroPostazioni() || office.isHidden()){
                 
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
                
                EPagamento payment = new EPagamento();
                payment.setImporto(office.getPrezzo());
                payment.setPrenotazione(prenotazione);
                
              em.persist(prenotazione);
              
              em.persist(payment);
              
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManager em = (EntityManager) request.getAttribute("em");
        try{
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
            PrenotazioneDao reservationDao = new PrenotazioneDaoImpl(em);
             
           List<EPrenotazione> reservations = new ArrayList<>();
           
           reservations= reservationDao.getReservationbyUser(user);
          
            List<Map<String, Object>> active = new ArrayList<>();
            List<Map<String, Object>> old = new ArrayList<>();

            LocalDate oggi = LocalDate.now();

     

for (EPrenotazione p : reservations) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("reservation", p);

    EUfficio ufficio = p.getUfficio();
    entry.put("ufficio", ufficio);

    // Seleziona la prima foto (se esiste)
    if (ufficio.getFoto() != null && !ufficio.getFoto().isEmpty()) {
        entry.put("foto", ufficio.getFoto().stream()
                                  .sorted(Comparator.comparing(f -> f.getId()))  
                                  .findFirst().orElse(null));
    } else {
        entry.put("foto", null);
    }

    if (p.getData().toLocalDate().isBefore(oggi)) {
        old.add(entry);
    } else {
        active.add(entry);
    }
}

data.put("activereservation", active);
data.put("oldreservation", old);

           
          
Template template = cfg.getTemplate("reservations/showReservations.ftl");
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
