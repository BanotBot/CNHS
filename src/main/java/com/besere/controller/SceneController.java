 
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.besere.controller;

import com.besere.StudentDAO.LoginDataQuery;
import com.besere.StudentDAO.RegisterDataQuery;
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
    @FXML private Label loginMessage;
    @FXML private Label RegisterMessage;
    
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
                    Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE,null,ex);
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
    
    @FXML public void LoginAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException
    {
        System.out.println("Submitttedd login credentials..............");
        String usernameData = username.getText().strip();
        String passwordData = password.getText().strip();
        System.out.println("username ->" + usernameData);
        System.out.println("password -> " + passwordData);
       
        if (!ValidatorData.anynull(usernameData,passwordData)) {
            System.out.println("Yess..........");
            LoginDataQuery.setSceneController(this);
            LoginDataQuery.processLogin(usernameData,passwordData);
        }
    }
    
    @FXML public void RegisterAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException
    {
        String usernameData = username.getText().strip();
        String passwordData = password.getText().strip();
       
        if (!ValidatorData.anynull(usernameData,passwordData)) {
            RegisterDataQuery.setSceneController(this);
            RegisterDataQuery.insertData(usernameData,passwordData);
        }
    }
    
    public void showMainContent() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
        Parent mainroot = loader.load();
        
        scene = new Scene(mainroot);
        stage = (Stage) username.getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public Label getLoginMessage(){
        return loginMessage;
    }
    
    public Label getRegisterMessage(){
        return RegisterMessage;
    }
        
}
