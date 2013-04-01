/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compshop.controller;

import com.compshop.mysql.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NikIvRu
 */
public class ViewProductBean {

    Statement ps;
    ResultSet rs;

    public ViewProductBean() {
    }

    public List getProducts() {
        
        List<Product> products = new ArrayList<Product>();
        
        try {

            //  DBConnection dbcon = new DBConnection();
            Connection con = DBConnection.connect();

            ps = con.createStatement();
            rs = ps.executeQuery("SELECT name,processor,graphicsCard,hardDisk FROM products");
            while (rs.next()) {
                //System.out.println(rs.getString(1));
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setProcessor(rs.getString("processor"));
                product.setGraphicsCard(rs.getString("graphicsCard"));
                product.setHardDisk(rs.getString("hardDisk"));
                products.add(product);
            }

        } catch (Exception e) {
            System.out.println("Error Data : " + e.getMessage());
        }
        return products;
    }

    public class Product {

        String name;
        String processor;
        String graphicsCard;
        String hardDisk;

        public void setName(String name) {
            this.name = name;
        }

        public void setProcessor(String processor) {
            this.processor = processor;
        }

        public void setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
        }

        public void setHardDisk(String hardDisk) {
            this.hardDisk = hardDisk;
        }

        /*
         * new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4))
         public Product(String name, String processor, String graphicsCard, String hardDisk) {

         this.name = name;
         this.processor = processor;
         this.graphicsCard = graphicsCard;
         this.hardDisk = hardDisk;
         }
         */
        public String getName() {
            return name;
        }

        public String getProcessor() {
            return processor;
        }

        public String getGraphicsCard() {
            return graphicsCard;
        }

        public String getHardDisk() {
            return hardDisk;
        }
    }
}
