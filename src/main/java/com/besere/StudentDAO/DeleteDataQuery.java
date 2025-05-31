
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.StudentDAO;

import com.besere.database.DatabaseConnection;
import com.besere.uicontroller.DialogUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 *
 * @author admin
*/

public class DeleteDataQuery{

    public static void deleteRecord(String tablename,int value){
        System.out.println("id -> " + value);
        String sql = "DELETE FROM " + tablename + " WHERE id=?";
        System.out.println("sql -> " + sql);
        try (
                Connection conn = DatabaseConnection.getconnection();
                PreparedStatement prepared = conn.prepareStatement(sql);
            ){
            
            prepared.setInt(1,value);
            int result = prepared.executeUpdate();
            
            if (result > 0) {
                System.out.println("Deleted Data successfully");
            }else{
                DialogUtil.showErrorDelete(
                    "DELETE UNSUCCESSFUL",
                    "THE DATA WAS UNSUCCESSFUL TO DELETE",
                    "/images/error.png");
            }
            
        } catch (Exception e) {
            System.out.println("Error deleting record => " + e.getMessage());
        }
        
    }
}
