/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBarang;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.util.Date;
import java.text.SimpleDateFormat;
import static java.time.LocalDate.now;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adilah Qurrotu'Aini
 */
public class FormTransaksi extends javax.swing.JFrame {

    public FormTransaksi() {
        int subtot = 0, jml_jenbar = 0, tot_bayar = 0, tot_kembali = 0;
        dB = new KoneksiDB();
        initComponents();
        showDate();
        kodeTransaksi();
        middle();
        txtKosong();
        txtGrandTotal.setEnabled(false);
        String kar = "KAR001";
        String kode_trx = txtKodeTrx.getText();
        Date tgl = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = dt.format(tgl);
        dB.insertTrx(kode_trx, tanggal, kar, subtot, jml_jenbar, tot_bayar, tot_kembali);
    }

    private void middle() {
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        int x = layar.width / 2 - jDialog1.getSize().width / 2;
        int y = layar.height / 2 - jDialog1.getSize().height / 2;
        jDialog1.setLocation(x, y);
    }

    void username(String user) {
        txtUser.setText(user);
    }

    public void showDate() {
        Date td = new Date();
        SimpleDateFormat t = new SimpleDateFormat("dd-MM-yyyy");
        txtDate.setText(t.format(td));
    }

    private void txtKosong() {
        txtKodeBrg.setText("");
        txtNamaBrg.setText("");
        txtQty.setText("");
        txtHargaSatuan.setText("");
    }

