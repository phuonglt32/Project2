/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tran Cuong
 */
public class ArrayMethod {

    interface Action {
        void sout();
    }
    
    public static void main(String[] args) {        
        Map<String, Action> hashMap = new HashMap<>();
        hashMap.put("method1", new Action() {
            public void sout() {
                System.out.println("hello 1");
            }
        });
        
        hashMap.put("method2", new Action() {
            public void sout() {
                System.out.println("hello 2");
            }
        });
        
        hashMap.get("method1").sout();
    }
}
