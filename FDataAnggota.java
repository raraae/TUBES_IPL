/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VR46
 */
public class FDataAnggota extends javax.swing.JFrame {

    /**
     * Creates new form FDataAnggota
     */
    
    
    public FDataAnggota() 
    {
        initComponents();
        //maximize
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        load_data();
        IDOtomatis();
        LoadTingkat();
        LoadKelas();
        LoadJurusan();
        
         //Mendisable kan tombol 
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
        
    }
    
     //load data Produk dari Database
    private void load_data()
    {
        Connection kon = Koneksi.koneksiDb();
        Object header[]={"ID ANGGOTA","NIS","NAMA ANGGOTA","JK","TINGKAT","JURUSAN","KELAS","NO HP","STATUS"};
        DefaultTableModel data= new DefaultTableModel(null,header);
        TabelAnggota.setModel(data);
        String sql="SELECT * FROM tbl_anggota";
        try
        {
            Statement st = kon.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {

                String k1=rs.getString(1);
                String k2=rs.getString(2);
                String k3=rs.getString(3);
                String k4=rs.getString(4);
                String k5=rs.getString(5);
                String k6=rs.getString(6);
                String k7=rs.getString(7);
                String k8=rs.getString(8);
                String k9=rs.getString(9);
                          
                String k[]={k1,k2,k3,k4,k5,k6,k7,k8,k9};
                data.addRow(k);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    //CARI DATA ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void cari()
    {
        
          Connection kon = Koneksi.koneksiDb();
            Object header[]={"ID ANGGOTA","NIS","NAMA ANGGOTA","JK","TINGKAT","JURUSAN","KELAS","NO HP","STATUS"};
            DefaultTableModel data= new DefaultTableModel(null,header);
            TabelAnggota.setModel(data);
            String sql="SELECT * FROM tbl_anggota WHERE id_anggota LIKE '%"+TCARI.getText()
                    +"' OR nis LIKE '%"+TCARI.getText()
                    +"%' OR nama_anggota LIKE '%"+TCARI.getText()
                    +"' OR jk LIKE '"+TCARI.getText()
                    +"' OR id_tingkat LIKE '"+TCARI.getText()
                    +"' OR kd_jurusan LIKE '"+TCARI.getText()
                    +"' OR id_kelas LIKE '"+TCARI.getText()
                    +"' OR no_hp LIKE '"+TCARI.getText()
                    +"' OR status LIKE '"+TCARI.getText()+"' ORDER BY id_anggota";
            try
            {
                Statement st = kon.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next())
                {

                    String k1=rs.getString(1);
                    String k2=rs.getString(2);
                    String k3=rs.getString(3);
                    String k4=rs.getString(4);
                    String k5=rs.getString(5);
                    String k6=rs.getString(6);
                    String k7=rs.getString(7);
                    String k8=rs.getString(8);
                    String k9=rs.getString(9);

                    String k[]={k1,k2,k3,k4,k5,k6,k7,k8,k9};
                    data.addRow(k);
                }
            }
            catch(SQLException ex)
            {
                 JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

            }
        
    }
    
    
    //Load Combo TINGKAT -------------------------------------------------------------------------------------------------------------------------------------------------- 
    public void LoadTingkat()
     {
        try {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql = "SELECT * FROM tbl_tingkat";
            ResultSet rs = st.executeQuery(sql);
                while (rs.next()) 
                {                
                    KTINGKAT.addItem(rs.getString("id_tingkat"));
                }
                rs.close();
            } 
        catch (SQLException e) 
            {
            }
    }
    
    //load nama tingkat ketika di pilih
    public void NTingkat()
    {
        try
            {
                Connection kon = Koneksi.koneksiDb();
                Statement st = kon.createStatement();
                String sqltingkat = "SELECT tingkat FROM tbl_tingkat WHERE id_tingkat='"+KTINGKAT.getSelectedItem()+"'";
                ResultSet rs=st.executeQuery(sqltingkat);
                while(rs.next())
                {
                    NTINGKAT.setText(rs.getString("tingkat"));
                }
            }
        catch(SQLException e)
        {
            
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JK = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        NIS = new javax.swing.JTextField();
        NAMA = new javax.swing.JTextField();
        KTINGKAT = new javax.swing.JComboBox();
        KJURUSAN = new javax.swing.JComboBox();
        KKELAS = new javax.swing.JComboBox();
        NOPE = new javax.swing.JTextField();
        STATUS = new javax.swing.JComboBox();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        NTINGKAT = new javax.swing.JTextField();
        NJURUSAN = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setText("DATA ANGGOTA PERPUSTAKAAN");

        jLabel2.setText("ID ANGGOTA");

        jLabel3.setText("NIM");

        jLabel4.setText("NAMA ");

        jLabel7.setText("TINGKAT");

        jLabel8.setText("JURUSAN");

        jLabel9.setText("KELAS");

        jLabel10.setText("NO HP");

        jLabel11.setText("STATUS");

        ID.setEnabled(false);

        NIS.setToolTipText("Nomor Induk Siswa");

        NAMA.setToolTipText("Nama Anggota");

        KTINGKAT.setToolTipText("");
        KTINGKAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KTINGKATActionPerformed(evt);
            }
        });

        KJURUSAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KJURUSANActionPerformed(evt);
            }
        });

        NOPE.setToolTipText("Nomor Handphone");

        STATUS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AKTIF", "TIDAK AKTIF" }));
        STATUS.setToolTipText("Status Keanggotaan");

        BInput.setText("INPUT");
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });

        BEdit.setText("EDIT");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BDelete.setText("DELETE");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        NTINGKAT.setEnabled(false);

        NJURUSAN.setEnabled(false);

        jButton1.setText("CLOSE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(43, 43, 43))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))))
                    .addComponent(jLabel9))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(BInput)
                        .addGap(18, 18, 18)
                        .addComponent(BEdit)
                        .addGap(18, 18, 18)
                        .addComponent(BDelete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(KJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(KKELAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(KTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(NIS, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NOPE, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(225, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(KTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KKELAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NOPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(BInput)
                    .addComponent(BEdit)
                    .addComponent(BDelete))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(869, 476));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed

    }//GEN-LAST:event_BDeleteActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed

    }//GEN-LAST:event_BEditActionPerformed

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed

    }//GEN-LAST:event_BInputActionPerformed

    private void KJURUSANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KJURUSANActionPerformed

    }//GEN-LAST:event_KJURUSANActionPerformed

    private void KTINGKATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KTINGKATActionPerformed

    }//GEN-LAST:event_KTINGKATActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.JTextField ID;
    private javax.swing.ButtonGroup JK;
    private javax.swing.JComboBox KJURUSAN;
    private javax.swing.JComboBox KKELAS;
    private javax.swing.JComboBox KTINGKAT;
    private javax.swing.JTextField NAMA;
    private javax.swing.JTextField NIS;
    private javax.swing.JTextField NJURUSAN;
    private javax.swing.JTextField NOPE;
    private javax.swing.JTextField NTINGKAT;
    private javax.swing.JComboBox STATUS;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
