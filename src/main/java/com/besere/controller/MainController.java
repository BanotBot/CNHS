
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.controller;

import com.besere.StudentService.StudentService;
import com.besere.StudentService.Students;
import com.besere.contentloader.grade10loader;
import com.besere.contentloader.grade11loader;
import com.besere.contentloader.grade12loader;
import com.besere.contentloader.grade7loader;
import com.besere.contentloader.grade8loader;
import com.besere.contentloader.grade9loader;
import com.besere.filechooser.FileChooserDialog;
import com.besere.uicontroller.DialogUtil;
import com.besere.uicontroller.UploadingController;
import com.besere.validator.ValidatorData;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 *
 * @author admin
*/

public class MainController implements Initializable
{

    @FXML private AnchorPane mainPane;
    @FXML private Pane mainContentPane;
    @FXML private ImageView logoImage;
    @FXML private Label logoLabel;
    
    @FXML private ImageView profileImage;
    @FXML private Label profile_username;
    @FXML private ComboBox<Integer> yearLevel;
    
    @FXML private TextField nameField;
    @FXML private TextField mnameField;
    @FXML private TextField lnameField;
    @FXML private TextField ageField;
    @FXML private DatePicker myBirthdate;
    
    @FXML private Label grade7;
    @FXML private Label grade8;
    @FXML private Label grade9;
    @FXML private Label grade10;
    @FXML private Label senior;
    
    @FXML private ImageView loadingImage;
    @FXML private VBox senior_high_container;
    @FXML private Label grade11,grade12;
    
    private Label currentLabel = null;
    private String clicked;
    private List<Students> studentsList ;
    private StudentService studobj;

    private Stage stage;
    private Scene scene;
    private Parent root;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        stage = new Stage();
        
        studobj = new StudentService();
        studentsList = new LinkedList<>();
        
        int[]ylvl = {7,8,9,10,11,12};
      
        for (int i : ylvl)
        {
            yearLevel.setStyle(""
                    + "-fx-font-style:'Arial';"
                    + "-fx-font-size:20px;"
                    + "-fx-text-fill:blue;");
            yearLevel.getItems().add(i);
        }
        
