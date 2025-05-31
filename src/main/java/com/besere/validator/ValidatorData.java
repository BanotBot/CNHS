/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.validator;

/**
 *
 * @author admin
*/

public class ValidatorData {
   
    //CHECK DATA IF NULL
    public static boolean anynull(Object... objects)
    {
        for (Object object : objects){
            if (object != null) {
                return false;
            }
        }
       return true;
    }
    
    public static boolean isvalidatedAge(int age)
    {
        return !(age < 12 || age > 120);
    }
    
    public static boolean isvalidatedYearLevel(int validateyear)
    {
       return !(validateyear < 7 || validateyear > 12);
    }
}
