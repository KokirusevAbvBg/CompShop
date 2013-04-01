/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compshop.mysql;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NikIvRu
 */
@ManagedBean()
@SessionScoped
public class DBConnection {

    static public Connection connect()throws Exception{
       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/compshop","root","");   
       return con;
    }
}
