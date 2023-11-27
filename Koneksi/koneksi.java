/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class koneksi {
    private static Connection cn;
    //mendeklarasikan variable connection
   
   public static Connection getkoneksi(){
       if (cn == null){
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                cn = DriverManager.getConnection("jdbc:mysql://localhost/perpus","root","");
                System.out.println("Berhasil");
            }
            catch (SQLException e){
                e.getStackTrace();
            }
       }
       return cn;
   } 
    
}
