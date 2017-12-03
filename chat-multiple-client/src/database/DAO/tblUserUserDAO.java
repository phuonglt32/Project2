/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import database.Entities.TbluserUser;
import database.Utility.project2Utility;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class tblUserUserDAO {
    public EntityManager em = project2Utility.createConnect();
    
    public void saveMassage1v1(String username1,String username2,String content,int status){
        //lay thoi diem hien tai luu vao
        java.util.Date time = new java.util.Date();
        TbluserUser message = new TbluserUser(username1, username2, time, content,(short)status);
        
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
    }
    
    
    public List<TbluserUser> checkUnreadMessage1v1(String username){
        List<TbluserUser> list = em.createQuery("SELECT t FROM TbluserUser t WHERE t.tbluserUserPK.userName2 = '"+
                username+"' AND t.status = 0").getResultList();
        return list;
    }
    
    
    public List<TbluserUser> getAllMessage1v1(String username1, String username2){
        // lay tat ca cac tin nhan trong 1 cuoc hoi thoai 1 v 1
        List<TbluserUser> list = em.createQuery("SELECT t FROM TbluserUser t WHERE (t.tbluserUserPK.userName1 = '"+
                username1+"' AND t.tbluserUserPK.userName2 = '"+username2+"') OR (t.tbluserUserPK.userName1 = '"+
                username2+"' AND t.tbluserUserPK.userName2 = '"+username1+"') ORDER BY t.tbluserUserPK.dateTime").getResultList();
        return list;
    }
    
    
}
