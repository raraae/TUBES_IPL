/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubesperpustakaan;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;


public class FDataBuku extends javax.swing.JFrame {
    
    public FDataBuku() 
    {
        initComponents();
        
        load_data();
        KODEOtomatis();

        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
        //menginisialisasi antarmuka pengguna, memuat data awal,
        //menghasilkan atau mengisi kode otomatis, dan mengaktifkan tombol edit dan delete. 
    }
        private void load_data()
    {
        Connection cn = Koneksi.koneksi.getkoneksi();//Mendapatkan koneksi ke database
        Object header[]={"KODE BUKU","JUDUL BUKU","NAMA PENGARANG","NAMA PENERBIT"};
        DefaultTableModel data= new DefaultTableModel(null,header);// Membuat model tabel dengan header
        TabelBuku.setModel(data);// Mengatur model tabel
        String sql="SELECT * FROM tbl_buku";// Query SQL untuk mengambil semua data dari tabel_buku
        try
        {
            Statement st = cn.createStatement();//membuat statement query
            ResultSet rs = st.executeQuery(sql);//mengekskusi dan mendapatkan hasil query
            while(rs.next())
            {

                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);

                          
                String k[]={k1,k2,k3,k4};
                data.addRow(k);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);//// Menampilkan pesan kesalahan jika terjadi exception
        }
    }
   
    private void KODEOtomatis()
    //Mengeksekusi query kode buku
    {
        try
        {
            Connection cn = Koneksi.koneksi.getkoneksi();
            Statement st = cn.createStatement();
            
            String sql="SELECT * FROM tbl_buku order by kode_buku desc";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
            {
                    String kode_buku=rs.getString("kode_buku").substring(1);
                    String AN = "" + (Integer.parseInt(kode_buku) + 1);
                    String Nol="";
                    if(AN.length()==1)
                    {
                        Nol="0000";
                    }
                    else if(AN.length()==2)
                    {
                        Nol="000";
                    }
                    else if(AN.length()==3)
                    {
                        Nol="00";
                    }
                    KODE.setText("B" + Nol + AN);
            }
                else
                {
                    KODE.setText("B00001");
                }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }   
    //Input Data -----------------------------------------------------------------------------------------------------------------------------------------------
    private void input_data()
    {
        try {      
    Connection cn = Koneksi.koneksi.getkoneksi();
    
    // Use a prepared statement to avoid SQL injection
    String sql = "INSERT INTO tbl_buku VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = cn.prepareStatement(sql)) {
        preparedStatement.setString(1, KODE.getText());
        preparedStatement.setString(2, JUDUL.getText());
        preparedStatement.setString(3, PENGARANG.getText());
        preparedStatement.setString(4, PENERBIT.getText());
//        preparedStatement.setString(5, JUMLAH.getText());

        preparedStatement.execute();
    }
    
    JOptionPane.showMessageDialog(null, "Data Buku Berhasil Ditambahkan");
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, e.getMessage());
}
    }

    public void clear()
    {
        JUDUL.setText("");
        PENGARANG.setText("");
        PENERBIT.setText("");
//        JUMLAH.setText("");
    }
   
    //Update --------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void update()
    {
        try
        {
            Connection cn = Koneksi.koneksi.getkoneksi();
            Statement st = cn.createStatement();
            

            
            String sql_update="UPDATE tbl_buku SET judul_buku='"+JUDUL.getText()
                    +"',nama_pengarang='"+PENGARANG.getText()
                    +"',nama_penerbit='"+PENERBIT.getText()
//                    +"',jumlah_buku='"+JUMLAH.getText()
                    +"'WHERE kode_buku='"+KODE.getText()+"'";
            st.executeUpdate(sql_update);
            JOptionPane.showMessageDialog(null, "Data berhasil Di Update");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // delete
    private void delete()
    {
        try
        {
            Connection cn = Koneksi.koneksi.getkoneksi();
            Statement st = cn.createStatement();
            String sql_delete="DELETE FROM tbl_buku WHERE kode_buku='"+KODE.getText()+"'";
            
            st.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data Buku berhasil Di Hapus");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JK = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        KODE = new javax.swing.JTextField();
        JUDUL = new javax.swing.JTextField();
        PENGARANG = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelBuku = new javax.swing.JTable();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        PENERBIT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setText("DATA BUKU");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 404, -1));

        jLabel2.setText("KODE BUKU");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 68, -1, -1));

        jLabel3.setText("JUDUL");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 98, -1, -1));

        jLabel4.setText("NAMA PENGARANG");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 132, -1, -1));

        jLabel5.setText("NAMA PENERBIT");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 157, -1, -1));

        KODE.setEnabled(false);
        jPanel1.add(KODE, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 65, 83, -1));

        JUDUL.setToolTipText("Judul");
        jPanel1.add(JUDUL, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 95, 142, -1));

        PENGARANG.setToolTipText("Nama Pengarang");
        jPanel1.add(PENGARANG, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 129, 261, -1));

        TabelBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "JUDUL", "NAMA PENGARANG", "NAMA PENERBIT", "JUMLAH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelBuku);
        if (TabelBuku.getColumnModel().getColumnCount() > 0) {
            TabelBuku.getColumnModel().getColumn(0).setResizable(false);
            TabelBuku.getColumnModel().getColumn(1).setResizable(false);
            TabelBuku.getColumnModel().getColumn(2).setResizable(false);
            TabelBuku.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 660, 200));

        BInput.setText("INPUT");
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });
        jPanel1.add(BInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 219, -1, -1));

        BEdit.setText("EDIT");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });
        jPanel1.add(BEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 219, -1, -1));

        BDelete.setText("DELETE");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(BDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 219, -1, -1));

        jButton1.setText("KEMBALI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, -1, -1));

        PENERBIT.setToolTipText("Nama Penerbit");
        jPanel1.add(PENERBIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 157, 142, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg.jpg"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 870, 520));

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

        setSize(new java.awt.Dimension(749, 512));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed
        //KOnfirmasi input
        int simpan = JOptionPane.showOptionDialog(this, 
                        "Apakah Data Sudah Benar? SIMPAN?", 
                        "Simpan Data", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if(simpan == JOptionPane.YES_OPTION)
        {
        input_data();
        load_data();
        KODEOtomatis();
        clear();
        }
    }//GEN-LAST:event_BInputActionPerformed

    private void TabelBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelBukuMouseClicked

        int bar=TabelBuku.getSelectedRow();
        String a = TabelBuku.getValueAt(bar, 0).toString();
        String b = TabelBuku.getValueAt(bar, 1).toString();
        String c = TabelBuku.getValueAt(bar, 2).toString();
        String d = TabelBuku.getValueAt(bar, 3).toString();
//        String e = TabelBuku.getValueAt(bar, 4).toString();


        
        KODE.setText(a);
        JUDUL.setText(b);
        PENGARANG.setText(c);
        PENERBIT.setText(d);
//        JUMLAH.setText(e);
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
    }//GEN-LAST:event_TabelBukuMouseClicked

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
        //KOnfirmasi Hapus
        int hapus = JOptionPane.showOptionDialog(this, 
                        "Hapus Data Anggota?", 
                        "Hapus Data", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if(hapus == JOptionPane.YES_OPTION)
        {
            delete();
            load_data();
            clear();

            BInput.setEnabled(true);

            KODEOtomatis();
        }
        
        
    }//GEN-LAST:event_BDeleteActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed

        int edit = JOptionPane.showOptionDialog(this, 
                        "Data Akan di EDIT?", 
                        "Edit", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if(edit == JOptionPane.YES_OPTION)
        {
            update();
            load_data();
            clear();
            BInput.setEnabled(true);

            KODEOtomatis();
        }
        
    }//GEN-LAST:event_BEditActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int jawab = JOptionPane.showOptionDialog(this, 
                        "Keluar dari Data Buku?", 
                        "Keluar", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if(jawab == JOptionPane.YES_OPTION)
        {
            new MenuAdmin().show();
            this.dispose();
        }      
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.ButtonGroup JK;
    private javax.swing.JTextField JUDUL;
    private javax.swing.JTextField KODE;
    private javax.swing.JTextField PENERBIT;
    private javax.swing.JTextField PENGARANG;
    private javax.swing.JTable TabelBuku;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
