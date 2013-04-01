/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compshop.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.compshop.mysql.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NikIvRu
 */
public class RegistrationBean {

    /**
     * Creates a new instance of RegistrationBean
     */
    private String username;
    private String password;
    private String confirmpassword;
    private String firstname;
    private String lastname;
    private String email;
    private String confirmemail;
    private String address;

    public RegistrationBean() {
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

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmemail() {
        return confirmemail;
    }

    public void setConfirmemail(String confirmemail) {
        this.confirmemail = confirmemail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String addUser() throws Exception {
        DBConnection dbcon = new DBConnection();
        Connection con = dbcon.connect();
        String check = "failure";
        String result=null;
        
        String query="SELECT COUNT(username) FROM users WHERE username='"+username+"';";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        
        if(rs.first()){
             result = rs.getString(1);
        }
        
        if(password.equals(confirmpassword) && email.equals(confirmemail)
                && result.equals("0")){
           query= "INSERT INTO users(username,password,firstname,lastname,email,address) VALUES('" + username + "','" + password + "',"
                   + "'" + firstname + "','" + lastname + "','" + email + "','" + address + "')";
           statement.executeUpdate(query);
           
           check = "success";        
        }

        if (check.equals("success")) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "User Registration Successful!!!", null));
        } else if (check.equals("failure")) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "User Registration Failed!!!", null));
        }
        return check;
    }
}
