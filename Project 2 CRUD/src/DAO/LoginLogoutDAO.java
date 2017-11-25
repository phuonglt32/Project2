/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Tbluser;
import Untility.project2Utility;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class LoginLogoutDAO {
    public EntityManager em = project2Utility.createConnect();
    
    public Tbluser login(String username, String password){
        Tbluser userlogin = em.createQuery("SELECT t FROM Tbluser t WHERE t.userName = '"+
                username+"' AND t.passWord = '"+password+"'", Tbluser.class).getSingleResult();
        return userlogin;
    }
    
    public void logout(String username){
        Tbluser user = em.find(Tbluser.class, username);
        user.setStatus("off");
        tblUserDAO userdao = new tblUserDAO();
        userdao.updateUser(user);
    }
}
