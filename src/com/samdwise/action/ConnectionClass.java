/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samdwise.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author samdwise
 */
public class ConnectionClass {
    
    public Connection getConnecion() {
        Connection con = null;
        
        try{
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product?useUnicode=true&"
                + "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "kenny1367");
            //JOptionPane.showMessageDialog(null, "Connection establised");
        return con;
      }catch(SQLException ex){
          Logger.getLogger(ConnectionClass.class.getName()).log(Level.SEVERE,null, ex);
     
        return null;
      }
        
        
    }
}
