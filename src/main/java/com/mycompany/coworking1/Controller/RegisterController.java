package com.mycompany.coworking1.Controller;

import com.mycompany.coworking1.DAO.PrenotazioneDao;
import com.mycompany.coworking1.DAO.impl.PrenotazioneDaoImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import com.mycompany.coworking1.Service.ProfiloService;
import com.mycompany.coworking1.Service.impl.ProfiloServiceImpl;
import com.mycompany.coworking1.DAO.impl.ProfiloDaoImpl;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.ELocatore;
import com.mycompany.coworking1.Model.entity.EPrenotazione;
import com.mycompany.coworking1.Model.entity.EUfficio;
import com.mycompany.coworking1.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterController extends BaseController {
    private ProfiloService userService;
    private Configuration cfg;
      

    @Override
    public void init() throws ServletException {
       //start freemarker

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    //when a user want to register  on the site use this function
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = (EntityManager) req.getAttribute("em");
        this.userService = new ProfiloServiceImpl(new ProfiloDaoImpl(em));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String nome = req.getParameter("name");
        String cognome = req.getParameter("surname");
        String  datanascita = req.getParameter("date");
        String PIva = req.getParameter("piva");
         
        LocalDate data1 = LocalDate.parse(datanascita);
        String phone = req.getParameter("phone");
        
        //create a profile if is a user and a locatore if had partita iva
        EProfilo profilo;
            if(PIva == null || PIva.trim().isEmpty()){     
        EProfilo newUser = new EProfilo();
       //  String id = UUID.randomUUID().toString();
        //newUser.(id); 
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setName(nome);
        newUser.setSurname(cognome);
        newUser.setDob(data1);
        newUser.setPhone(phone);
        profilo = newUser;
        }else{
            ELocatore newUser = new ELocatore ();
            newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setName(nome);
        newUser.setSurname(cognome);
        newUser.setDob(data1);
        newUser.setPhone(phone);
        newUser.setPartitaIva(PIva);
        profilo = newUser;
           
            }
        boolean registered = userService.isRegister(profilo);
    

        
        if (registered) {
            Map<String, Object> data = new HashMap<>();
             data.put("ctx", req.getContextPath()); 
            Template template = cfg.getTemplate("login/login.ftl");
            resp.setContentType("text/html;charset=UTF-8");
            try (Writer out = resp.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
           // dopo registrazione vai al login
        } else {
         Map<String, Object> data = new HashMap<>();
             data.put("ctx", req.getContextPath()); 
            data.put("error", "Utente già registrato");
            Template template = cfg.getTemplate("login/login.ftl");
            resp.setContentType("text/html;charset=UTF-8");
            try (Writer out = resp.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }finally {
    em.close();
}
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
     EntityManager em = (EntityManager) req.getAttribute("em");
        HttpSession session = req.getSession(false);
         if (session != null) {
            Object userObj = session.getAttribute("user");
             String nome = "";
        String cognome = "";
            
                EProfilo user = (EProfilo) userObj;
                 nome = user.getName();
                cognome = user.getSurname();
                
                data.put("nome", nome);
              data.put("cognome", cognome);
           
          data.put("user",user);
            
              data.put("ctx", req.getContextPath());
Template template = cfg.getTemplate("login/profile.ftl");
        // Imposta la risposta
        resp.setContentType("text/html;charset=UTF-8");
            try (Writer out = resp.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }

        }else{resp.sendRedirect(req.getContextPath() + "/login");}
    }
}
