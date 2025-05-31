/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.besere.controller.*;
import com.besere.StudentService.StudentService;
import com.besere.StudentService.Students;
import com.besere.database.DatabaseConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.besere.validator.ValidatorData;

/**
 *
 * @author admin
*/



public class Grade7UpdateController implements Initializable{

    @FXML
    private ImageView myImageBack;
    @FXML
    private ComboBox<Integer>updateYearLevel;
    @FXML
    private TextField updateID,updateName,updateAge;
    @FXML
    private DatePicker updateBirthDate;
    private Students selectedstudents;
    
    
    protected void setStudentData(Students students){
        this.selectedstudents = students;
        
        //SETTING DATA IN DIALOG BOX
        if (students != null) {
            updateID.setText(String.valueOf(selectedstudents.getId()));
            updateName.setText(selectedstudents.getName());
            updateAge.setText(String.valueOf(selectedstudents.getAge()));
            updateBirthDate.setValue(selectedstudents.getBirthdate().toLocalDate());
            updateYearLevel.setValue(selectedstudents.getYearLevel());
        }else{
            System.out.println("TABLE CELL IS EMPTY");
        }
    }
    
    //UPDATE DIALOG BOX UI
    protected void setDialogStage(Stage stage,Scene scene,Parent root){
        
        Image image = new Image(getClass().getResource("/images/logo.png").toExternalForm());
        stage = new Stage();
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        stage.setTitle("UPDATE STUDENT");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
               
        setCloseOperation(stage,image);
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
    
    
    private void setCloseOperation(Stage stage,Image image){

        ImageView customIcon = new ImageView(getClass().getResource("/images/exclamation.png").toExternalForm());
        customIcon.setFitWidth(30);
        customIcon.setFitHeight(30);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CLOSE DIALOG");
        alert.setHeaderText("You're about to exit");
        alert.setContentText("Do you want to save before exiting?");
        alert.setGraphic(customIcon);
        
        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(image);
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("updated successfully");
            stage.close();
        }
    }
    
    
    public void updateStudent(ActionEvent event) throws IOException,ClassNotFoundException,SQLException{

        if (ValidatorData.anynull(
                getId(),
                getName(),
                getAge(),
                getBirthDate(),
                getYearLevel())) {
            System.out.println("Value updated is empty");
        }
        
        if (ValidatorData.isvalidatedAge(getAge()) && ValidatorData.isvalidatedYearLevel(getYearLevel())) {
            String table = "grade"+getYearLevel();
            String query = "UPDATE " + table + " SET name= ?,age=?,birthdate=?,year_level=? WHERE id=?";
             
            try(Connection conn = DatabaseConnection.getconnection();
                PreparedStatement prepared = conn.prepareStatement(query);
                ){
                
                prepared.setString(1,selectedstudents.getName());
                prepared.setInt(2,selectedstudents.getAge());
                prepared.setDate(3,selectedstudents.getBirthdate());
                prepared.setInt(4,selectedstudents.getYearLevel());
                prepared.setInt(5,selectedstudents.getId());
                
                int rowUpdated = prepared.executeUpdate();
                
                if (rowUpdated > 0) {
                    System.out.println("Student with id -> " + getId() + " updated successfully");
                }else{
                    System.out.println("No student found with ID : " + getId());
                }
                
            } catch (Exception e) {
                System.out.println("Error updating -> " + e.getMessage());
            }
        }
    }
    
    public int getId(){
        if (updateID == null || updateID.getText() == null || updateID.getText().isBlank()) {
            throw new IllegalStateException("Student id field is not initialized!!!");
        }
        return Integer.parseInt(updateID.getText().strip());
    }
    public String getName(){
        return updateName.getText().stripIndent();
    }
    public int getAge(){
        return Integer.parseInt(updateAge.getText().stripIndent());
    }
    public LocalDate getBirthDate(){
        return updateBirthDate.getValue();
    }
    public int getYearLevel(){
        return updateYearLevel.getValue();
    }
    
   
}
