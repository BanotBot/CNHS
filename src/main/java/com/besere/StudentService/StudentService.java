
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.StudentService;

import com.besere.StudentDAO.InsertDataQuery;

import java.io.IOException;
import java.sql.Connection;
import com.besere.validator.ValidatorData;
import java.sql.Date;

/**
 *
 * @author admin
*/

public class StudentService
{
    
    private Connection conn;
    
    private String name;
    private String middlename;
    private String lastname;
    private int age;
    private Date birthdate;
    private int yearLevel;
  
    public void handleData() throws IOException, ClassNotFoundException
    {
        System.out.println("connection => " + conn);
        
        //VALIDATE THE DATA
        if (ValidatorData.anynull(getName(),getAge(),getBirthdate(),getYearLevel())) {
            System.out.println("Value is empty");
        }else{
            if (ValidatorData.isvalidatedAge(getAge()) && ValidatorData.isvalidatedYearLevel(getYearLevel())) {
                
                InsertDataQuery insertQuery = new InsertDataQuery(this);
                insertQuery.insertData();
            }
        }
    }
    
    //SETTERS
    public void setName(String name){
        this.name = name;
    }
    public void setMName(String mname){
        this.middlename = mname;
    }
    public void setLName(String lname){
        this.lastname = lname;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setBirthdate(Date birthdate){
        this.birthdate = birthdate;
    }
    public void setYearLevel(int yearlevel){
        this.yearLevel = yearlevel;
    }
    
    public String getName(){
        return name;
    }
    public String getmname(){
        return middlename;
    }
    public String getlname(){
        return lastname;
    }
    public int getAge(){
        return age;
    }
    public Date getBirthdate(){
        return birthdate;
    }
    public int getYearLevel(){
        return yearLevel;
    }
    
    
}
