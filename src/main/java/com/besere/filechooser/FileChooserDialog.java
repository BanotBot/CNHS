
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.filechooser;

import com.besere.controller.MainController;
import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author admin
*/

public class FileChooserDialog{
    
    private MainController maincontroller;
    
    private final FileChooser filechooser = new FileChooser();
    
    public void showFileChooser(Stage stage){
        FileChooser.ExtensionFilter imagefileFilters = new FileChooser.ExtensionFilter(
                "Images Files","*.jpg","*.jpeg","*.png");
        filechooser.getExtensionFilters().add(imagefileFilters);
        
        File selectedfile = filechooser.showOpenDialog(stage);
        
        if (selectedfile != null) {
            System.out.println("Selected file -> " + selectedfile.getAbsolutePath());
            Image imageProfile = new Image(selectedfile.toURI().toString());
            maincontroller.setProfileImage(imageProfile);
            
        }
    } 
   
    public void setMainController(MainController maincontroller){
        this.maincontroller = maincontroller;
    }
    
    
}
    