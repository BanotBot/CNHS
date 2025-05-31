
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.uicontroller;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author admin
*/

public class DialogLoader{

    private ImageView loadingImage;
    
    public void dialogAnimation(){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(loadingImage);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setByAngle(360);
        rotate.play();
    }
   
    public void setImageView(ImageView loadingImage){
        this.loadingImage = loadingImage;
    }
    
    public ImageView getImage(){
        return loadingImage;
    }
    
}
