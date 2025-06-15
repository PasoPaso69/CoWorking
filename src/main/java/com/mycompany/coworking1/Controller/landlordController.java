/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */


/**
 *
 * @author Lenovo
 */
package com.mycompany.coworking1.Controller;

import com.mycompany.coworking1.Model.entity.ELocatore;
import com.mycompany.coworking1.Model.entity.EProfilo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/profilo")
public class landlordController extends BaseController {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoWirking1PU");
    private Configuration cfg;

    @Override
    public void init() throws ServletException {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
    }

    private String getIdLocatoreFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Object user = session.getAttribute("user");
            if (user instanceof EProfilo) {
                return ((EProfilo) user).getId();
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    EntityManager em = emf.createEntityManager();

    try (Writer out = resp.getWriter()) {
        String idLocatore = getIdLocatoreFromSession(req);
        if (idLocatore == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        ELocatore locatore = em.find(ELocatore.class, idLocatore);
        if (locatore == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

       Map<String, Object> data = new HashMap<>();
data.put("ctx", req.getContextPath());
data.put("nome", locatore.getName());  // getName() viene da EProfilo
data.put("cognome", locatore.getSurname());  // getSurname() viene da EProfilo
data.put("telefono", locatore.getPhone());  // getPhone() viene da EProfilo
 // getDob() viene da EProfilo
data.put("partitaIva", locatore.getPartitaIva()); 

        Template template = cfg.getTemplate("locatore/profiloLocatore/profilo_locatore.ftl");
        template.process(data, out);

    } catch (Exception e) {
        throw new ServletException("Errore nel caricamento della pagina profilo", e);
    } finally {
        em.close();
    }
}
}

