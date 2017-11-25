/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg2.crud;

import DAO.ListfriendDAO;
import DAO.LoginLogoutDAO;
import DAO.tblUserDAO;
import DAO.tblUserUserDAO;
import Entities.Tbluser;
import Entities.TbluserUser;
import Entities.TbluserUser_;
import java.sql.Date;
import java.util.List;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Administrator
 */
public class Project2CRUD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //List Friend
//        ListfriendDAO friendDAO = new ListfriendDAO();
//        
//        List<Tbluser> list = friendDAO.getListFriend("cuong");
//        
//        for (Tbluser tbluser : list) {
//            System.out.println(tbluser);
//        }

//          save message
//        tblUserUserDAO message = new tblUserUserDAO();
//        List<TbluserUser> list = message.checkmessage("phuong");
//        for (TbluserUser tbluserUser : list) {
//            System.out.println(tbluserUser);
//        }

    //log out
    LoginLogoutDAO login = new LoginLogoutDAO();
    login.logout("cuong");

//    Tbluser user = new Tbluser("cuong", "Hung Cuong", "1234", "", "off");
//    tblUserDAO userdao = new tblUserDAO();
//    userdao.updateUser(user);


    }
    
}
