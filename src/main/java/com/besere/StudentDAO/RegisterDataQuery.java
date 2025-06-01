
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.besere.StudentDAO;

import com.besere.controller.SceneController;
import com.besere.database.DatabaseConnection;
import com.besere.uicontroller.DialogUtil;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author admin
 */

public class RegisterDataQuery{
    
    private static SceneController scenecontroller;
    
    public static void insertData(String username,String password){
        
        String sql = "INSERT INTO user (username,password) VALUES(?,?)";

        try (
            Connection conn = DatabaseConnection.getconnection();
            PreparedStatement prepared = conn.prepareStatement(sql)){
            
            CheckTableQuery.checkTable(conn,"user");
            System.out.println("CheckTable -> " + CheckTableQuery.checkTable(conn,"user"));               
            System.out.println("sql => " + sql);
            
                prepared.setString(1,username);
                prepared.setString(2,password);

                int rowInserted = prepared.executeUpdate();

                if (rowInserted > 0) {
                    Platform.runLater(() ->{
                        scenecontroller.getRegisterMessage().setText("Successfully Inserted");
                        scenecontroller.getRegisterMessage().setFont(Font.font("Montserrat",FontWeight.BOLD,16));
                        scenecontroller.getRegisterMessage().setAlignment(Pos.BASELINE_CENTER);
                    });

                }else{
                    Platform.runLater(()-> {
                        scenecontroller.getRegisterMessage().setText("Failed to Insert Data");
                        scenecontroller.getRegisterMessage().setFont(Font.font("Montserrat",FontWeight.BOLD,16));
                        scenecontroller.getLoginMessage().setAlignment(Pos.BASELINE_CENTER);
                    });
                }

        } catch (SQLException e) {
              System.out.println("Database Error!" + e.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RegisterDataQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setSceneController(SceneController scenecontroller){
        RegisterDataQuery.scenecontroller = scenecontroller;
    }
    
}
