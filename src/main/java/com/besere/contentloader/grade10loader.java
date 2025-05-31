/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.besere.contentloader;

import com.besere.controller.Grade10Controller;
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

public class grade10loader {
    private MainController mainCont;

    public void load10Content(Parent parentload){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/grade10.fxml"));
            try {
                parentload = loader.load();
                Grade10Controller refreshData = loader.getController();
                refreshData.refreshData();
                refreshData.setMainController(mainCont);
            } catch (IOException ex) {
                Logger.getLogger(grade7loader.class.getName()).log(Level.SEVERE, null, ex);
            }

        mainCont.getMainContent().getChildren().setAll(parentload);
    }
    
    public void setgrade10loader(MainController mainCont) {
        this.mainCont = mainCont;
    }
    
    public void setMainContent(MainController maincont){
        this.mainCont = maincont;
    }
}
