/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detail_laporan;
 
/**
 *
 * @author Dell
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class laporanDb {
    Connection con;
    
    public laporanDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_atk_fc","root","");
            System.out.println("Berhasil !");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(laporanDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(laporanDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
