package com.mycompany.coworking1.Controller.User;


import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.Model.entity.EPrenotazione;
import com.mycompany.coworking1.Model.entity.EProfilo;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 39327
 */
@WebServlet("/home-utente/search/showoffice/Detailsreservation")
public class ShowReservationController extends BaseController{
      private Configuration cfg;
    //start freemarker
    @Override
    public void init() throws ServletException {
        

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    
    //this servlet show the reservationdetails when the user click visualizza
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //take the entity manager
        EntityManager em = (EntityManager) request.getAttribute("em");
        //take the reservation id that was passed for request
        String idreservation = request.getParameter("idreservation");
        //take the session
        HttpSession session = request.getSession(false);
        
        String isLoggedIn = "notLoggedIn";
        String nome = "";
        String cognome = "";
         Map<String, Object> data = new HashMap<>();
        try{
            
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
            
            //call reservation and office from the db
            EPrenotazione reservation = em.find(EPrenotazione.class, idreservation);
            EUfficio office = em.find(EUfficio.class, reservation.getUfficio().getId());
            
            data.put("isloggedin",isLoggedIn);
            data.put("reservation", reservation);
            data.put("office", office);
            data.put("ctx", request.getContextPath());
            
            //call to the template
            Template template = cfg.getTemplate("User/reservations/showreservationdetails.ftl");
              response.setContentType("text/html;charset=UTF-8");

        try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
         }else{response.sendRedirect(request.getContextPath() + "/login");}
            }catch (Exception e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: " + e.getMessage());
    } 


    
    }}
