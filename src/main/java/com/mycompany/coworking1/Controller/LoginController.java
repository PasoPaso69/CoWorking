/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import com.mycompany.coworking1.Service.ProfiloService;
import com.mycompany.coworking1.Service.impl.ProfiloServiceImpl;
import com.mycompany.coworking1.DAO.impl.ProfiloDaoImpl;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.ELocatore;
import com.mycompany.coworking1.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
/**
 *
 * @author 39327
 */
@WebServlet("/login")
    
public class LoginController extends BaseController {
    private ProfiloService userService;
    private Configuration cfg;

    @Override
    public void init() throws ServletException {
     
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     EntityManager em = (EntityManager) req.getAttribute("em");
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
        EntityManager em = (EntityManager) req.getAttribute("em");
         this.userService = new ProfiloServiceImpl(new ProfiloDaoImpl(em));
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        EProfilo user= userService.login(email, password); // crea metodo login nel service

        if (user!= null) {
           
    HttpSession session = req.getSession();
    session.setAttribute("user", user);
    //session.setAttribute("userName", user.getName());

    if (user.isAdmin()) {
    session.setAttribute("userType", "admin");
    resp.sendRedirect(req.getContextPath() + "/home-admin");
} else if (user instanceof ELocatore) {
    session.setAttribute("userType", "locatore");
    resp.sendRedirect(req.getContextPath() + "/homeLocatore");
} else {
    session.setAttribute("userType", "utente");
    resp.sendRedirect(req.getContextPath() + "/home-utente");
} // es. home protetta
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("ctx", req.getContextPath());
            data.put("error", "Email o password errati");
            Template template = cfg.getTemplate("login/login.ftl");
            resp.setContentType("text/html;charset=UTF-8");
            try (Writer out = resp.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
        }
    }
    }
    