    public void showTableBarang() {
        try {
            dtm = new DefaultTableModel(new String[]{"KODE BARANG", "NAMA BARANG", "STOK", "HARGA SATUAN"}, 0);
            ResultSet rs;
            rs = dB.selectDB("data_brg");
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("kd_brg"),
                    rs.getString("nama_brg"), rs.getString("stok"),
                    rs.getString("hrg_jual")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbl_brg.setModel(dtm);
    }

    public void showTableDetail() {
        try {
            dtm = new DefaultTableModel(new String[]{"KODE BARANG", "NAMA BARANG", "QTY", "HARGA SATUAN", "HARGA TOTAL"}, 0);
            ResultSet rs;
            rs = dB.selectDB("detail_jual");
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("kd_brg"),
                    rs.getString("nama_brg"), rs.getString("qty"),
                    rs.getString("hrg_jual"), rs.getString("total_harga")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblTrx.setModel(dtm);
    }

    public void showCariBarang(String key) {
        try {
            dtm = new DefaultTableModel(new String[]{"KODE BARANG", "NAMA BARANG", "STOK", "HARGA BELI", "HARGA JUAL"}, 0);
            ResultSet rs;
            rs = dB.selectDB("data_brg where kd_brg like '%" + key + "%'");
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("kd_brg"),
                    rs.getString("nama_brg"), rs.getString("stok"),
                    rs.getString("hrg_jual"), rs.getString("hrg_beli")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbl_brg.setModel(dtm);
    }

    public void kodeTransaksi() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_atk_fc", "root", "");
            String sql = "Select kd_trx from transaksi ORDER by kd_trx desc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                String kode = rs.getString("kd_trx").substring(3);
                String auto = "" + (Integer.parseInt(kode) + 1);
                String zero = "";

                if (auto.length() == 1) {
                    zero = "000000";
                } else if (auto.length() == 2) {
                    zero = "00000";
                } else if (auto.length() == 3) {
                    zero = "0000";
                } else if (auto.length() == 4) {
                    zero = "000";
                } else if (auto.length() == 5) {
                    zero = "00";
                } else if (auto.length() == 6) {
                    zero = "0";
                } else if (auto.length() == 7) {
                    zero = "";
                }
                txtKodeTrx.setText("TRX" + zero + auto);

            } else {
                txtKodeTrx.setText("TRX0000001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    public void kodeDetailJual(String kd_detail) {
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_atk_fc", "root", "");
//            String sql = "Select kd_detjual from detail_jual";
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            if (rs.next()) {
//                String kode = rs.getString("kd_detjual").substring(3);
//                String auto = "" + (Integer.parseInt(kode) + 1);
//                String zero = "";
//
//                if (auto.length() == 1) {
//                    zero = "00000000";
//                } else if (auto.length() == 2) {
//                    zero = "0000000";
//                } else if (auto.length() == 3) {
//                    zero = "000000";
//                } else if (auto.length() == 4) {
//                    zero = "00000";
//                } else if (auto.length() == 5) {
//                    zero = "0000";
//                } else if (auto.length() == 6) {
//                    zero = "000";
//                } else if (auto.length() == 7) {
//                    zero = "00";
//                } else if (auto.length() == 8) {
//                    zero = "0";
//                } else if (auto.length() == 9) {
//                    zero = "";
//                }
//                kd_detail = "DTL" + zero + auto;
//
//            } else {
//                kd_detail = "DTL000000001";
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_brg = new javax.swing.JTable();
        txt_find = new javax.swing.JTextField();
        btn_refresh = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtKodeBrg = new javax.swing.JTextField();
        txtNamaBrg = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        txtHargaSatuan = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrx = new javax.swing.JTable();
        btnCek = new javax.swing.JButton();
        btnX = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtKodeTrx = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnClean = new javax.swing.JButton();
        txtGrandTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        txtKembali = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();

        jDialog1.setPreferredSize(new java.awt.Dimension(709, 572));
        jDialog1.setResizable(false);
        jDialog1.setSize(new java.awt.Dimension(709, 590));
        jDialog1.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                jDialog1WindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                jDialog1WindowOpened(evt);
            }
        });

        tbl_brg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_brg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_brgMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_brg);

        txt_find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_findActionPerformed(evt);
            }
        });
        txt_find.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_findKeyReleased(evt);
            }
        });

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        jLabel13.setText(" Cari Barang");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_find)))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_find, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btn_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TRANSAKSI");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Kode Barang");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Nama Barang");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Kuantitas");

        txtKodeBrg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtKodeBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeBrgActionPerformed(evt);
            }
        });

        txtNamaBrg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNamaBrg.setEnabled(false);

        txtQty.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtHargaSatuan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHargaSatuan.setEnabled(false);

        btnTambah.setBackground(new java.awt.Color(255, 255, 255));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/add.png"))); // NOI18N
        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUbah.setBackground(new java.awt.Color(255, 255, 255));
        btnUbah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/edit.png"))); // NOI18N
        btnUbah.setText("UBAH");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(255, 255, 255));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/trash.png"))); // NOI18N
        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Transaksi");

        tblTrx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "KODE", "NAMA BARANG", "JUMLAH", "HARGA", "HARGA TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTrx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTrxMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTrx);
        if (tblTrx.getColumnModel().getColumnCount() > 0) {
            tblTrx.getColumnModel().getColumn(0).setResizable(false);
            tblTrx.getColumnModel().getColumn(1).setResizable(false);
            tblTrx.getColumnModel().getColumn(2).setResizable(false);
            tblTrx.getColumnModel().getColumn(3).setResizable(false);
            tblTrx.getColumnModel().getColumn(4).setResizable(false);
        }

        btnCek.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCek.setText("CEK");
        btnCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekActionPerformed(evt);
            }
        });

        btnX.setBackground(new java.awt.Color(255, 255, 255));
        btnX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
        jLabel15.setText("TRANSAKSI");

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Kode Trx");

        txtKodeTrx.setEditable(false);
        txtKodeTrx.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtKodeTrx.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtKodeTrx.setEnabled(false);
        txtKodeTrx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeTrxActionPerformed(evt);
            }
        });

        txtUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUser.setEnabled(false);
        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        txtDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDate.setEnabled(false);
        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Harga Satuan");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/calendar.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/user (2).png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("CARI BARANG");

        btnClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/sweeping.png"))); // NOI18N
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        txtGrandTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("GRAND TOTAL");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("BAYAR");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("KEMBALI");

        txtBayar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBayarKeyPressed(evt);
            }
        });

        txtKembali.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtKembali.setEnabled(false);

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
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
                    .addComponent(txtNamaBrg)
                    .addComponent(txtQty)
                    .addComponent(txtHargaSatuan)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtKodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCek))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtKodeTrx, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClean)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(142, 142, 142))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBayar)
                            .addComponent(txtKembali)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnClean)
                            .addComponent(txtKodeTrx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCek))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnX)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnHapus)
                                    .addComponent(btnUbah)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_simpan)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(966, 576));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        String kode_trx, kode_brg, nama_brg, qty, hrg_jual, kd_dtl, tgl;
        int total = 0, hrg_beli = 0, laba = 0;
        kode_trx = txtKodeTrx.getText();
        kode_brg = txtKodeBrg.getText();
        nama_brg = txtNamaBrg.getText();
        qty = txtQty.getText();
        hrg_jual = txtHargaSatuan.getText();
        tgl = txtDate.getText();

        if (kode_brg.equals("")) {
            JOptionPane.showMessageDialog(null, "Kode barang tidak boleh kosong !");
        } else if (qty.equals("")) {
            JOptionPane.showMessageDialog(null, "Kuantitas tidak boleh kosong !");
        } else {
            try {
                ResultSet rs;
                rs = dB.selectDB("detail_jual where kd_brg = '" + kode_brg + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Data sudah ada !");
                } else {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_atk_fc", "root", "");
                        String sql = "Select kd_detjual from detail_jual ORDER by kd_detjual desc";
                        Statement st = conn.createStatement();
                        ResultSet rsl = st.executeQuery(sql);

                        if (rsl.next()) {
                            String kode = rsl.getString("kd_detjual").substring(3);
                            String auto = "" + (Integer.parseInt(kode) + 1);
                            String zero = "";

                            if (auto.length() == 1) {
                                zero = "00";
                            } else if (auto.length() == 2) {
                                zero = "0";
                            } else if (auto.length() == 3) {
                                zero = "";
                            }
                            kd_dtl = "DTL" + zero + auto;
                            total = Integer.parseInt(hrg_jual) * Integer.parseInt(qty);
                            dB.insertDetailJual(kd_dtl, kode_trx, kode_brg, nama_brg, qty, hrg_jual, total, hrg_beli, laba);
                            dB.insertHargaBeli(kode_brg);
                            dB.insertLaba(kode_brg);
                            txtGrandTotal.setText(dB.selectSum().toString());
                            ResultSet rslt;
                            rslt = dB.selectSum();
                            if (rslt.next()) {
                                txtGrandTotal.setText(rslt.getString("total"));
                            }
                            showTableDetail();
                        } else {
                            kd_dtl = "DTL001";
                            total = Integer.parseInt(hrg_jual) * Integer.parseInt(qty);
                            dB.insertDetailJual(kd_dtl, kode_trx, kode_brg, nama_brg, qty, hrg_jual, total, hrg_beli, laba);
                            dB.insertHargaBeli(kode_brg);
                            dB.insertLaba(kode_brg);
                            ResultSet rslt;
                            rslt = dB.selectSum();
                            if (rslt.next()) {
                                txtGrandTotal.setText(rslt.getString("total"));
                            }
                            showTableDetail();
                        }
                        txtKosong();
                    } catch (SQLException ex) {
                        Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnTambahActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        showDate();
    }//GEN-LAST:event_txtDateActionPerformed

    private void txtKodeTrxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeTrxActionPerformed
//        autoNumberTRX();
    }//GEN-LAST:event_txtKodeTrxActionPerformed

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
        String user = txtUser.getText().toUpperCase();
        String kode = txtKodeTrx.getText();
        int conf = JOptionPane.showOptionDialog(this, "Apakah ingin membatalkan transaksi ?", "Tutup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (conf == JOptionPane.YES_OPTION) {
            FormWellcome fw = new FormWellcome();
            dB.deleteTrx(kode);
            dB.deleteDetailTrx(kode);
            fw.setVisible(true);
            fw.username(user);
            dispose();
            jDialog1.dispose();
        }
    }//GEN-LAST:event_btnXActionPerformed

    private void txtKodeBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeBrgActionPerformed

    }//GEN-LAST:event_txtKodeBrgActionPerformed

    private void btnCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekActionPerformed
        String kode;
        kode = txtKodeBrg.getText();
        if (kode.equals("")) {
            jDialog1.setVisible(true);
        } else {
            try {
                ResultSet rs;
                rs = dB.selectDB("data_brg where kd_brg = '" + kode + "'");
                if (rs.next()) {
                    txtNamaBrg.setText(rs.getString("nama_brg"));
                    txtHargaSatuan.setText(rs.getString("hrg_jual"));
                } else {
                    JOptionPane.showMessageDialog(null, "Data Tidak Ada !");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "GAGAL");
            }
        }


    }//GEN-LAST:event_btnCekActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        txtKosong();
        txtKodeBrg.setEnabled(true);
    }//GEN-LAST:event_btnCleanActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtBayarActionPerformed

    private void txt_findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_findActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_findActionPerformed

    private void jDialog1WindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog1WindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_jDialog1WindowActivated

    private void jDialog1WindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog1WindowOpened
        // TODO add your handling code here:
        showTableBarang();
    }//GEN-LAST:event_jDialog1WindowOpened

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        showTableBarang();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void txt_findKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_findKeyReleased
        // TODO add your handling code here:
        String key = txt_find.getText();
        System.out.println(key);
        if (key != "") {
            showCariBarang(key);
        } else {
            showTableBarang();
        }

    }//GEN-LAST:event_txt_findKeyReleased

    private void tbl_brgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_brgMouseClicked
        // TODO add your handling code here:
        int row = tbl_brg.getSelectedRow();
        txtKodeBrg.setText(tbl_brg.getValueAt(row, 0).toString());
        txtNamaBrg.setText(tbl_brg.getValueAt(row, 1).toString());
        txtHargaSatuan.setText(tbl_brg.getValueAt(row, 3).toString());
        txtKodeBrg.setEnabled(false);
        jDialog1.dispose();
    }//GEN-LAST:event_tbl_brgMouseClicked

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String total = txtGrandTotal.getText();
        int jml = tblTrx.getRowCount();
        String bayar = txtBayar.getText();
        String kembali = txtKembali.getText();
        String kode = txtKodeTrx.getText();
        if (bayar.equals("")) {
            JOptionPane.showMessageDialog(null, "Harap lakukan pembayaran terelebih dahulu !");
        } else {
            int conf = JOptionPane.showOptionDialog(this, "Apakah ingin membatalkan transaksi ?", "Tutup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (conf == JOptionPane.YES_OPTION) {
                dB.insertDetTrans();
                dB.updateTrans(total, jml, bayar, kembali, "where kd_trx = '" + kode + "'");
                dB.deleteDetailTrx(kode);
                JOptionPane.showMessageDialog(null, "Data transaksi berhasil disimpan !");
                showTableDetail();
                kodeTransaksi();
                int subtot = 0, jml_jenbar = 0, tot_bayar = 0, tot_kembali = 0;
                String kar = "KAR001";
                Date tgl = new Date();
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                String tanggal = dt.format(tgl);
                String kodeTrx = txtKodeTrx.getText();
                dB.insertTrx(kodeTrx, tanggal, kar, subtot, jml_jenbar, tot_bayar, tot_kembali);
                txtKosong();
            }
        }

    }//GEN-LAST:event_btn_simpanActionPerformed

    private void tblTrxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTrxMouseClicked
        // TODO add your handling code here:
        int row = tblTrx.getSelectedRow();
        txtKodeBrg.setText(tblTrx.getValueAt(row, 0).toString());
        txtNamaBrg.setText(tblTrx.getValueAt(row, 1).toString());
        txtQty.setText(tblTrx.getValueAt(row, 2).toString());
        txtHargaSatuan.setText(tblTrx.getValueAt(row, 3).toString());
        txtKodeBrg.setEnabled(false);
    }//GEN-LAST:event_tblTrxMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        String kode_brg = txtKodeBrg.getText();
        int conf = JOptionPane.showOptionDialog(this, "Apakah ingin membatalkan transaksi ?", "Tutup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (conf == JOptionPane.YES_OPTION) {
            try {
                dB.deleteDetailBrg(kode_brg);
                showTableDetail();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus !");
                ResultSet rslt;
                rslt = dB.selectSum();
                if (rslt.next()) {
                    txtGrandTotal.setText(rslt.getString("total"));
                }
                txtKodeBrg.setEnabled(true);
                txtKosong();
            } catch (SQLException ex) {
                Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        String qty = txtQty.getText();
        String kode_brg = txtKodeBrg.getText();
        String hrg_jual = txtHargaSatuan.getText();
        int total = Integer.parseInt(hrg_jual) * Integer.parseInt(qty);
        if (kode_brg.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih data barang terlebih dahulu !");
        } else if (qty.equals("")) {
            JOptionPane.showMessageDialog(null, "Kuantitas tidak boleh kosong !");
        } else {
            int conf = JOptionPane.showOptionDialog(this, "Apakah ingin mengubah jumlah barang tersebut ?", "Tutup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (conf == JOptionPane.YES_OPTION) {
                try {
                    dB.updateDetail(qty, "where kd_brg = '" + kode_brg + "'", total);
                    ResultSet rslt;
                    rslt = dB.selectSum();
                    if (rslt.next()) {
                        txtGrandTotal.setText(rslt.getString("total"));
                    }
                    showTableDetail();
                    txtKodeBrg.setEnabled(true);
                    txtKosong();
                } catch (SQLException ex) {
                    Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void txtBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyPressed
        // TODO add your handling code here:
        String grandTotal = txtGrandTotal.getText();
        String bayar = txtBayar.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (grandTotal.equals("")) {
                JOptionPane.showMessageDialog(null, "Grand total masih kosong, harap lakukan transaksi  !");
            } else if (Integer.parseInt(bayar) < Integer.parseInt(grandTotal)) {
                JOptionPane.showMessageDialog(null, "Uang kurang !");
            } else {
                int kembali = Integer.parseInt(bayar) - Integer.parseInt(grandTotal);
                txtKembali.setText(String.valueOf(kembali));
            }

        }
    }//GEN-LAST:event_txtBayarKeyPressed

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
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTransaksi().setVisible(true);
            }
        });
    }

    KoneksiDB dB;
    DefaultTableModel dtm;
    Statement st;
    Connection con;
    ResultSet rs;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCek;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton btnX;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTrx;
    private javax.swing.JTable tbl_brg;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtGrandTotal;
    private javax.swing.JTextField txtHargaSatuan;
    private javax.swing.JTextField txtKembali;
    private javax.swing.JTextField txtKodeBrg;
    private javax.swing.JTextField txtKodeTrx;
    private javax.swing.JTextField txtNamaBrg;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txt_find;
    // End of variables declaration//GEN-END:variables
}
