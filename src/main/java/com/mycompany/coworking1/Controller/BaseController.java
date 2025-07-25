/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller;

import com.mycompany.coworking1.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 39327
 */
public abstract class BaseController extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            // Passa EntityManager alla richiesta come attributo
            req.setAttribute("em", em);
            super.service(req, resp);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}

