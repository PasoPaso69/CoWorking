/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller.admin;

import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.DAO.RecensioniDao;
import com.mycompany.coworking1.DAO.impl.PrenotazioneDaoImpl;

import com.mycompany.coworking1.DAO.impl.RecensioniDaoImpl;
import com.mycompany.coworking1.Model.entity.EProfilo;


import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Model.enums.StatoUfficioEnum;
import com.mycompany.coworking1.Service.PrenotazioneService;

import com.mycompany.coworking1.Service.impl.PrenotazioneServiceImpl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 39327
 */
@WebServlet("/admin/office")
public class adminOffice extends BaseController {
    //serve a richiamare il configuratore di freekmarker
    
     private Configuration cfg;
     
     //call the service of reservation
      private PrenotazioneService ReservationService;
      
      //start freemarker
    @Override
    public void init() throws ServletException {
     
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //call entity manager from the bse controller
        EntityManager em = (EntityManager) request.getAttribute("em");
        //call the dao of reservation to use the query on reservation
        PrenotazioneDao reservationDao = new PrenotazioneDaoImpl(em);
        //take the reservation serivce where there is the operation on reservation
         this.ReservationService = new PrenotazioneServiceImpl(new PrenotazioneDaoImpl(em));
        try{
         //take the id office form the request
    String officeId = request.getParameter("id");
    if (officeId == null || officeId.isBlank()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID ufficio mancante o vuoto.");
        return;
    }

    
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


    // search office usgin the office id that was passed
    EUfficio office = em.find(EUfficio.class, officeId);
    if (office == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ufficio non trovato.");
        return;
    }
    //take the count of reservation for office
    int reservationCount= (reservationDao.getReservationbyoffice(office).size());
    //take the state of office and with this we do a check below
     StatoUfficioEnum status = office.getStato(); 
    
    String templateName;
    
     Map<String, Object> data = new HashMap<>();
     
     data.put("office",office);
     
     data.put("ctx", request.getContextPath());
     //is important to understand for what type of office show details
    switch (status.name().toLowerCase()) {
        case "approvato":
            templateName = "admin/approvedOfficeDetails.ftl";
             int year = LocalDate.now().getYear();
             int prezzo = office.getPrezzo();
             List<Integer> reservation = ReservationService.getMonthReservationCounts(officeId, year);//take the reservation for month
             //take the monthly revenue 
             List<Integer> revenue = ReservationService.getMonthlyRevenue(officeId, year, prezzo);
             data.put("reservationformonth", reservation);
             data.put("revenueformonth", revenue);
            data.put("reservationcount", reservationCount);
            break;
        case "inattesa":
            templateName = "admin/pendingOfficeDetails.ftl";
            break;
        case "nonapprovato":
            templateName = "admin/rejectedOfficeDetails.ftl";
            break;
        default:
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Stato ufficio sconosciuto.");
            return;
    }
    
    Template template = cfg.getTemplate(templateName);
    // call the template
     try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
        
            }catch (Exception e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: " + e.getMessage());
    } 
        
        
        
        
    }
     @Override
     //this post removed office when admin click "rimuovi" in approvedOfficeDetails.ftl
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session = request.getSession(false);
    if (session == null) {
             response.sendRedirect(request.getContextPath() + "/login");
         }
        
         EntityManager em = (EntityManager) request.getAttribute("em");
         String officeId = request.getParameter("id");
         PrenotazioneDao reservationDao = new PrenotazioneDaoImpl(em);
         RecensioniDao reviewDao = new RecensioniDaoImpl(em);
         EUfficio office= em.find(EUfficio.class, officeId);
         
         //inizialmente avevamo messo che l'ufficio veniva cancellato ma in realtà è meglio semplicemente renderlo nascosto 
         //e riattivarlo solo quando vuole l'admin
         /*
        List<EPrenotazione> prenotazioni = reservationDao.getReservationbyoffice(office);
        List<ERecensione>review = reviewDao.getReviewbyDb(officeId);
         */
         em.getTransaction().begin();
         
         /*
         for (ERecensione r : review){
             em.remove(r);
         }
         
         for (EPrenotazione p : prenotazioni) {
    em.remove(p);
      }    
         em.remove(office);
         
        */
         office.setHidden(true);
         office.setDataCancellazione(LocalDateTime.now());
        
         
         
         
         em.getTransaction().commit();
         response.sendRedirect(request.getContextPath() + "/home-admin");
        
    }
    
}
