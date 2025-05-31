
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.besere.StudentDAO;

import com.besere.StudentService.StudentService;
import com.besere.database.DatabaseConnection;
import com.besere.uicontroller.DialogUtil;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;

/**
 *
 * @author admin
 */

public class InsertDataQuery{
    
    private final StudentService studService;

    public InsertDataQuery(StudentService studentService) {
        this.studService = studentService;
    }
    
    public void insertData(){

        String tablename = "grade" +studService.getYearLevel();
        String sql = "INSERT INTO `" + tablename + "`(name,middlename,lastname,age,birthdate,year_level) VALUES(?,?,?,?,?,?)";

        try (
            Connection conn = DatabaseConnection.getconnection();
            PreparedStatement prepared = conn.prepareStatement(sql)){
            
            CheckTableQuery.checkTable(conn, tablename);
            System.out.println("CheckTable -> " + CheckTableQuery.checkTable(conn, tablename));               
                System.out.println("sql => " + sql);
            
                prepared.setString(1,studService.getName());
                prepared.setString(2,studService.getmname());
                prepared.setString(3,studService.getlname());
                prepared.setInt(4,studService.getAge());
                prepared.setDate(5,studService.getBirthdate());
                prepared.setInt(6,studService.getYearLevel());

                int rowInserted = prepared.executeUpdate();

                if (rowInserted > 0) {
                    Platform.runLater(() ->{
                        DialogUtil.showSuccess(
                            "SUCCESS",
                            "SUCCESSFULLY INSERTED DATA",
                            "/images/checkmark.png");
                    });

                }else{
                    Platform.runLater(()-> {
                        DialogUtil.showError(
                            "SUCCESS",
                            "SUCCESSFULLY INSERTED DATA",
                            "/images/checkmark.png");
                    });
                }

        } catch (SQLException e) {
              System.out.println("Database Error!" + e.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InsertDataQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
