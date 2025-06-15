/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 39327
 */@WebServlet("/logout")
public class LogoutController extends BaseController{
    

//this do the logout and make invalidate the function
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Prende la sessione se esiste, ma non ne crea una nuova
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate(); // Invalida la sessione attiva
        }

        // Reindirizza alla home )
        resp.sendRedirect(req.getContextPath() + "/home-utente");
    }
}
    

