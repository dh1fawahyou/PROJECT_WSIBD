/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class connection {
     
    String jdbcDriver = "com.mysql.jdbc.Driver";
    String dbURL = "jdbc:mysql://localhost/db_atk_fc";
    String user = "root";
    String pass = "";
    
    Connection conn;
    Statement stat;
    ResultSet rs;   
    
    public static void main(String[] args) {
        
        connection test = new connection();
        test.koneksi();
        
    }
    
    public void koneksi(){
        try {
            Class.forName(jdbcDriver);
            System.out.println("Class berhasil dipanggil !");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Error !");        }
        
        try {
            conn = (Connection) DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Koneksi berhasil !");
        } catch (SQLException ex) {
            System.out.println("Koneksi Erorr !");        }
    }
}
