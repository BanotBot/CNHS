
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.StudentService;

import com.besere.StudentProvider.StudentProvider;
import java.sql.Date;

/**
 *
 * @author admin
*/

// WRAPPER FOR STUDENT PROVIDER
public class StudentProviderImpl implements StudentProvider{

    private final Students students;
    
    public StudentProviderImpl(Students students) {
        this.students = students;
    }
    
    @Override
    public int getId() {
        return students.getId();
    }
    @Override
    public String getName() {
        return students.getName();
    }
    @Override
    public String getMname() {
        return students.getMname();
    }
    @Override
    public String getLname() {
        return students.getLname();
    }
    @Override
    public int getAge() {
        return students.getAge();
    }
    @Override
    public Date getBirthdate() {
        return students.getBirthdate();
    }
    @Override
    public int getYearlevel() {
        return students.getYearLevel();
    }
    
}
