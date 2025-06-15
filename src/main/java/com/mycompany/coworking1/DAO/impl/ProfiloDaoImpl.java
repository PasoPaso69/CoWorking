
package com.mycompany.coworking1.DAO.impl;
import  com.mycompany.coworking1.DAO.ProfiloDao;
import com.mycompany.coworking1.Model.entity.EProfilo;
import com.mycompany.coworking1.util.EntityManagerUtil;
import jakarta.persistence.*;


/**
 *
 * @author 39327
 */
public class ProfiloDaoImpl implements ProfiloDao {
    
     private final EntityManager em;

    public ProfiloDaoImpl(EntityManager em) {
        this.em = em;
    }
    //find by userna,e
    @Override
    public EProfilo findbyUsername(String email) {
        try {
            EntityManager entitymanager = EntityManagerUtil.getEntityManager();
            return entitymanager.createQuery("FROM EProfilo WHERE email = :username", EProfilo.class)
                .setParameter("username", email)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } 
    }
    
    //this save the profile when there is a registration
    @Override
    public void save(EProfilo user) {
        EntityManager entitymanager = EntityManagerUtil.getEntityManager();
         EntityTransaction transaction = entitymanager.getTransaction();
    try {
        transaction.begin();
        entitymanager.merge(user);
        transaction.commit();
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        throw e;
    }
    }
    
}
