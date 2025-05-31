

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.uicontroller;

import com.besere.controller.MainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;


import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author admin
*/

public class UploadingController implements Initializable{
   
    @FXML private AnchorPane uploadingParentPane;
    @FXML private ProgressBar progressBar;
    @FXML private ImageView loadingImage;

    private MainController main;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(loadingImage);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setByAngle(360);
        rotate.play();

        uploadingParentPane.getStylesheets().add(getClass().getResource("/css/style/StyleMain.css").toExternalForm());
        
    }
    
    public void settaskDeligator(){
        
        Task<Void> taskPrepareData = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("I am runninggggg.......");
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                    updateProgress(i + 1,100);
                    Thread.sleep(200);
                    if (i == 100) {
                        main.submitData();
                    }
                }
               
                updateMessage("Successfully Done!");
                return null;
            }
        };

        main.getMainContent().getChildren().add(uploadingParentPane);
        progressBar.progressProperty().bind(taskPrepareData.progressProperty());

        
        taskPrepareData.setOnSucceeded((event) -> {
            uploadingParentPane.setVisible(false);
            System.out.println("Task Completed!");
        });
        
        new Thread(taskPrepareData).start();
    }
    
    public void setMain(MainController main){
        this.main = main;
    }
    
    
}
