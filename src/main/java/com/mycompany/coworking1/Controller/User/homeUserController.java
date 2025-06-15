/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller.User;

/**
 *
 * @author 39327
 */
import com.mycompany.coworking1.Controller.BaseController;
import freemarker.template.Configuration;
import freemarker.template.Template;

import com.mycompany.coworking1.Model.entity.EProfilo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

@WebServlet("/home-User")
public class homeUserController extends BaseController{
    
    private Configuration cfg;
   //start fremarker
@Override
    public void init() throws ServletException {
        // Configura FreeMarker
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
    }
//this show the home
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        
        HttpSession session = request.getSession(false);

        String isLoggedIn = "notLoggedIn";
        String nome = null;
        String cognome = null;
        Map<String, Object> data = new HashMap<>();
         
        //check if the user is logged
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
       
        data.put("isloggedin", isLoggedIn );
        data.put("ctx", request.getContextPath());
        // call the template
        Template template = cfg.getTemplate("User/home/homeUser.ftl");

        response.setContentType("text/html;charset=UTF-8");

        try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
        }
    }


    
