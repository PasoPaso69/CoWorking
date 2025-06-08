/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller;

/**
 *
 * @author 39327
 */

import com.mycompany.coworking1.DAO.FotoDao;
import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.DAO.UfficioDao;
import com.mycompany.coworking1.DAO.impl.FotoDaoimpl;
import com.mycompany.coworking1.DAO.impl.PrenotazioneDaoImpl;
import com.mycompany.coworking1.DAO.impl.ProfiloDaoImpl;
import com.mycompany.coworking1.DAO.impl.UfficioDaoImpl;
import javax.servlet.ServletException;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jakarta.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import com.mycompany.coworking1.Model.entity.EFoto;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Service.impl.ProfiloServiceImpl;
import com.mycompany.coworking1.util.EntityManagerUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.persistence.Persistence;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

   @WebServlet("/home-utente/search")
public class SearchController extends BaseController {
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
    
        String query = request.getParameter("query");
    String dateStr = request.getParameter("date");
    String slot = request.getParameter("slot");

         

    // Instanzia DAO passando l'EntityManager
    FotoDao fotoDao = new FotoDaoimpl(em);
    UfficioDao ufficioDao = new UfficioDaoImpl(em);
    PrenotazioneDao prenotazioneDao = new PrenotazioneDaoImpl(em);

    List<Map<String, Object>> officeWithPhotoList = new ArrayList<>();

    try {
        LocalDate date = LocalDate.parse(dateStr);

        // Usa il DAO per prendere la lista degli uffici filtrati
        List<EUfficio> uffici = ufficioDao.findByThree(query, date, slot);

        for (EUfficio u : uffici) {
            if (u.isHidden()) continue;

            // Conta prenotazioni attive tramite DAO
            long prenotazioni = prenotazioneDao.getActiveReservationsByOfficeDateSlot(u, date, slot);
          
            if (prenotazioni < u.getNumeroPostazioni()) {
              List<EFoto> foto = fotoDao.getFotobyDb(u.getId());
String photoUrl = null;

if (foto != null && !foto.isEmpty()) {
    EFoto photo = foto.get(0);
    photoUrl = request.getContextPath() + "/photo?id=" + photo.getId();
}
                Map<String, Object> entry = new HashMap<>();
                entry.put("ctx", request.getContextPath());
                entry.put("ufficio", u);
                entry.put("foto", photoUrl);
                officeWithPhotoList.add(entry);
            }
        }

        
        HttpSession session = request.getSession(false);

        String isLoggedIn = "notLoggedIn";
        String nome = null;
        String cognome = null;
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
        data.put("results", officeWithPhotoList);
        data.put("isloggedin", isLoggedIn );
        data.put("slot",slot);
        data.put("date",dateStr);

       data.put("ctx", request.getContextPath());
        // Fai forward alla pagina FreeMarker (o JSP)
        Template template = cfg.getTemplate("office/searchoffice.ftl");

        // Imposta la risposta
        response.setContentType("text/html;charset=UTF-8");

        try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }

    } catch (Exception e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: " + e.getMessage());
    } finally {
        em.close();
    }
    }
   }


