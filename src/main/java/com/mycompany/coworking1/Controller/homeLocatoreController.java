/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.coworking1.Controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/homeLocatore")
public class homeLocatoreController extends BaseController {

    private Configuration cfg;

    @Override
    public void init() throws ServletException {
        // Inizializza la configurazione di FreeMarker
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Dati da passare alla pagina (se serve)
        System.out.println("Richiesta ricevuta in /homeLocatore");

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("titolo", "Benvenuto Locatore");
        
         String ctx = req.getContextPath();
        System.out.println("Context path: " + ctx);  // Debug
        dataModel.put("ctx", ctx);

        // Imposta il tipo di contenuto
        resp.setContentType("text/html;charset=UTF-8");
        
        try {
            
            // Carica e processa il template
            Template template = cfg.getTemplate("locatore/home/homeLocatore.ftl");
            template.process(dataModel, resp.getWriter());

        } catch (Exception e) {
            throw new ServletException("Errore rendering FreeMarker", e);
        }
    }
}
