/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Tbluser;
import Untility.project2Utility;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class tblUserDAO {
    public EntityManager em = project2Utility.createConnect();
    
    public void createUser(Tbluser user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
    
    public List<Tbluser> findAll(){
        List<Tbluser> list = em.createNamedQuery("Tbluser.findAll", Tbluser.class).getResultList();
        return list;
    }
    
    public void updateUser(Tbluser user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }
    
    public void deleteUser(String username){
        em.getTransaction().begin();
        Tbluser user = em.find(Tbluser.class, username);
        em.remove(user);
        em.getTransaction().commit();
    }
    
    
}
