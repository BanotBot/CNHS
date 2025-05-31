/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.contentloader;

import com.besere.controller.Grade7Controller;
import com.besere.controller.MainController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author admin
*/

//CONTENT FILE CLICKER
public class grade7loader {
    
    private MainController maincontroller;

    public void load7Content(Parent parentload){
        System.out.println("Loader in grade 7 ");
        System.out.println("Parent -> " + parentload);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/grade7.fxml"));
            try {
                System.out.println("Load grade 7");
                parentload = loader.load();
                Grade7Controller refreshData = loader.getController();
                refreshData.refreshData();
                refreshData.setMainController(maincontroller);
            } catch (IOException ex) {
                Logger.getLogger(grade7loader.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        maincontroller.getMainContent().getChildren().setAll(parentload);
    }
    
    public void setgrade7loader(MainController mainCont) {
        this.maincontroller = mainCont;
    }
    
    public void setMainContent(MainController maincont){
        this.maincontroller = maincont;
    }
    
}
