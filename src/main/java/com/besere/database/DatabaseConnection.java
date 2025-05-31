
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.database;

/**
 *
 * @author admin
*/

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;


public class DatabaseConnection
{
    private static Connection conn = null;
    
    public static Connection getconnection() throws IOException, ClassNotFoundException, SQLException
    {
        
        Class.forName("com.mysql.cj.jdbc.Driver");

        Properties props = new Properties();
        InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties");
        
        if (input == null) {
            throw new IOException("Unable to find db.properties");
        }
        
        props.load(input);
        
        String DB_URL = props.getProperty("db.url");
        String DB_USER = props.getProperty("db.username");
        String DB_PASS = props.getProperty("db.password");
        
        //System.out.println("Conn -> " + conn);
        return conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        
    }

}
