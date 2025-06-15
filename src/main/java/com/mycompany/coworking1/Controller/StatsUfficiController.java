package com.mycompany.coworking1.Controller;

import com.google.gson.Gson;
import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.DAO.UfficioDao;
import com.mycompany.coworking1.DAO.impl.PrenotazioneDaoImpl;
import com.mycompany.coworking1.DAO.impl.UfficioDaoImpl;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.EUfficio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/api/grafici/utilizzoUffici")
public class StatsUfficiController extends BaseController {

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
            UfficioDao ufficioDao = new UfficioDaoImpl(em);

            // Recupera tutti gli uffici del locatore
            List<EUfficio> uffici = em.createQuery(
                    "SELECT u FROM EUfficio u WHERE u.locatore.id = :locatoreId", EUfficio.class)
                    .setParameter("locatoreId", idLocatore)
                    .getResultList();

            List<Map<String, Object>> lista = new ArrayList<>();
            for (EUfficio ufficio : uffici) {
                Long numeroPrenotazioni = ufficioDao.countByOffice(ufficio);
                Map<String, Object> map = new HashMap<>();
                map.put("nome", ufficio.getTitolo());
                map.put("numeroPrenotazioni", numeroPrenotazioni);
                lista.add(map);
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String json = new Gson().toJson(lista);
            resp.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            String errorJson = new Gson().toJson(Map.of("error", e.getMessage()));
            resp.getWriter().write(errorJson);
        } finally {
            em.close();
        }
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
}
