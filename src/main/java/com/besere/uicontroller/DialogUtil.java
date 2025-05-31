/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.uicontroller;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class DialogUtil {
    
    private static ImageView customIcon;
    
    //SUCCESS UPLOADING DATA
    public static void showSuccess(String title,String msg,String iconPath){
        Alert alert = createAlert(Alert.AlertType.INFORMATION,title,msg, iconPath);
        alert.showAndWait();
    }
    
    //UPDATE DATA CONFIRMATION
    public static void showConfirmation(String title, String msg, String iconPath, Runnable onOkAction){
        Alert alert = createAlert(Alert.AlertType.INFORMATION,title,msg,iconPath);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK && onOkAction != null) {
            onOkAction.run();
        }
    }
    
    public static void showError(String title,String msg,String iconPath){
        Alert alert = createAlert(Alert.AlertType.ERROR,title,msg,iconPath);
        alert.showAndWait();
    }
    
    //SHOW CONFIRMATION DELETE
    public static void showConfirmationDelete(String title,String msg,String iconPath,Runnable onOkAction){
        Alert alert = createAlert(Alert.AlertType.CONFIRMATION,title,msg,iconPath);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK && onOkAction != null) {
            onOkAction.run();
        }
    }
    public static void showErrorDelete(String title,String msg,String iconPath){
        Alert alert = createAlert(Alert.AlertType.ERROR,title, msg, iconPath);
        alert.showAndWait();
    }
    
    //MAIN GETTERS TYPE OF ALERT IN THE GRADE LEVEL CAME FROM
    private static Alert createAlert(Alert.AlertType type,String title,String msg,String iconPath){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        
        if (iconPath != null) {
            customIcon = new ImageView(DialogUtil.class.getResource(iconPath).toExternalForm());
            customIcon.setFitWidth(30);
            customIcon.setFitHeight(30);
            alert.setGraphic(customIcon);
            
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(DialogUtil.class.getResource("/images/logo.png").toExternalForm()));
        }
        
        DialogPane dialogpane = alert.getDialogPane();
        dialogpane.setStyle("-fx-font-weight:15px;"
                + "");
        
        return alert;
    }
        
}