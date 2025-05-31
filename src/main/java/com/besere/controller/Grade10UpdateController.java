/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.besere.controller;

import com.besere.StudentDAO.UpdateDataQuery;
import com.besere.StudentProvider.StudentProvider;
import com.besere.uicontroller.DialogUtil;
import com.besere.validator.ValidatorData;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author admin
*/

public class Grade10UpdateController implements Initializable,StudentProvider{
    @FXML private ImageView myImageBack;
    @FXML private ComboBox<Integer>updateYearLevel;
    @FXML private TextField updateID,updateName,updateMname,updateLname,updateAge;
    @FXML private DatePicker updateBirthDate;
    
    private StudentProvider studentprovider;
    
    private Grade10Controller grade10Controller;
    private MainController maincontroller;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Image image;
   
    protected void setStudentProvider(StudentProvider studentprovider){
        this.studentprovider = studentprovider;
        
        //SETTING DATA IN DIALOG BOX
      
        updateID.setText(String.valueOf(studentprovider.getId()));
        updateName.setText(studentprovider.getName());
        updateMname.setText(studentprovider.getMname());
        updateLname.setText(studentprovider.getLname());
        updateAge.setText(String.valueOf(studentprovider.getAge()));
        updateBirthDate.setValue(studentprovider.getBirthdate().toLocalDate());
        updateYearLevel.setValue(studentprovider.getYearlevel());

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myImageBack.toBack();
        
        int[]yearLevel = {7,8,9,10,11,12};
        for (int i : yearLevel) {
            updateYearLevel.setStyle(""
                + "-fx-font-size:14px;"
                + "-fx-font-family:'Arial';"
                + "-fx-text-fill:blue;");
            updateYearLevel.getItems().add(i);
        }
    }
    
    //UPDATE DIALOG BOX UI
    protected void setDialogStage() throws IOException
    {
        image = new Image(getClass().getResource("/images/logo.png").toExternalForm());
        scene = new Scene(root);
        //stage.initModality(Modality.APPLICATION_MODAL);
        
        stage.setTitle("UPDATE STUDENT");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }
    
    public void updateStudent(ActionEvent event) throws IOException,ClassNotFoundException,SQLException
    {
        if(ValidatorData.anynull(
                studentprovider.getId(),
                studentprovider.getName(),
                studentprovider.getMname(),
                studentprovider.getLname(),
                studentprovider.getAge(),
                studentprovider.getBirthdate(),
                studentprovider.getYearlevel())) {
            
            DialogUtil.showConfirmation(
                "VALIDATION FAILED",
                "PLEASE FILL IN ALL THE FIELDS",
                "/images/error.png",
                ()->{
                    stage.close();
                    grade10Controller.refreshTableAfterUpdate();
                });
            
            return;
        }
        
        if (ValidatorData.isvalidatedAge(studentprovider.getAge()) && ValidatorData.isvalidatedYearLevel(studentprovider.getYearlevel())) {
            //It should go to the update class
            UpdateDataQuery obj = new UpdateDataQuery(this);
            obj.updateData(studentprovider.getYearlevel());
            
            DialogUtil.showConfirmation(
                "SUCCESS",
                "STUDENT WITH ID -> " + studentprovider.getId() + " UPDATED SUCCESSFULLY",
                "/images/check.png",
                ()->{
                    stage.close();
                    grade10Controller.refreshTableAfterUpdate();
            });
        }
    }
 
    
    public void setGrade10Controller(Grade10Controller grade10Controller){
        this.grade10Controller = grade10Controller;
    }
    public void setMainController(MainController maincontroller){
        this.maincontroller = maincontroller;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }
    public void setRoot(Parent root){
        this.root = root;
    }
    
    //GETTERS FOR UPDATE DIALOG
    public Stage getStage(){
        return stage;
    }
    public Scene getScene(){
        return scene;
    }
    public Parent getRoot(){
        return root;
    }
    
    @Override
    public int getId(){
      return Integer.parseInt(updateID.getText());
    }
    @Override
    public String getName(){
        return updateName.getText().strip();
    }
    @Override
    public String getMname(){
        return updateMname.getText().strip();
    }
    @Override
    public String getLname(){
        return updateLname.getText().strip();
    }
    @Override
    public int getAge(){
        return Integer.parseInt(updateAge.getText());
    }
    @Override
    public Date getBirthdate(){
        return java.sql.Date.valueOf(updateBirthDate.getValue());
    }
   
    @Override
    public int getYearlevel() {
        return updateYearLevel.getValue();
    }

    
     
}
