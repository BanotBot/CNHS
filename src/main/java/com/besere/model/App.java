
package com.besere.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.image.Image;

/**
 * JavaFX App
*/

// MAIN ENTRY POINT
public class App extends Application
{
    private static Scene scene;
    private Parent root;
    
    @Override
    public void start(Stage stage) throws IOException
    {
        try
        {
             root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
           // root.setStyle("-fx-background-color:blue;");
            
            Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
            
            scene = new Scene(root);
            stage.setTitle("CNHS");
            stage.getIcons().add(icon);
            stage.setScene(scene);
        
            //stage.setMaximized(true);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch();
    }
 
  
}