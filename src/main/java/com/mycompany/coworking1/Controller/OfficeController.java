package com.mycompany.coworking1.Controller;

import com.mycompany.coworking1.DAO.UfficioDao;
import com.mycompany.coworking1.DAO.impl.UfficioDaoImpl;
import com.mycompany.coworking1.Model.entity.EIndirizzo;
import com.mycompany.coworking1.Model.entity.EFoto;
import com.mycompany.coworking1.Model.entity.EIntervalliDisponibilita;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.Model.entity.EServiziAggiuntivi;
import com.mycompany.coworking1.Model.enums.FasciaOrariaEnum;
import com.mycompany.coworking1.Model.enums.StatoUfficioEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;

import jakarta.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.time.LocalDate;
import java.util.*;

@WebServlet({"/aggiunta", "/gestione"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // max 5MB per file
public class OfficeController extends BaseController {

    private Configuration cfg;
    private UfficioDao ufficioDao;
    private EntityManager em;

    @Override
    public void init() throws ServletException {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");

        em = getEntityManager();
        ufficioDao = new UfficioDaoImpl(em);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        resp.setContentType("text/html;charset=UTF-8");
        Map<String, Object> data = new HashMap<>();
        data.put("ctx", req.getContextPath());

        try (Writer out = resp.getWriter()) {
            Template template;

            switch (path) {
                case "/aggiunta":
                    // Mostra il form per aggiunta ufficio
                    template = cfg.getTemplate("locatore/aggiuntaUfficio/aggiuntaUfficio.ftl");
                    break;

                case "/gestione":
                    // Mostra lista uffici del locatore
                    Long idLocatore = getIdLocatoreFromSession(req);
                    if (idLocatore == null) {
                        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Utente non autenticato");
                        return;
                    }
                    List<EUfficio> uffici = ufficioDao.getUfficiByLocatoreId(idLocatore);
                    data.put("uffici", uffici);
                    template = cfg.getTemplate("locatore/gestioneUffici/gestioneUffici.ftl");
                    break;

                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
            }

            template.process(data, out);

        } catch (Exception e) {
            throw new ServletException("Errore nel caricamento della pagina", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/aggiunta":
                handleAggiuntaPost(req, resp);
                break;

            case "/gestione":
                // Qui potresti gestire aggiornamenti o cancellazioni
                resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, "Funzionalità non implementata");
                break;

            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleAggiuntaPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            em.getTransaction().begin();

            EIndirizzo indirizzo = new EIndirizzo();
            indirizzo.setProvincia(req.getParameter("provincia"));
            indirizzo.setCitta(req.getParameter("comune"));
            indirizzo.setCap(req.getParameter("cap"));
            indirizzo.setNumeroCivico(req.getParameter("civico"));
            indirizzo.setVia(req.getParameter("indirizzo"));

            EUfficio ufficio = new EUfficio();
            ufficio.setTitolo(req.getParameter("nome-ufficio"));
            ufficio.setPrezzo(Integer.parseInt(req.getParameter("prezzo")));
            ufficio.setDescrizione(req.getParameter("descrizione"));
            ufficio.setNumeroPostazioni(Integer.parseInt(req.getParameter("postazioni")));
            ufficio.setSuperficie(Float.parseFloat(req.getParameter("superficie")));
            ufficio.setDataCaricamento(java.time.LocalDateTime.now());
            ufficio.setStato(StatoUfficioEnum.InAttesa);
            ufficio.setIndirizzo(indirizzo);

            EIntervalliDisponibilita intervallo = new EIntervalliDisponibilita();
            intervallo.setDataInizio(LocalDate.parse(req.getParameter("data_inizio")).atStartOfDay());
            intervallo.setDataFine(LocalDate.parse(req.getParameter("data_fine")).atStartOfDay());
            intervallo.setFascia(FasciaOrariaEnum.valueOf(req.getParameter("fascia")));
            intervallo.setUfficio(ufficio);

            em.persist(indirizzo);
            em.persist(ufficio);
            em.persist(intervallo);

            Collection<Part> fileParts = req.getParts();
            for (Part part : fileParts) {
                if ("foto".equals(part.getName()) && part.getSize() > 0) {
                    try (InputStream is = part.getInputStream()) {
                        byte[] content = is.readAllBytes();

                        EFoto foto = new EFoto();
                        foto.setContent(content);
                        foto.setMimeType(part.getContentType());
                        foto.setSize((int) part.getSize());
                        foto.setUfficio(ufficio);
                        em.persist(foto);
                    }
                }
            }

            List<String> servizi = Arrays.asList(Optional.ofNullable(req.getParameterValues("servizi")).orElse(new String[]{}));
            String altroServizio = req.getParameter("altro-servizio");
            if (altroServizio != null && !altroServizio.trim().isEmpty()) {
                servizi = new ArrayList<>(servizi);
                servizi.add(altroServizio.trim());
            }
            for (String nomeServizio : servizi) {
                EServiziAggiuntivi servizio = new EServiziAggiuntivi();
                servizio.setNomeServizio(nomeServizio);
                servizio.setUfficio(ufficio);
                em.persist(servizio);
            }

            em.getTransaction().commit();

            resp.sendRedirect(req.getContextPath() + "/uffici");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ServletException("Errore durante il salvataggio dell’ufficio", e);
        }
    }

    private Long getIdLocatoreFromSession(HttpServletRequest req) {
        // Modifica questo metodo in base a come memorizzi l’utente in sessione
        HttpSession session = req.getSession(false);
        if (session != null) {
            Object userId = session.getAttribute("userId");
            if (userId instanceof Long) {
                return (Long) userId;
            }
            if (userId instanceof String) {
                try {
                    return Long.parseLong((String) userId);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }

    private EntityManager getEntityManager() {
        return (EntityManager) getServletContext().getAttribute("em");
    }
}
