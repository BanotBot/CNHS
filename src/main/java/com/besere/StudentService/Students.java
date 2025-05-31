
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.StudentService;

import java.sql.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author admin
*/

public class Students
{
    private Students studentsObj;
    
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty mname;
    private final SimpleStringProperty lname;
    private final SimpleIntegerProperty age;
    private final SimpleObjectProperty<Date> birthdate;
    private final SimpleIntegerProperty yearLevel;
    
    public Students(int id,String name,String mname,String lname,int age,Date birthdate,int yearLevel)
    {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.mname = new SimpleStringProperty(mname);
        this.lname = new SimpleStringProperty(lname);
        this.age = new SimpleIntegerProperty(age);
        this.birthdate = new SimpleObjectProperty(birthdate);
        this.yearLevel = new SimpleIntegerProperty(yearLevel);
    }
    
    // SETTER
    public void setid(int id){
        this.id.set(id);
    }
    public void setname(String name){
        this.name.set(name);
    }
    public void setmname(String mname){
        this.mname.set(mname);
    }
    public void setlname(String lname){
        this.lname.set(lname);
    }
    public void setage(int age){
        this.age.set(age);
    }
    public void setBirthDate(Date birthdate){
        this.birthdate.set(birthdate);
    }
    public void setYearLevel(int yearLevel){
        this.yearLevel.set(yearLevel);
    }
    
    // GETTER 
    public int getId(){
        return id.get(); 
    }
    public String getName(){
        return name.get();
    }
    public String getMname(){
        return mname.get();
    }
    public String getLname(){
        return lname.get();
    }
    public int getAge(){
        return age.get();
    }
    public Date getBirthdate(){
        return birthdate.get();
    }
    public int getYearLevel(){
        return yearLevel.get();
    }
   
}

