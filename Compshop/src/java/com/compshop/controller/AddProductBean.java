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
public class AddProductBean {

    private String name;
    private String processor;
    private String graphicsCard;
    private int hardDisk;

    public AddProductBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public int getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(int hardDisk) {
        this.hardDisk = hardDisk;
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String addProduct() throws Exception {
        DBConnection dbcon = new DBConnection();
        Connection con = dbcon.connect();
        String check = "success";


        String query;
        query = "INSERT INTO products (name,processor,graphicsCard,hardDisk) VALUES('" + name + "','" + processor + "','"+ graphicsCard+ "','" + hardDisk + "')";
        PreparedStatement statement = con.prepareStatement(query);
        statement.executeUpdate(query);




        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Adding product Successful!!!", null));

        return check;
    }
}
