/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller.User;

import com.mycompany.coworking1.Controller.BaseController;
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
   @WebServlet("/home-utente/search/showoffice/Detailsoffice/confirm")
        
public class ReservationController extends BaseController {
       private Configuration cfg;
     public void init() throws ServletException {
        

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
     
     //this function allow to the user to reservate an office
      @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //take the data and entitymanager
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
            //check the login because a user don't can reservate an office if not is logged
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
         
         //find the office to start the transaction of creation reservation 
            EUfficio office = em.find(EUfficio.class, idufficio);
             //take the number of reservation for this office in a date and slot
            long prenotazioni = prenotazioneDao.getActiveReservationsByOfficeDateSlot(office, date, slot);
                data.put("nome", nome);
                data.put("cognome", cognome);
                data.put("isloggedin",isLoggedIn);
                data.put("ctx", request.getContextPath());
                
                //check if the office is full
             if (prenotazioni >= office.getNumeroPostazioni() || office.isHidden()){
                 
                 Template template = cfg.getTemplate("User/confirm/PlaceNotAvaible.ftl");
                 
        // Imposta la risposta
        response.setContentType("text/html;charset=UTF-8");
            try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
             }else{
                 
                 //create reservation
             
                EPrenotazione prenotazione = new EPrenotazione();
                prenotazione.setData(endOfDay);
                prenotazione.setUfficio(office);
                prenotazione.setFascia(slotEnum);
                prenotazione.setUtente(user);
                //create payment
                EPagamento payment = new EPagamento();
                payment.setImporto(office.getPrezzo());
                payment.setPrenotazione(prenotazione);
                
              em.persist(prenotazione);
              
              em.persist(payment);
              
              em.getTransaction().commit();
            
             
            
Template template = cfg.getTemplate("User/confirm/confirmReservation.ftl");
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
    //this getMethod show the reservation for every user that is logged
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManager em = (EntityManager) request.getAttribute("em");
        try{
            //inizialized parameters
             String isLoggedIn="notIsloggedin";
             String nome=null;
             String cognome=null;
            
                Map<String, Object> data = new HashMap<>();
                
            // take user from the session
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
           //take the reservation for every user so can show this in apposite page
           reservations= reservationDao.getReservationbyUser(user);
          //the reservation is divided in active and old
            List<Map<String, Object>> active = new ArrayList<>();
            List<Map<String, Object>> old = new ArrayList<>();

            LocalDate oggi = LocalDate.now();

     

for (EPrenotazione p : reservations) {
    
    Map<String, Object> entry = new HashMap<>();
    entry.put("reservation", p);

    EUfficio ufficio = p.getUfficio();
    entry.put("ufficio", ufficio);

    // use the first foto
    if (ufficio.getFoto() != null && !ufficio.getFoto().isEmpty()) {
        entry.put("foto", ufficio.getFoto().stream()
                                  .sorted(Comparator.comparing(f -> f.getId()))  
                                  .findFirst().orElse(null));
    } else {
        entry.put("foto", null);
    }
//take the active and old reservation doing a check on date of today
    if (p.getData().toLocalDate().isBefore(oggi)) {
        old.add(entry);
    } else {
        active.add(entry);
    }
}

data.put("activereservation", active);
data.put("oldreservation", old);

           
      //call to template    
Template template = cfg.getTemplate("User/reservations/showReservations.ftl");
        // Imposta la risposta
        response.setContentType("text/html;charset=UTF-8");
            try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
     //if the user don't is logged was redirect to the login
        }else{response.sendRedirect(request.getContextPath() + "/login");}
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ServletException(e);
        }
    }
   }
