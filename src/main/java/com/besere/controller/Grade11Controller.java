/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.controller;

import com.besere.StudentDAO.DeleteDataQuery;
import com.besere.StudentProvider.StudentTableController;
import com.besere.StudentService.StudentProviderImpl;
import com.besere.StudentService.Students;
import com.besere.database.DatabaseConnection;
import com.besere.uicontroller.DialogUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 *
 * @author admin
*/

public class Grade11Controller implements Initializable, StudentTableController {

    @FXML TextField searchField;

    @FXML private TableView<Students> grade11Table;
    @FXML private ObservableList<Students> students;

    @FXML private TableColumn<Students, Integer> idColumn;
    @FXML private TableColumn<Students, String> nameColumn;
    @FXML private TableColumn<Students, String> middlenameColumn;
    @FXML private TableColumn<Students, String> lastnameColumn;
    @FXML private TableColumn<Students, Integer> ageColumn;
    @FXML private TableColumn<Students, Date> birthdateColumn;
    @FXML private TableColumn<Students, Integer> yearLevelColumn;

    private Students selectedStud;
    private MainController maincontroller;
    
    private Stage stage;
    private Parent root;
    private Scene scene;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stage = new Stage();
        students = FXCollections.observableArrayList();

        // get the getter name method in the students
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        middlenameColumn.setCellValueFactory(new PropertyValueFactory<>("mname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        yearLevelColumn.setCellValueFactory(new PropertyValueFactory<>("yearLevel"));

        setStyleData(); // style data table cell
        fetchData(); // fetch data from db
        searchListImpl();
       
        grade11Table.setOnMouseClicked((var e) -> {
            if (e.getClickCount() == 2) {
                selectedStud = grade11Table.getSelectionModel().getSelectedItem();
                //LOADER FOR DIALOG CONTROLLER

                if (selectedStud != null) {
                    System.out.println("I am editable!!!");
                    loaderDialog();
                }
            }
        });
        
        grade11Table.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                System.out.println("I am working delete and backspace");
                selectedStud = grade11Table.getSelectionModel().getSelectedItem();
                
                if (selectedStud != null) {
                    DialogUtil.showConfirmationDelete("DELETE DATA",
                        "ARE YOU SURE YOU WANT TO DELETE?",
                        "/images/delete.png",
                        ()->{
                            DeleteDataQuery.deleteRecord("grade11",selectedStud.getId());
                            refreshTableAfterUpdate();
                        }
                    );
                    
                }else{
                    System.out.println("Null selectedStud");
                }
            }
        });
    }
    
    private void searchListImpl() {
        
        searchField.textProperty().addListener((obs,oldval,newval)->{
            System.out.println("NewValue -> " + newval);
           grade11Table.refresh();
        });
        
        grade11Table.setRowFactory(tableview -> new TableRow<Students>(){
            @Override
            protected void updateItem(Students student, boolean empty)
            {
                super.updateItem(student,empty);
                    if (empty || student == null) {
                        setStyle("");
                    }else{
                        String filter = searchField.getText().toLowerCase();
                        System.out.println("filter -> " + filter);
                        
                        if (filter.isEmpty()) {
                            setStyle("");
                        }else{
                            System.out.println("getname -> " + student.getName());
                            boolean match = 
                                String.valueOf(student.getId()).startsWith(filter) ||
                                student.getName().toLowerCase().startsWith(filter) ||
                                student.getMname().toLowerCase().startsWith(filter)|| 
                                student.getLname().toLowerCase().startsWith(filter) ||
                                String.valueOf(student.getAge()).startsWith(filter) ||
                                String.valueOf(student.getBirthdate()).startsWith(filter)||
                                String.valueOf(student.getYearLevel()).startsWith(filter);
                            
                            setStyle(match ? "-fx-background-color:yellow;" : "");
                        }
                    }
            }
            
        });
    }
    
    //LOADER DIALOG
    public void loaderDialog() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grade11UpdateDialog.fxml"));
        try {
            root = loader.load();
            Grade11UpdateController controller = loader.getController();

            controller.setStudentProvider(new StudentProviderImpl(selectedStud));
            controller.setStage(stage);
            controller.setScene(scene);
            controller.setRoot(root);
            controller.setGrade11Controller(this);
            controller.setMainController(maincontroller);
            controller.setDialogStage();

        } catch (IOException ex) {
            Logger.getLogger(Grade11Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //STYLE DIALOG
    private void setStyleData() {
        for (TableColumn<?, ?> columns : grade11Table.getColumns()) {
            columns.setReorderable(false);
            columns.setResizable(false);
        }
        grade11Table.getStylesheets().add(getClass().getResource("/css/style/grade11Style.css").toExternalForm());
    }

    //FETCH THE DATA
    private void fetchData() {
        
        Task<Void> taskFetch = new Task<>(){
            @Override
            protected Void call() throws Exception {
                students.clear();
                String sql = "SELECT * FROM grade11";
                try (
                    Connection conn = DatabaseConnection.getconnection(); PreparedStatement prepared = conn.prepareStatement(sql)) {

                    ResultSet rs = prepared.executeQuery();

                    while (rs.next()) {
                        students.add(new Students(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("middlename"),
                            rs.getString("lastname"),
                            rs.getInt("age"),
                            rs.getDate("birthdate"),
                            rs.getInt("year_level")
                        ));
                    }
                    
                } catch (Exception e) {
                }
                return null;
            }
        };
        
        Platform.runLater(()->{
            grade11Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // set the table to not include the extra col
            grade11Table.setItems(students);
            grade11Table.refresh();
        });
        
        Thread thread = new Thread(taskFetch);
        thread.start();
        
        try {
            thread.join();
        } catch (InterruptedException e) {
        }
        
    }

    //Click label11 refresher
    public void refreshData() {
        System.out.println("Calling refresh data!!!");
        fetchData();
    }

    //FETCH THE DATA AGAIN IN ORDER TO REFRESH TABLE 
    @Override
    public void refreshTableAfterUpdate() {
        fetchData();
        grade11Table.refresh();
    }
   
    public void setMainController(MainController maincontroller){
        this.maincontroller = maincontroller;
    }
    
}

