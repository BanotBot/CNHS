
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.besere.StudentDAO;

import com.besere.database.DatabaseConnection;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */

public class LoginDataQuery {
    
    public static void processLogin(String username,String password) throws IOException, ClassNotFoundException, SQLException{
        String loginQuery = "SELECT username,password FROM user WHERE BINARY username=?";
        
        try (Connection connection = DatabaseConnection.getconnection();
             PreparedStatement prepared = connection.prepareStatement(loginQuery);
            ){
            
            prepared.setString(1,username);
          
            ResultSet result = prepared.executeQuery();
            if (result.next()) {
                String hashpassword = result.getString("password");
                if (passwordVerification(password,hashpassword)) {
                    System.out.println("Correctness");
                }else{
                    System.out.println("Wrongggness");
                }
            }
            System.out.println("Done logging in....");
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage());
        }
    }
    
    private static boolean passwordVerification(String inputPassword,String hashpassword){
        //String hashpassInput = hashPassword(inputPassword);
        return inputPassword.equals(hashpassword);
    }
    
    private static String hashPassword(String password){
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] encodeHash = digest.digest(password.getBytes());
            StringBuilder hextString = new StringBuilder();
            
            for (byte b : encodeHash) {
                hextString.append(String.format("%02x",b));
            }
            return hextString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
