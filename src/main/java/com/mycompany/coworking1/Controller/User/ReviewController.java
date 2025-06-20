

package com.mycompany.coworking1.Controller.User;

import com.mycompany.coworking1.Controller.BaseController;
import com.mycompany.coworking1.DAO.RecensioniDao;
import com.mycompany.coworking1.DAO.impl.RecensioniDaoImpl;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.Model.entity.ERecensione;
import com.mycompany.coworking1.Model.entity.EUfficio;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 39327
 */
@WebServlet("/home-utente/search/showoffice/Detailsoffice/review")
public class ReviewController extends BaseController {
       private Configuration cfg;
//start freemarker
    @Override
    public void init() throws ServletException {
        

        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }
    
    //this getMethod, take the review for office using the idoffice that was in the request
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManager em = (EntityManager) request.getAttribute("em");
       String idoffice = request.getParameter("idufficio"); 
       EUfficio office = em.find(EUfficio.class,idoffice);
       RecensioniDao reviewDao = new RecensioniDaoImpl(em);
       
       List<ERecensione> review = new ArrayList<>();
       try{
           //take the review for office
        review= reviewDao.getReviewbyDb(idoffice);
       
        HttpSession session = request.getSession(false);

        String isLoggedIn = "notLoggedIn";
        String nome = "";
        String cognome = "";
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
            }}
            //pass the data to the template
            data.put("isloggedin",isLoggedIn);
            data.put("office", office);
            data.put("reviews", review);
            data.put("ctx", request.getContextPath());
            
            
            Template template = cfg.getTemplate("User/Review/ShowReview.ftl");
              response.setContentType("text/html;charset=UTF-8");

        try (Writer out = response.getWriter()) {
                template.process(data, out);
            } catch (Exception e) {
                throw new ServletException("Errore nel template", e);
            }
         
            }catch (Exception e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: " + e.getMessage());
    } 

    }
    
    
}

