/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBarang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Adilah Qurrotu'Aini
 */
public class KoneksiLog {
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    Statement st;
    
    public void KoneksiLog() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/wsi", "root", "");
            st = con.createStatement();
            System.out.println("Koneksi Berhasil");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KoneksiLog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
