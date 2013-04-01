/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compshop.controller;

import com.compshop.mysql.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author NikIvRu
 */
public class LoginBean {
    
    String username;
    String password;

    public LoginBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String CheckValidUser() throws SQLException, Exception {
        DBConnection dbcon = new DBConnection();
        Connection con = dbcon.connect();
        String check = "failure";
        String result;

        String query = "SELECT id FROM users WHERE username='" + username + "' AND password='" + password + "';";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        if (rs.first()) {
            result = rs.getString(1);
            if (result != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put("username", username);
                check = "success";
            } else {
                check = "fail";
            }
        }

        if (check.equals("success")) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful!!!", null));
        } else if (check.equals("failure")) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed!!!", null));
        }
        return check;
    }
}
