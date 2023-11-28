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
    
// Variables declaration - do not modify                     
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
    // End of variables declaration                   
}