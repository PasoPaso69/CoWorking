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

/**
 *
 * @author 39327
 */
@WebServlet("/admin/office")
public class adminOffice extends BaseController {
     private Configuration cfg;
      private PrenotazioneService ReservationService;
     
    @Override
    public void init() throws ServletException {
     
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManager em = (EntityManager) request.getAttribute("em");
        PrenotazioneDao reservationDao = new PrenotazioneDaoImpl(em);
         this.ReservationService = new PrenotazioneServiceImpl(new PrenotazioneDaoImpl(em));
        try{
         // Recupera l'ID stringa dalla richiesta
    String officeId = request.getParameter("id");
    if (officeId == null || officeId.isBlank()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID ufficio mancante o vuoto.");
        return;
    }

    // Cerca l'ufficio usando la chiave primaria stringa
    EUfficio office = em.find(EUfficio.class, officeId);
    if (office == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ufficio non trovato.");
        return;
    }
    int reservationCount= (reservationDao.getReservationbyoffice(office).size());
    // Determina lo stato
     StatoUfficioEnum status = office.getStato(); 
     
    String templateName;
     Map<String, Object> data = new HashMap<>();
     data.put("office",office);
     data.put("ctx", request.getContextPath());
    switch (status.name().toLowerCase()) {
        case "approvato":
            templateName = "admin/approvedOfficeDetails.ftl";
             int year = LocalDate.now().getYear();
             int prezzo = office.getPrezzo();
             List<Integer> reservation = ReservationService.getMonthReservationCounts(officeId, year);
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
    // Imposta e mostra il template
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
         EntityManager em = (EntityManager) req.getAttribute("em");
         String officeId = req.getParameter("id");
         PrenotazioneDao reservationDao = new PrenotazioneDaoImpl(em);
         RecensioniDao reviewDao = new RecensioniDaoImpl(em);
         EUfficio office= em.find(EUfficio.class, officeId);
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
         resp.sendRedirect(req.getContextPath() + "/home-admin");
        
    }
    
}
