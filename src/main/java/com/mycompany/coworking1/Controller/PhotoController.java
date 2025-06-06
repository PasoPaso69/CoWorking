/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coworking1.Controller;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import com.mycompany.coworking1.Model.entity.EFoto;
import com.mycompany.coworking1.util.EntityManagerUtil;

import java.io.IOException;

/**
 *
 * @author 39327
 */
@WebServlet("/photo")
public class PhotoController extends BaseController {
    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID mancante");
            return;
        }

        EntityManager em = EntityManagerUtil.getEntityManager();
        EFoto foto = em.find(EFoto.class, id);

        if (foto == null || foto.getContent() == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Foto non trovata");
            return;
        }

        response.setContentType(foto.getMimeType()); // es. "image/jpeg"
        response.setContentLength(foto.getSize());

        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(foto.getContent());
            out.flush();
        } finally {
            em.close();
        }
    }
}

