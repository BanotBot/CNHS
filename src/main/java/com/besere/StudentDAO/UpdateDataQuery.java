
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

package com.besere.StudentDAO;

import com.besere.StudentProvider.StudentProvider;
import com.besere.database.DatabaseConnection;
import com.besere.uicontroller.DialogUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author admin
*/

public class UpdateDataQuery {
    
    private final StudentProvider studentprovider;

    public UpdateDataQuery(StudentProvider studentprovider) {
        this.studentprovider = studentprovider;
    }
    
    public void updateData(int yearlevel){
        
        String oldTable = "grade"+String.valueOf(yearlevel);
        System.out.println("Old Table => " + oldTable);
        String newTable = "grade"+String.valueOf(studentprovider.getYearlevel());
        
            String DeleteQuery = "DELETE FROM " + oldTable + " WHERE id=?";
            String InsertQuery = "INSERT INTO " + newTable + " (id,name,middlename,lastname,age,birthdate,year_level) VALUES(?,?,?,?,?,?,?)";

            try(
                    Connection conn = DatabaseConnection.getconnection();
                    
                    PreparedStatement Delete = conn.prepareStatement(DeleteQuery);
                    PreparedStatement Insert = conn.prepareStatement(InsertQuery);
                    
                ){
                
                    Delete.setInt(1,studentprovider.getId());
                    int deleteUpdate = Delete.executeUpdate();
                    
                    Insert.setInt(1,studentprovider.getId());
                    Insert.setString(2,studentprovider.getName());
                    Insert.setString(3,studentprovider.getMname());
                    Insert.setString(4,studentprovider.getLname());
                    Insert.setInt(5,studentprovider.getAge());
                    Insert.setDate(6,studentprovider.getBirthdate());
                    Insert.setInt(7,studentprovider.getYearlevel());
                    
                    int insertUpdate = Insert.executeUpdate();

                System.out.println("RowUpdated => " + insertUpdate);
                System.out.println("Result => " + deleteUpdate);
                
                if(deleteUpdate > 0) {
                    //conn.commit();
                    System.out.println("Updated Data => " + studentprovider.getName());
                    System.out.println("Nisulod ko diri");
                }else{
                    //conn.rollback();
                    DialogUtil.showError(
                        "ERROR",
                        "Students with id -> " + studentprovider.getId(),
                        "/images/error.png"
                    );
                }
            }catch (Exception e) {
             
            }
            
    }
}

