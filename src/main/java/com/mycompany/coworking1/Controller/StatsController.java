package com.mycompany.coworking1.Controller;

import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.DAO.impl.PrenotazioneDaoImpl;

import com.mycompany.coworking1.Model.entity.EProfilo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/grafici/entrate-mensili")
public class StatsController extends BaseController {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoWirking1PU");

    @Override
    
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String idLocatore = getIdLocatoreFromSession(req);
    if (idLocatore == null) {
        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Utente non autenticato");
        return;
    }

    EntityManager em = emf.createEntityManager();
    try {
        PrenotazioneDao prenotazioneDao = new PrenotazioneDaoImpl(em);
        List<Object[]> results = prenotazioneDao.getEntrateMensili(idLocatore);

        List<Map<String, Object>> lista = new ArrayList<>();
        for (Object[] r : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("mese", r[0]);
            map.put("entrate", ((Number) r[1]).intValue());
            lista.add(map);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String json = new com.google.gson.Gson().toJson(lista);
        resp.getWriter().write(json);

    } catch (Exception e) {
        e.printStackTrace();  // stampa l’errore nei log di Tomcat

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        String errorJson = new com.google.gson.Gson().toJson(Map.of("error", e.getMessage()));
        resp.getWriter().write(errorJson);

    } finally {
        em.close();
    }
}


    // Usa il metodo già presente in BaseController o come nel tuo OfficeController
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
}


