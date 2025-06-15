package com.mycompany.coworking1.Controller;

import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.DAO.UfficioDao;
import com.mycompany.coworking1.DAO.impl.PrenotazioneDaoImpl;
import com.mycompany.coworking1.DAO.impl.UfficioDaoImpl;
import com.mycompany.coworking1.Model.entity.*;
import com.mycompany.coworking1.Model.enums.FasciaOrariaEnum;
import com.mycompany.coworking1.Model.enums.StatoUfficioEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.time.LocalDate;
import java.util.*;

@WebServlet({"/aggiunta", "/gestione", "/prenotazioni"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // max 5MB per file
public class OfficeController extends BaseController {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoWirking1PU");
    private Configuration cfg;

    @Override
    public void init() throws ServletException {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
    }

    // Metodo per recuperare l'ID locatore dalla sessione (già esistente)
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

    // Metodo per recuperare il locatore "attachato" all'EntityManager
    private ELocatore getAttachedLocatore(EntityManager em, HttpServletRequest req) throws ServletException {
        String idLocatore = getIdLocatoreFromSession(req);
        if (idLocatore == null) {
            throw new ServletException("Utente non autenticato");
        }
        ELocatore locatore = em.find(ELocatore.class, idLocatore);
        if (locatore == null) {
            throw new ServletException("Locatore non trovato nel database");
        }
        return locatore;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        UfficioDao ufficioDao = new UfficioDaoImpl(em);
        String path = req.getServletPath();
        resp.setContentType("text/html;charset=UTF-8");
        Map<String, Object> data = new HashMap<>();
        data.put("ctx", req.getContextPath());

        try (Writer out = resp.getWriter()) {
            Template template;

            switch (path) {
                case "/aggiunta":
                    String idLocatore = getIdLocatoreFromSession(req);
                    if (idLocatore == null) {
                        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Utente non autenticato");
                        return;
                    }
                    // Mostra il form per aggiunta ufficio
                    template = cfg.getTemplate("locatore/aggiuntaUfficio/aggiuntaUfficio.ftl");
                    break;

                case "/gestione":
                    String idLocatore2 = getIdLocatoreFromSession(req);
                    if (idLocatore2 == null) {
                        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Utente non autenticato");
                        return;
                    }
                    try {
                        List<EUfficio> uffici = ufficioDao.getUfficiByLocatoreId(idLocatore2);
                        data.put("uffici", uffici);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    template = cfg.getTemplate("locatore/gestioneUffici/gestione_uffici.ftl");
                    break;
                    
                case "/prenotazioni":
                    String idLocatore3 = getIdLocatoreFromSession(req);
                    if (idLocatore3 == null) {
                        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Utente non autenticato");
                        return;
                    }
                    try {
                        PrenotazioneDao prenotazioneDao = new PrenotazioneDaoImpl(em);
                        List<EPrenotazione> prenotazioni = prenotazioneDao.getPrenotazioniByLocatore(idLocatore3);

                        List<EPrenotazione> activeReservations = new ArrayList<>();
                        List<EPrenotazione> pastReservations = new ArrayList<>();

                        LocalDate today = LocalDate.now();

                        for (EPrenotazione p : prenotazioni) {
                            if (!p.getData().toLocalDate().isBefore(today)) {
                                activeReservations.add(p);
                            } else {
                                pastReservations.add(p);
                            }
                        }
                        
                        System.out.println("Active reservations count: " + activeReservations.size());
                        System.out.println("Past reservations count: " + pastReservations.size());

                        data.put("activeReservations", activeReservations);
                        data.put("pastReservations", pastReservations);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    template = cfg.getTemplate("locatore/gestionePrenotazioni/gestione_prenotazioni_locatore.ftl");
                    break;

                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
            }
            template.process(data, out);
        } catch (Exception e) {
            throw new ServletException("Errore nel caricamento della pagina", e);
        } finally {
            em.close();
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
                handleNascondiUfficioPost(req, resp);
                break;

            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleAggiuntaPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Recupera il locatore "gestito" dall'EntityManager
            ELocatore locatore = getAttachedLocatore(em, req);

            // Crea e popola indirizzo
            EIndirizzo indirizzo = new EIndirizzo();
            indirizzo.setProvincia(req.getParameter("provincia"));
            indirizzo.setCitta(req.getParameter("comune"));
            indirizzo.setCap(req.getParameter("cap"));
            indirizzo.setNumeroCivico(req.getParameter("civico"));
            indirizzo.setVia(req.getParameter("indirizzo"));

            // Crea e popola ufficio
            EUfficio ufficio = new EUfficio();
            ufficio.setTitolo(req.getParameter("nome-ufficio"));
            ufficio.setPrezzo(Integer.parseInt(req.getParameter("prezzo")));
            ufficio.setDescrizione(req.getParameter("descrizione"));
            ufficio.setNumeroPostazioni(Integer.parseInt(req.getParameter("postazioni")));
            ufficio.setSuperficie(Float.parseFloat(req.getParameter("superficie")));
            ufficio.setDataCaricamento(java.time.LocalDateTime.now());
            ufficio.setStato(StatoUfficioEnum.InAttesa);
            ufficio.setIndirizzo(indirizzo);
            ufficio.setLocatore(locatore);  // usa locatore attachato

            // Crea e popola intervallo disponibilità
            EIntervalliDisponibilita intervallo = new EIntervalliDisponibilita();
            intervallo.setDataInizio(LocalDate.parse(req.getParameter("data_inizio")).atStartOfDay());
            intervallo.setDataFine(LocalDate.parse(req.getParameter("data_fine")).atStartOfDay());
            String fasciaParam = req.getParameter("fascia");
            FasciaOrariaEnum fascia = FasciaOrariaEnum.fromLabel(fasciaParam);
            intervallo.setFascia(fascia);
            intervallo.setUfficio(ufficio);

            // Persist delle entità nuove (indirizzo, ufficio, intervallo)
            em.persist(indirizzo);
            em.persist(ufficio);
            em.persist(intervallo);

            // Gestione foto
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

            // Gestione servizi aggiuntivi
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
            
            em.refresh(ufficio);
            System.out.println("isHidden dopo aggiornamento: " + ufficio.isHidden());


            
            

            resp.sendRedirect(req.getContextPath() + "/gestione");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ServletException("Errore durante il salvataggio dell’ufficio", e);
        } finally {
            em.close();
        }
    }
    
    // Metodo per nascondere un ufficio
private void handleNascondiUfficioPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    EntityManager em = emf.createEntityManager();

    try {
        em.getTransaction().begin();

        String idUfficio = req.getParameter("idUfficio");
        if (idUfficio == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID ufficio mancante");
            return;
        }

        
        EUfficio ufficio = em.find(EUfficio.class, idUfficio);

        if (ufficio == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Ufficio non trovato");
            return;
        }

        // Log per debug
        System.out.println("Nascondo ufficio id: " + idUfficio);

        // Modifica isHidden
        ufficio.setHidden(true);

        em.getTransaction().commit();

        resp.sendRedirect(req.getContextPath() + "/gestione");

    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        throw new ServletException("Errore durante l'aggiornamento isHidden", e);
    } finally {
        em.close();
    }
}
}
