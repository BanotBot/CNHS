 
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.besere.controller;

import com.besere.StudentDAO.LoginDataQuery;
import com.besere.validator.ValidatorData;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent; 

import javafx.stage.Stage;

/**
 *
 * @author admin
 */

public class SceneController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
   
    @FXML private Label switchtoSignin;
    @FXML private Label switchtoLogin;
    @FXML private TextField username;
    @FXML private TextField password;
    
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        
        if (switchtoSignin != null) {
            switchtoSignin.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
                try {
                    System.out.println("I am clicking sign-in..........");
                    switchDisplayWindow("1",(Node) e.getSource());
                } catch (IOException ex) {
                    Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE,null,ex);
                }
            });
        }
        
        if (switchtoLogin != null) {
            switchtoLogin.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            System.out.println("I am clicking login............");
                try {
                    switchDisplayWindow("2",(Node) e.getSource());
                } catch (IOException ex) {
                    Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
    
    private void switchDisplayWindow(String userclicked,Node source) throws IOException{
        switch(userclicked) {
            case "1" -> {
                root = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
            }
            case "2" -> {
                root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            }
                default -> throw new AssertionError();
            }
        stage = (Stage) source.getScene().getWindow();
        stage.setScene(new Scene(root));    
        stage.show();
    }
    
    @FXML public void LoginAction() throws IOException, ClassNotFoundException, SQLException{
        System.out.println("Submitttedd login credentials..............");
        String usernameData = username.getText().strip();
        String passwordData = password.getText().strip();
        System.out.println("username ->" + usernameData);
        System.out.println("password -> " + passwordData);
       
        if (!ValidatorData.anynull(usernameData,passwordData)) {
            System.out.println("Yess..........");
            LoginDataQuery.processLogin(usernameData,passwordData);
        }
    }
    
    public void RegisterAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
        String usernameData = username.getText().strip();
        String passwordData = password.getText().strip();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
