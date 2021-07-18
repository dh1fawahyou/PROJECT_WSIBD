/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBarang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Adilah Qurrotu'Aini
 */
public class FormBarang extends javax.swing.JFrame {
    Statement st;
    Connection con;
    
    public void showDate() {
        Date td = new Date();
        SimpleDateFormat t = new SimpleDateFormat("dd-MM-yyyy");
        txtDate.setText(t.format(td));
    }

    /**
     * Creates new form FormBarang
     */
    public FormBarang() {
        this.setExtendedState(Login.MAXIMIZED_VERT);
        dB = new KoneksiDB();
        initComponents();
        showTable();
        showDate();
    }
    
    void username (String user) {
        txtUser.setText(user);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtKodeBrg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNamaBrg = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtHargaBeli = new javax.swing.JTextField();
        btnTambahBrg = new javax.swing.JButton();
        btnUbahBrg = new javax.swing.JButton();
        btnHapuBrg = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtHargaJual = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtCariBrg = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnCetak = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtKodeSup = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnX = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBrg = new javax.swing.JTable();
        btnClean = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DATA BARANG");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Kode Barang");

        txtKodeBrg.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Stok");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Harga Beli");

        txtNamaBrg.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtStok.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtHargaBeli.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnTambahBrg.setBackground(new java.awt.Color(255, 255, 255));
        btnTambahBrg.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambahBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/add.png"))); // NOI18N
        btnTambahBrg.setText("TAMBAH");
        btnTambahBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahBrgActionPerformed(evt);
            }
        });

        btnUbahBrg.setBackground(new java.awt.Color(255, 255, 255));
        btnUbahBrg.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUbahBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/edit.png"))); // NOI18N
        btnUbahBrg.setText("UBAH");
        btnUbahBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahBrgActionPerformed(evt);
            }
        });

        btnHapuBrg.setBackground(new java.awt.Color(255, 255, 255));
        btnHapuBrg.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapuBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/trash.png"))); // NOI18N
        btnHapuBrg.setText("HAPUS");
        btnHapuBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapuBrgActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Harga Jual");

        txtHargaJual.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/user (2).png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/calendar.png"))); // NOI18N

        txtUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUser.setEnabled(false);

        txtDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDate.setEnabled(false);
        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        txtCariBrg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCariBrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariBrgKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Data Barang");

        btnCetak.setBackground(new java.awt.Color(255, 255, 255));
        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/printer.png"))); // NOI18N
        btnCetak.setText("CETAK");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Kode Supplier");

        txtKodeSup.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel14.setText("CARI BARANG");

        btnX.setBackground(new java.awt.Color(255, 255, 255));
        btnX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnX.setForeground(new java.awt.Color(255, 255, 255));
        btnX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/cancel.png"))); // NOI18N
        btnX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 123, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("DATA BARANG ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(344, 344, 344)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblBrg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tblBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBrgMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBrg);

        btnClean.setBackground(new java.awt.Color(255, 255, 255));
        btnClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/sweeping.png"))); // NOI18N
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtKodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClean))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)
                                .addComponent(txtHargaBeli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtStok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNamaBrg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtHargaJual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtKodeSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnTambahBrg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHapuBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUbahBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCariBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnClean)
                            .addComponent(txtKodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(2, 2, 2)
                        .addComponent(txtNamaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKodeSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambahBrg)
                            .addComponent(btnUbahBrg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHapuBrg)
                            .addComponent(btnCetak))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnX)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtCariBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2)
                        .addGap(392, 392, 392))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahBrgActionPerformed
        String kodeBrg, namaBrg, stok, hargaBeli, hargaJual, kodeSup;
        kodeBrg = txtKodeBrg.getText();
        namaBrg = txtNamaBrg.getText();
        stok = txtStok.getText();
        hargaBeli = txtHargaBeli.getText();
        hargaJual = txtHargaJual.getText();
        kodeSup = txtKodeSup.getText();
        dB.insertDB(kodeBrg, namaBrg, stok, hargaBeli, hargaJual, kodeSup);
        JOptionPane.showMessageDialog(this, "Barang Berhasil Ditambahkan!", 
                "Penambahan Barang", JOptionPane.INFORMATION_MESSAGE);
        showTable();
        txtKodeBrg.setText("");
        txtNamaBrg.setText("");
        txtStok.setText("");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
        txtKodeSup.setText("");
    }//GEN-LAST:event_btnTambahBrgActionPerformed

    private void tblBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBrgMouseClicked
        int row = tblBrg.getSelectedRow();
        txtKodeBrg.setText(tblBrg.getValueAt(row, 0).toString());
        txtNamaBrg.setText(tblBrg.getValueAt(row, 1).toString());
        txtStok.setText(tblBrg.getValueAt(row, 2).toString());
        txtHargaBeli.setText(tblBrg.getValueAt(row, 3).toString());
        txtHargaJual.setText(tblBrg.getValueAt(row, 4).toString());
        txtKodeSup.setText(tblBrg.getValueAt(row, 5).toString());
    }//GEN-LAST:event_tblBrgMouseClicked

    private void btnHapuBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapuBrgActionPerformed
        String kodeBrg;
        kodeBrg = txtKodeBrg.getText();
        dB.deleteDB(kodeBrg);
        JOptionPane.showConfirmDialog(rootPane, "Anda yakin akan  menghapus data?", 
                "Penghapusan Data", JOptionPane.YES_NO_OPTION);
        JOptionPane.showMessageDialog(rootPane, "Data Berhasil Dihapus!", 
                "Penghapusan Data", JOptionPane.INFORMATION_MESSAGE);
        showTable();
        txtKodeBrg.setText("");
        txtNamaBrg.setText("");
        txtStok.setText("");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
        txtKodeSup.setText("");
    }//GEN-LAST:event_btnHapuBrgActionPerformed

    private void btnUbahBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahBrgActionPerformed
        String kodeBrg, namaBrg, stok, hargaBeli, hargaJual, kodeSup;
        kodeBrg = txtKodeBrg.getText();
        namaBrg = txtNamaBrg.getText();
        stok = txtStok.getText();
        hargaBeli = txtHargaBeli.getText();
        hargaJual = txtHargaJual.getText();
        kodeSup = txtKodeSup.getText();
        dB.updateDB(kodeBrg, namaBrg, stok, hargaBeli, hargaJual, kodeSup);
        JOptionPane.showMessageDialog(this, "Data Berhasil Diubah!", "Perubahan Data",
                JOptionPane.INFORMATION_MESSAGE);
        showTable();
        txtKodeBrg.setText("");
        txtNamaBrg.setText("");
        txtStok.setText("");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
        txtKodeSup.setText("");
    }//GEN-LAST:event_btnUbahBrgActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed

    }//GEN-LAST:event_txtDateActionPerformed

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
        String user = txtUser.getText();
        FormWellcome fw = new FormWellcome();
        fw.setVisible(true);
        fw.username(user);
        dispose();
    }//GEN-LAST:event_btnXActionPerformed

    private void txtCariBrgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariBrgKeyTyped
        showTable();
    }//GEN-LAST:event_txtCariBrgKeyTyped

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        txtKodeBrg.setText("");
        txtNamaBrg.setText("");
        txtStok.setText("");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
        txtKodeSup.setText("");
    }//GEN-LAST:event_btnCleanActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/wsi", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String file = "F:\\TIF C\\Semester 2\\Workshop SI Berbasis Desktop\\project\\ProgramPenjualan fix\\ProgramPenjualan fix\\ProgramPenjualan\\ProgramPenjualan\\src\\report\\LaporanBarang.jrxml";
        JasperReport jr;
        try {
            jr = JasperCompileManager.compileReport(file);
            JasperPrint jp = JasperFillManager.fillReport(jr,null, conn);
            JasperViewer.viewReport(jp);
        } catch (JRException ex) {
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    public void showTable() {
        try {
            String cari = txtCariBrg.getText();
            tbmBarang = new DefaultTableModel(new String[]{"Kode Barang", "Nama Barang", "Stok", "Harga Beli", "Harga Jual", "Kode Suplier", "Nama Supplier"},0);
            ResultSet rs;
            rs = dB.selectDB("data_brg, supplier where data_brg.kd_sup = supplier.kd_sup and nama_brg LIKE '%"+ cari +"%'");
            while (rs.next()) {
                tbmBarang.addRow(new Object[]{
                    rs.getString("data_brg.kd_brg"),
                    rs.getString("data_brg.nama_brg"),
                    rs.getString("data_brg.stok"),
                    rs.getString("data_brg.hrg_beli"),
                    rs.getString("data_brg.hrg_jual"),
                    rs.getString("data_brg.kd_sup"),
                    rs.getString("supplier.nama_sup")});                      
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblBrg.setModel(tbmBarang);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBarang().setVisible(true);
            }
        });
    }

    KoneksiDB dB;
    DefaultTableModel tbmBarang;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnHapuBrg;
    private javax.swing.JButton btnTambahBrg;
    private javax.swing.JButton btnUbahBrg;
    private javax.swing.JButton btnX;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBrg;
    private javax.swing.JTextField txtCariBrg;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtHargaBeli;
    private javax.swing.JTextField txtHargaJual;
    private javax.swing.JTextField txtKodeBrg;
    private javax.swing.JTextField txtKodeSup;
    private javax.swing.JTextField txtNamaBrg;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

    private JFrame JFrame(String exit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}