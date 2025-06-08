
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.besere.StudentDAO;

import com.besere.controller.MainController;
import com.besere.controller.SceneController;
import com.besere.database.DatabaseConnection;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author admin
 */

public class LoginDataQuery {
    
    private static SceneController scenecontroller;
    
    public static void processLogin(String username,String password) throws IOException,ClassNotFoundException,SQLException{
        
        String loginQuery = "SELECT password FROM user WHERE BINARY username=?";
        
        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement prepared = connection.prepareStatement(loginQuery);
            ){
            
            prepared.setString(1,username);
            
            ResultSet result = prepared.executeQuery();
            
            if(result.next()) {
                String hashpassword = result.getString("password");
                System.out.println("Database Password -> " + hashpassword);

                if(passwordVerification(password,hashpassword)) {
                    System.out.println("Correctness");
                    scenecontroller.showMainContent(username);
                }else{
                    Platform.runLater(()-> {
                        System.out.println("Wrongggness");
                        scenecontroller.getLoginMessage().setText("Invalid password");
                        scenecontroller.getLoginMessage().setFont(Font.font("Montserrat",FontWeight.BOLD,16));
                        scenecontroller.getLoginMessage().setAlignment(Pos.BASELINE_CENTER);
                    });
                }
            }else{
                Platform.runLater(()->{
                    System.out.println("Wrongggness Username");
                    scenecontroller.getLoginMessage().setText("Invalid username");
                    scenecontroller.getLoginMessage().setFont(Font.font("Montserrat",FontWeight.BOLD,16));
                    scenecontroller.getLoginMessage().setAlignment(Pos.BASELINE_CENTER);
                });
            }
            System.out.println("Done logging in....");
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage());
        }
    }
    
    private static boolean passwordVerification(String inputPassword,String hashpassword){
        //String hashpassInput = hashPassword(inputPassword);
        return inputPassword.equals(hashpassword);
    }
    
    private static String hashPassword(String password){
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] encodeHash = digest.digest(password.getBytes());
            StringBuilder hextString = new StringBuilder();
            
            for (byte b : encodeHash) {
                hextString.append(String.format("%02x",b));
            }
            return hextString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    
    public static void setSceneController(SceneController scenecontroller){
        LoginDataQuery.scenecontroller = scenecontroller;
    }
}
