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
        
         //Mendisable kan tombol 
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
        
    }
    
     //load data Produk dari Database
    private void load_data()
    {
        Connection kon = Koneksi.koneksiDb();
        Object header[]={"ID ANGGOTA","NIM","NAMA ANGGOTA","ALAMAT","NO HP","STATUS"};
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

                String k[]={k1,k2,k3,k4,k5,k6};
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
            Object header[]={"ID ANGGOTA","NIM","NAMA ANGGOTA","ALAMAT","NO HP","STATUS"};
            DefaultTableModel data= new DefaultTableModel(null,header);
            TabelAnggota.setModel(data);
            String sql="SELECT * FROM tbl_anggota WHERE id_anggota LIKE '%"+TCARI.getText()
                    +"' OR nis LIKE '%"+TCARI.getText()
                    +"%' OR nama_anggota LIKE '%"+TCARI.getText()
                    +"' OR id_alamat LIKE '"+TCARI.getText()
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


                    String k[]={k1,k2,k3,k4,k5,k6};
                    data.addRow(k);
                }
            }
            catch(SQLException ex)
            {
                 JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

            }
        
    }
    
    private void IDOtomatis()
    {
        try
        {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            
            String sql="SELECT * FROM tbl_anggota order by id_anggota desc";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
            {
                    String id_anggota=rs.getString("id_anggota").substring(1);
                    String AN = "" + (Integer.parseInt(id_anggota) + 1);
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
                    ID.setText("A" + Nol + AN);
            }
                else
                {
                    ID.setText("A00001");
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
        try
        {      
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            
            String s="";
            if(JKP.isSelected())
            {
                s=JKP.getText();
            }
            else
            {
                s=JKW.getText();
            }
            
            String sql="INSERT INTO tbl_anggota values('"+ID.getText()
                    +"','"+NIM.getText()
                    +"','"+NAMA.getText()
                    +"','"+s
                    +"','"+ALAMAT.getText()
                    +"','"+NOPE.getText()
                    +"','"+STATUS.getSelectedItem()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Ditambahkan");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //dEFAULT cLEAR ------------------------------------------------------------------------------------------------------------------------------------------------------
    public void clear()
    {
        NIS.setText("");
        NAMA.setText("");
        ALAMAT.setText("");
        NOPE.setText("");
        STATUS.setSelectedItem("AKTIF");
    }
    
    //Update --------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void update()
    {
        try
        {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            
            String s="";
            if(JKP.isSelected())
            {
                s=JKP.getText();
            }
            else
            {
                s=JKW.getText();
            }
            
            String sql_update="UPDATE tbl_anggota SET nis='"+NIM.getText()
                    +"',nama_anggota='"+NAMA.getText()
                    +"',alamat='"+ALAMAT.getText()
                    +"',no_hp='"+NOPE.getText()
                    +"',status='"+STATUS.getSelectedItem()
                    +"'WHERE id_anggota='"+ID.getText()+"'";
            st.executeUpdate(sql_update);
            JOptionPane.showMessageDialog(null, "Data berhasil Di Update");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    //Delete --------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void delete()
    {
        try
        {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql_delete="DELETE FROM tbl_anggota WHERE id_anggota='"+ID.getText()+"'";
            
            st.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data Anggota Perpustakaan berhasil Di Hapus");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JK = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NIM = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ALAMAT = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        NIS = new javax.swing.JTextField();
        NAMA = new javax.swing.JTextField();
        NOPE = new javax.swing.JTextField();
        STATUS = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelAnggota = new javax.swing.JTable();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        TCARI = new javax.swing.JTextField();
        BCARI = new javax.swing.JButton();
        NOHP = new javax.swing.JLabel();
        NOPE1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setText("DATA ANGGOTA PERPUSTAKAAN");

        jLabel2.setText("ID ANGGOTA");

        NIM.setText("NIM");

        jLabel4.setText("NAMA ANGGOTA");

        ALAMAT.setText("ALAMAT");

        jLabel11.setText("STATUS");

        ID.setEnabled(false);

        NIS.setToolTipText("Nomor Induk Siswa");

        NAMA.setToolTipText("Nama Anggota");

        NOPE.setToolTipText("Nomor Handphone");

        STATUS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AKTIF", "TIDAK AKTIF" }));
        STATUS.setToolTipText("Status Keanggotaan");

        TabelAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID ANGGOTA", "NIM", "NAMA ANGGOTA", "ALAMAT", "NO HP", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelAnggota);
        if (TabelAnggota.getColumnModel().getColumnCount() > 0) {
            TabelAnggota.getColumnModel().getColumn(0).setResizable(false);
            TabelAnggota.getColumnModel().getColumn(1).setResizable(false);
            TabelAnggota.getColumnModel().getColumn(2).setResizable(false);
            TabelAnggota.getColumnModel().getColumn(3).setResizable(false);
            TabelAnggota.getColumnModel().getColumn(4).setResizable(false);
            TabelAnggota.getColumnModel().getColumn(5).setResizable(false);
        }

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

        jButton1.setText("CLOSE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        BCARI.setText("CARI");
        BCARI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCARIActionPerformed(evt);
            }
        });

        NOHP.setText("NO HP");

        NOPE1.setToolTipText("Nomor Handphone");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(NIM)
                            .addComponent(jLabel4)
                            .addComponent(ALAMAT)
                            .addComponent(jLabel11)
                            .addComponent(NOHP)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NIS, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NOPE, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BInput)
                        .addGap(18, 18, 18)
                        .addComponent(BEdit)
                        .addGap(28, 28, 28)
                        .addComponent(BDelete))
                    .addComponent(NOPE1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TCARI, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BCARI)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(562, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TCARI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BCARI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NIM))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NOPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ALAMAT))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NOPE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NOHP))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(99, 99, 99)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BInput)
                            .addComponent(BEdit)
                            .addComponent(BDelete)
                            .addComponent(jButton1))))
                .addGap(19, 19, 19))
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
                .addGap(0, 18, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1333, 512));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed
        // TODO add your handling code here:
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
        IDOtomatis();
        clear();
        }
    }//GEN-LAST:event_BInputActionPerformed

    private void TabelAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelAnggotaMouseClicked
        // TODO add your handling code here:
        int bar=TabelAnggota.getSelectedRow();
        String a = TabelAnggota.getValueAt(bar, 0).toString();
        String b = TabelAnggota.getValueAt(bar, 1).toString();
        String c = TabelAnggota.getValueAt(bar, 2).toString();
        String d = TabelAnggota.getValueAt(bar, 3).toString();
        String e = TabelAnggota.getValueAt(bar, 4).toString();
        String f = TabelAnggota.getValueAt(bar, 5).toString();
        String g = TabelAnggota.getValueAt(bar, 6).toString();

        
        ID.setText(a);
        NIM.setText(b);
        NAMA.setText(c);
        ALAMAT.setText(d);
        NOPE.setText(e);
        //kode produk combo
       
        //set disable button
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
    }//GEN-LAST:event_TabelAnggotaMouseClicked

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

            IDOtomatis();
        }
        
        
    }//GEN-LAST:event_BDeleteActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        //akan edit?
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
            IDOtomatis();
        }
        
    }//GEN-LAST:event_BEditActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int jawab = JOptionPane.showOptionDialog(this, 
                        "Keluar dari Kelola Data Anggota?", 
                        "Keluar", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if(jawab == JOptionPane.YES_OPTION)
        {
            new FUtamaPustakawan().show();
            this.dispose();
        }

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BCARIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCARIActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_BCARIActionPerformed

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
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ALAMAT;
    private javax.swing.JButton BCARI;
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.JTextField ID;
    private javax.swing.ButtonGroup JK;
    private javax.swing.JTextField NAMA;
    private javax.swing.JLabel NIM;
    private javax.swing.JTextField NIS;
    private javax.swing.JLabel NOHP;
    private javax.swing.JTextField NOPE;
    private javax.swing.JTextField NOPE1;
    private javax.swing.JComboBox STATUS;
    private javax.swing.JTextField TCARI;
    private javax.swing.JTable TabelAnggota;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
