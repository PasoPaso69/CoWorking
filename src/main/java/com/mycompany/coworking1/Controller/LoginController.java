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
/**
 *
 * @author 39327
 */
@WebServlet("/login")
    
public class LoginController extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        EProfilo user= userService.login(email, password); // crea metodo login nel service

        if (user!= null) {
            // salva in sessione, reindirizza alla pagina protetta
            req.getSession().setAttribute("userEmail", email);
            resp.sendRedirect(req.getContextPath() + "/home"); // es. home protetta
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
