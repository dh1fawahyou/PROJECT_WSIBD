/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBarang;

import com.mysql.jdbc.Connection;
import com.sun.istack.internal.logging.Logger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author Adilah Qurrotu'Aini
 */
public class KoneksiDB {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;

    
//    public KoneksiDB(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_atk_fc","root","");
//            System.out.println("Koneksi Berhasil !");
//        } catch (ClassNotFoundException | SQLException ex) {
//            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//    }
//
//    private static Connection getConnection;
//
//    static PreparedStatement prepareStatement(String sql) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    static java.sql.Connection getKoneksiDB() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    Connection con;
//    PreparedStatement pst;
//    ResultSet rs;
//    Statement st;
//    private String sql;
//    
    public KoneksiDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_atk_fc", "root", "");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Koneksi Berhasil");
    }
//    
    //Barang
    public void insertDB(String kodeBrg, String namaBrg, String stok, String hargaBeli, String hargaJual, String kodeSup) {
        try {
            String sql = "insert into data_brg values (?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeBrg);
            pst.setString(2, namaBrg);
            pst.setString(3, stok);
            pst.setString(4, hargaBeli);
            pst.setString(5, hargaJual);
            pst.setString(6, kodeSup);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertId() {
        try {
            String sql = "insert into detail_transaksi(id) values ('')";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertDetTrans() {
        try {
            String sql = "insert into detail_transaksi(kode_detail_trx, kode_trx, kode_barang, nama_barang, qty, harga_jual, total_harga, harga_beli, total_laba) select * from detail_jual";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDB(String kodeBrg, String namaBrg, String stok, String hargaBeli, String hargaJual, String kodeSup) {
        try {
            String sql = "update data_brg set nama_brg =?, stok =?, hrg_beli =?, "
                    + "hrg_jual =?, kd_sup =? where kd_brg =?";
            pst = con.prepareStatement(sql);
            pst.setString(6, kodeBrg);
            pst.setString(1, namaBrg);
            pst.setString(2, stok);
            pst.setString(3, hargaBeli);
            pst.setString(4, hargaJual);
            pst.setString(5, kodeSup);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet selectDB(String tabel) {
        try {
            String sql = "select * from " + tabel;
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void deleteDB(String kodeBrg) {
        try {
            String sql = "delete from data_brg where kd_brg=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeBrg);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDetail(String qty, String where, int total) {
        try {
            String sql = "update detail_jual set qty =?, total_harga = ?" + where;
            pst = con.prepareStatement(sql);
            pst.setString(1, qty);
            pst.setString(2, String.valueOf(total));
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDetailBrg(String kode) {
        try {
            String sql = "delete from detail_jual where kd_brg=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, kode);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Karyawan
    public void insertBDK(String kodeKry, String namaKry, String alamatKry, String notelpKry, String username, String password) {
        try {
            String sql = "insert into data_kry values (?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeKry);
            pst.setString(2, namaKry);
            pst.setString(3, alamatKry);
            pst.setString(4, notelpKry);
            pst.setString(5, username);
            pst.setString(6, password);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateBDK(String kodeKry, String namaKry, String alamatKry, String notelpKry, String username, String password) {
        try {
            String sql = "update data_kry set nama_kry =?, no_telp =?, alamat =?, "
                    + "username =?, password =? where kd_kry =?";
            pst = con.prepareStatement(sql);
            pst.setString(6, kodeKry);
            pst.setString(1, namaKry);
            pst.setString(2, alamatKry);
            pst.setString(3, notelpKry);
            pst.setString(4, username);
            pst.setString(5, password);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet selectDBK(String tabel) {
        try {
            String sql = "select * from " + tabel;
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void deleteDBK(String kodeKry) {
        try {
            String sql = "delete from data_kry where kd_kry=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeKry);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //SUPPLIER
    public void insertDBS(String kodeSup, String namaSup, String alamatSup, String notelpSup) {
        try {
            String sql = "insert into supplier values (?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeSup);
            pst.setString(2, namaSup);
            pst.setString(3, alamatSup);
            pst.setString(4, notelpSup);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet selectDBS(String tabel) {
        try {
            String sql = "select * from " + tabel;
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void updateDBS(String kodeSup, String namaSup, String alamatSup, String notelpSup) {
        try {
            String sql = "update supplier set nama_sup =?, alamat =?, no_telp =? where kd_sup =?";
            pst = con.prepareStatement(sql);
            pst.setString(5, kodeSup);
            pst.setString(1, namaSup);
            pst.setString(2, notelpSup);
            pst.setString(3, alamatSup);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTrans(String total, int jmlBrg, String bayar, String kembali, String kode) {
        try {
            String sql = "update transaksi set sub_total =?, jml_jenisBrg =?, tot_bayar =?, tot_kembali =?" + kode;
            pst = con.prepareStatement(sql);
            pst.setString(4, kembali);
            pst.setString(1, total);
            pst.setString(2, String.valueOf(jmlBrg));
            pst.setString(3, bayar);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDBS(String kodeSup) {
        try {
            String sql = "delete from supplier where kd_sup=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeSup);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void deleteTrx(String kode) {
        try {
            String sql = "delete from transaksi where kd_trx= ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, kode);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void deleteDetailTrx(String kode) {
        try {
            String sql = "delete from detail_jual where kd_trx= ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, kode);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //TRANSAKSI
    public void insertTrx(String kd_trx, String tgl, String kar, int subtot, int jum_jenbar, int total_bayar, int total_kembali) {
        try {
            String sql = "insert into transaksi values (?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, kd_trx);
            pst.setString(2, tgl);
            pst.setString(3, kar);
            pst.setString(4, String.valueOf(subtot));
            pst.setString(5, String.valueOf(jum_jenbar));
            pst.setString(6, String.valueOf(total_bayar));
            pst.setString(7, String.valueOf(total_kembali));
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertDetailJual(String kd_detail, String kd_trx, String kd_brg, String nama_brg, String qty,
            String hrg_jual,int total, int hrg_beli, int total_laba) {
        try {
            String sql = "insert into detail_jual values (?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, kd_detail);
            pst.setString(2, kd_trx);
            pst.setString(3, kd_brg);
            pst.setString(4, nama_brg);
            pst.setString(5, qty);
            pst.setString(6, hrg_jual);
            pst.setString(7, String.valueOf(total));
            pst.setString(8, String.valueOf(hrg_beli));
            pst.setString(9, String.valueOf(total_laba));
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertHargaBeli(String kd_brg) {
        try {
            String sql = "update detail_jual, data_brg set detail_jual.hrg_beli = data_brg.hrg_beli where detail_jual.kd_brg = data_brg.kd_brg";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    public void insertLaba(String kd_brg) {
        try {
            String sql = "update detail_jual set total_laba = hrg_jual - hrg_beli where kd_brg = '" +kd_brg+ "'";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    public ResultSet selectDBT(String tabel) {
        try {
            String sql = "select * from " + tabel;
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void updateDBT(String kodeBrg, String qty, String bayar) {
        try {
            String sql = "update transaksi set kuantitas =?, tot_bayar =? where kd_brg =?";
            pst = con.prepareStatement(sql);
            pst.setString(3, kodeBrg);
            pst.setString(1, qty);
            pst.setString(2, bayar);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDBT(String kodeBrg) {
        try {
            String sql = "delete from transaksi where kd_brg =?";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeBrg);
            pst.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void checkDBT (String kodeb) {
        try {
            String sql = "select * from data_brg where kd_brg = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeb);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet select(){
        try {
            String sql = "select * from data_brg";
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet selectSum(){
        try {
            String sql = "select SUM(total_harga) AS total from detail_jual";
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    java.sql.Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}