        grade7.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> {
            try {
                selectLabel(grade7,e);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
            }
        });
        grade8.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> {
            try {
                selectLabel(grade8,e);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grade9.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> {
            try {
                selectLabel(grade9,e);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grade10.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> {
            try {
                selectLabel(grade10,e);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        senior.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            try {
                selectLabel(senior,e);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
            }
        });
        grade11.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
            try {
                selectLabel(grade11, e);
            } catch (IOException ex) {
            }
        });
        grade12.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
            try {
                selectLabel(grade12, e);
            } catch (IOException ex) {
            }
        });
        
        logoImage.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("I am clicking.............");
            try {
                root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
                mainPane.getChildren().setAll(root);
            } catch (IOException ex) {
                System.out.println("Error -> " + ex.getMessage());
            }
        });
        
        logoLabel.addEventHandler(MouseEvent.MOUSE_CLICKED,e -> {
            System.out.println("I am clicking.............");
            try {
                root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
                mainPane.getChildren().setAll(root);
            } catch (IOException ex) {
                System.out.println("Error -> " + ex.getMessage());
            }
        });
        
        profileImage.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
            //FILE CHOOSER FOR UPLOAD PHOTO
            FileChooserDialog getFile = new FileChooserDialog();
            getFile.setMainController(this);
            getFile.showFileChooser(getStage());
            setProfileImageStyle();
        });
        
        
        myBirthdate.valueProperty().addListener((obs,oldval,newval)->{
            if (newval != null) {
                int age = calculateAge(newval,LocalDate.now());
                ageField.setText(String.valueOf(age));
            }
        });
        mainPane.getStylesheets().add(getClass().getResource("/css/style/StyleMain.css").toExternalForm());
    }
    
    public void submitStud(ActionEvent event) throws IOException, ClassNotFoundException, SQLException
    {
        
        try {
            
            String name = nameField.getText().strip();
            String mname = mnameField.getText().strip();
            String lname = lnameField.getText().strip();

            int age = Integer.parseInt(ageField.getText().strip());

            java.sql.Date birthdate = java.sql.Date.valueOf(myBirthdate.getValue());
            int yLevel = yearLevel.getValue();

            
            if (!ValidatorData.anynull(name,mname,lname,age,birthdate,yLevel)) {
                
                System.out.println("First Task......");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProgressBar.fxml"));
                root = loader.load();

                UploadingController uploadingController = loader.getController();
                
                uploadingController.setMain(this);
                uploadingController.settaskDeligator();
                Students student = new Students(0,name,mname,lname,age,birthdate,yLevel);
                
                System.out.println("Set students runninggggg...........");
        
                try {
                    studobj.setName(name);
                    studobj.setMName(mname);
                    studobj.setLName(lname);
                    studobj.setAge(age);
                    studobj.setBirthdate(birthdate);
                    studobj.setYearLevel(yLevel);

                } catch (NumberFormatException e) {
                    System.err.println("Invalid input Age!!!");
                }catch (IllegalArgumentException ex){
                    System.err.println(ex.getMessage());
                }
        
                //Clear the input field after the upload of the data
                Platform.runLater(() ->{
                    studentsList.add(student);
                    nameField.clear();
                    mnameField.clear();
                    lnameField.clear();
                    ageField.clear();
                    myBirthdate.setValue(null);
                    yearLevel.setValue(null);
                });
            }
            
        } catch (IOException | NumberFormatException e) {
            DialogUtil.showError(
                "DATAFIELD MUST NOT BE EMPTY",
                "PLEASE INPUT ALL DATA FIELDS",
                "/images/error.png"
            );
        }
    }
    
    public void submitData() throws IOException,ClassNotFoundException
    {
        studobj.handleData();
    }
    
    private void selectLabel(Label label,MouseEvent event) throws IOException
    {
        if (currentLabel != null) {
            currentLabel.setBackground(Background.EMPTY);
        }
        
        label.setBackground(new Background(
            new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY,Insets.EMPTY)
        ));
        
        currentLabel = label;
        
        Label clickedLabel = (Label) event.getSource();
        System.out.println("Clicked Label => " + clickedLabel);
        
        clicked = clickedLabel.getText().replaceAll("[^0-9]",""); 
        System.out.println("Clicked -> " + clicked);
        
        switch ((clicked)) {
            
            case "7"->{
                //MAIN CONTENT OF GRADE 7
                grade7loader loadFile = new grade7loader();
                loadFile.setMainContent(this);
                loadFile.load7Content(root);
            }
            case "8"->{
                grade8loader loadFile = new grade8loader();
                loadFile.setMainContent(this);
                loadFile.load8Content(root);
            }
            case "9"->{
                grade9loader loadFile = new grade9loader();
                loadFile.setMainContent(this);
                loadFile.load9Content(root);
            }
            case "10"->{
                grade10loader loadFile = new grade10loader();
                loadFile.setMainContent(this);
                loadFile.load10Content(root);
            }
            case "11"->{
                grade11loader loadFile  = new grade11loader();
                loadFile.setMainContent(this);
                loadFile.load11Content(root);
            }
            case "12"->{
                grade12loader loadFile = new grade12loader();
                loadFile.setMainContent(this);
                loadFile.load12Content(root);
            }
            default-> {
                seniorHighMenu();
            }
        }
    }
    
    private int calculateAge(LocalDate birthdate,LocalDate currentDate){
        if (birthdate != null && currentDate != null)
            return Period.between(birthdate, currentDate).getYears();
        return 0;
    }
    
    private void seniorHighMenu(){
      senior_high_container.setVisible(true);
      grade11.setVisible(true);
      grade12.setVisible(true);
    }
    private void setProfileImageStyle(){
        profileImage.setFitHeight(70);
        profileImage.setFitWidth(70);
        profileImage.setPreserveRatio(false);
      
        Circle clip = new Circle(35,35,35);
        profileImage.setClip(clip);
    }
    public void setProfileUsername(String newusername){
       this.profile_username.setText(newusername);
    }
    public void setProfileImage(Image image){
        profileImage.setImage(image);
    }

    public AnchorPane getmainPane(){
        return mainPane;
    }
    public Pane getMainContent(){
      return mainContentPane;
    }
    public Stage getStage(){
        return stage;
    }
    public int getClicked(){
        return Integer.parseInt(clicked);
    }
    public ImageView getLoadingImage(){
        return loadingImage;
    }
    
}

