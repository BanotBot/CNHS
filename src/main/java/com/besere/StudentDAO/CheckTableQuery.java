/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.besere.StudentDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author admin
 */

public class CheckTableQuery {
    
    protected static boolean checkTable(Connection conn,String tablename){
        String checkTableQuery = "SELECT COUNT(*) AS total FROM " + tablename;
        String resetQuery = "TRUNCATE TABLE " + tablename;
        
        try (
                PreparedStatement countTable = conn.prepareStatement(checkTableQuery);
                ResultSet rs = countTable.executeQuery();
            ){
            
            if (rs.next()) {
                int count_number = rs.getInt("total");
                System.out.println("Count number => " + count_number);
                
                if (count_number == 0) {
                    try(
                            Statement stmt = conn.createStatement();
                        ) {
                           stmt.executeUpdate(resetQuery);
                           return true;
                    } catch (Exception e) {
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error truncating data -> " + e.getMessage());
            return false;
        }
        
        return false;
    }
}

