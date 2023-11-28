package tubes;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FDataBuku extends javax.swing.JFrame {
    
    public FDataBuku() 
    {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        load_data();
        KODEOtomatis();

        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
        
    }
        private void load_data()
    {
        Connection kon = Koneksi.koneksiDb();
        Object header[]={"KODE BUKU","JUDUL BUKU","NAMA PENGARANG","NAMA PENERBIT","JUMLAH"};
        DefaultTableModel data= new DefaultTableModel(null,header);
        TabelBuku.setModel(data);
        String sql="SELECT * FROM tbl_buku";
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

                          
                String k[]={k1,k2,k3,k4,k5};
                data.addRow(k);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }     
    }
    //CARI DATA ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void KODEOtomatis()
    {
        try
        {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            
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
        try
        {      
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            

            
            String sql="INSERT INTO tbl_buku values('"+KODE.getText()
                    +"','"+JUDUL.getText()
                    +"','"+PENGARANG.getText()
                    +"','"+PENERBIT.getText()
                    +"','"+JUMLAH.getText()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Buku Berhasil Ditambahkan");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //dEFAULT cLEAR ------------------------------------------------------------------------------------------------------------------------------------------------------
    public void clear()
    {
        JUDUL.setText("");
        PENGARANG.setText("");
        PENERBIT.setText("");
        JUMLAH.setText("");
    }
    
    //Update --------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void update()
    {
        try
        {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            

            
            String sql_update="UPDATE tbl_buku SET judul_buku='"+JUDUL.getText()
                    +"',nama_pengarang='"+PENGARANG.getText()
                    +"',nama_penerbit='"+PENERBIT.getText()
                    +"',jumlah_buku='"+JUMLAH.getText()
                    +"'WHERE kode_buku='"+KODE.getText()+"'";
            st.executeUpdate(sql_update);
            JOptionPane.showMessageDialog(null, "Data berhasil Di Update");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void delete()
    {
        try
        {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql_delete="DELETE FROM tbl_buku WHERE kode_buku='"+KODE.getText()+"'";
            
            st.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data Buku berhasil Di Hapus");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
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
        KODE = new javax.swing.JTextField();
        JUDUL = new javax.swing.JTextField();
        PENGARANG = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelBuku = new javax.swing.JTable();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        JUMLAH = new javax.swing.JTextField();
        PENERBIT = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setText("DATA BUKU PERPUSTAKAAN");

        jLabel2.setText("NO BUKU");

        jLabel3.setText("JUDUL");

        jLabel4.setText("NAMA PENGARANG");

        jLabel5.setText("NAMA PENERBIT");

        jLabel7.setText("JUMLAH");

        KODE.setEnabled(false);

        JUDUL.setToolTipText("Judul");

        PENGARANG.setToolTipText("Nama Pengarang");

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

        BInput.setText("MASUK");
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

        BDelete.setText("HAPUS");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        jButton1.setText("TUTUP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        JUMLAH.setToolTipText("Jumlah");

        PENERBIT.setToolTipText("Nama Penerbit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JUDUL, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KODE, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PENGARANG, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PENERBIT, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JUMLAH, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BInput)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BEdit)
                                .addGap(18, 18, 18)
                                .addComponent(BDelete))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(KODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(JUDUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(PENGARANG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PENERBIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JUMLAH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BInput)
                            .addComponent(BEdit)
                            .addComponent(BDelete)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        setSize(new java.awt.Dimension(882, 512));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed

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
        String e = TabelBuku.getValueAt(bar, 4).toString();

        KODE.setText(a);
        JUDUL.setText(b);
        PENGARANG.setText(c);
        PENERBIT.setText(d);
        JUMLAH.setText(e);
        
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
                        "Keluar dari Kelola Data Anggota?", 
                        "Keluar", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if(jawab == JOptionPane.YES_OPTION)
        {
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
    private javax.swing.JTextField JUMLAH;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
