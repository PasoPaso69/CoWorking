package com.mycompany.coworking1.Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import com.mycompany.coworking1.Service.ProfiloService;
import com.mycompany.coworking1.Service.impl.ProfiloServiceImpl;
import com.mycompany.coworking1.DAO.impl.ProfiloDaoImpl;
import com.mycompany.coworking1.Model.entity.EProfilo;


@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private ProfiloService userService;
    private Configuration cfg;

    @Override
    public void init() throws ServletException {
        this.userService = new ProfiloServiceImpl(new ProfiloDaoImpl());

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    
    Map<String, Object> data = new HashMap<>();
    data.put("ctx", req.getContextPath()); 
    
    Template template = cfg.getTemplate("login/login.ftl");
    try (Writer out = resp.getWriter()) {
        template.process(data, out);
    } catch (Exception e) {
        throw new ServletException("Errore nel template", e);
    }
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String nome = req.getParameter("name");
        String cognome = req.getParameter("surname");
        String  datanascita = req.getParameter("date");
         
        LocalDate data1 = LocalDate.parse(datanascita);
        String phone = req.getParameter("phone");
                
        EProfilo newUser = new EProfilo();
         String id = UUID.randomUUID().toString();
        newUser.setId(id); 
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setNome(nome);
        newUser.setCognome(cognome);
        newUser.setDataNascita(data1);
        newUser.setTelefono(phone);
       
        // imposta altri campi

        boolean registered = userService.isRegister(newUser);
        if (registered) {
           // dopo registrazione vai al login
        } else {
            Map<String, Object> data = new HashMap<>();
             data.put("ctx", req.getContextPath()); 
            data.put("error", "Utente già registrato");
            Template template = cfg.getTemplate("register.ftl");
            resp.setContentType("text/html;charset=UTF-8");
            try (Writer out = resp.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
        }
    }
}
