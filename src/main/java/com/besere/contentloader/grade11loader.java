/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.besere.contentloader;

import com.besere.controller.Grade11Controller;
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

public class grade11loader {
    private MainController mainCont;

    public void load11Content(Parent parentload){
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/grade11.fxml"));
            try {
                parentload = loader.load();
                Grade11Controller refreshData = loader.getController();
                refreshData.refreshData();
                refreshData.setMainController(mainCont);
            } catch (IOException ex) {
                Logger.getLogger(grade7loader.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        mainCont.getMainContent().getChildren().setAll(parentload);
    }
    
    public void setgrade11loader(MainController mainCont) {
        this.mainCont = mainCont;
    }
    
    public void setMainContent(MainController maincont){
        this.mainCont = maincont;
    }
}
