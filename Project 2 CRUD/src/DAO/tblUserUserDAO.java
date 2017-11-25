/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Tbluser;
import Entities.TbluserUser;
import Untility.project2Utility;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class tblUserUserDAO {
    public EntityManager em = project2Utility.createConnect();
    
    public void create(String username1,String username2,String content){
        String status;
        Tbluser user_reccive = em.find(Tbluser.class, username2);
        if("onl".equals(user_reccive.getStatus())){
            status = "seen";
        }else{
            status = "notseen";
        }
        java.util.Date time = new java.util.Date();
        
        TbluserUser message = new TbluserUser(username1, username2, time, content, status);
        
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
    }
    
    public List<TbluserUser> checkmessage(String username){
        List<TbluserUser> list = em.createQuery("SELECT t FROM TbluserUser t WHERE t.tbluserUserPK.userName2 = '"+
                username+"' AND t.status = 'notseen'").getResultList();
        return list;
    }
    
    public List<TbluserUser> getAllMessage(String username1, String username2){
        List<TbluserUser> list = em.createQuery("SELECT t FROM TbluserUser t WHERE (t.tbluserUserPK.userName1 = '"+
                username1+"' AND t.tbluserUserPK.userName2 = '"+username2+"') OR (t.tbluserUserPK.userName1 = '"+
                username2+"' AND t.tbluserUserPK.userName2 = '"+username1+"') ORDER BY t.tbluserUserPK.dateTime").getResultList();
        return list;
    }
    
    
}
