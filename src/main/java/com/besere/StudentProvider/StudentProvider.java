/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.besere.StudentProvider;

import java.sql.Date;

/**
 *
 * @author admin
 */

public interface StudentProvider {
    int getId();
    String getName();
    String getMname();
    String getLname();
    int getAge();
    Date getBirthdate();
    int getYearlevel();
    
}
