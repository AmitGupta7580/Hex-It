/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HexIt;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;
/**
 *
 * @author acer
 */
public final class FileJPlane extends javax.swing.JFrame {

    /**
     * Creates new form FileJPlane
     */
    
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private String Path;
    
    public FileJPlane(String Path) {
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.Path = Path;
        initComponents();
    }
    public javax.swing.JPanel getPanel(){
        return jPanel1;
    }
    public void initComponents(){
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        String[][] _content = new String[100000][];
        Table tb = new Table();
        int sz=0;
        try {
            File f = new File(Path);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            byte[] chunk = null;
            int readStatus = 0;
            while (true) {
                String[] con = new String[18];
                String pad = "";
                System.out.println(Integer.toHexString(sz).toUpperCase().length());
                for(int i=0 ; i < 7 -Integer.toHexString(sz).toUpperCase().length();i++) {
                    pad += "0";
                }
                con[0] = pad+Integer.toHexString(sz).toUpperCase()+"0";
                chunk = new byte[16];
                readStatus = bis.read(chunk, 0, 16);
                char[] line = new char[16];

                if (readStatus == -1)
                    break;
                
                for (byte i=0; i < readStatus; i++) {
                    int readByte = (chunk[i] < 0) ? (-1 * (int) chunk[i]) : chunk[i];
                    String paddingZero = (readByte < 16) ? "0" : "";
                    con[i+1] = paddingZero + Integer.toHexString(readByte).toUpperCase();
                    line[i] = (readByte >= 33 && readByte <= 126) ? (char) readByte : (char) 46;
                    
                }
                for (int i=readStatus; i<16 ; i++) {
                    line[i] = (char) 46;
                }
                con[17] = new String(line);
                _content[sz] = con;
                sz++;
                
            }
        } catch (Exception e1) { e1.printStackTrace(); }
//        String[][] ab = new String[100][];
//        String[] a = new String[18];
//        a[0] = "00000000";
//        for(int i=1;i<=16;i++){
//            a[i] = "AA";
//        }
//        a[17] = "SOMERA.....EXT_+";
//        ab[0] = a;
//        String[] b = new String[18];
//        b[0] = "00000010";
//        for(int i=1;i<=16;i++){
//            b[i] = "DD";
//        }
//        b[17] = "SOMERANDOMTEXT_+";
//        ab[1] = b;
        String[][] _contentorg = new String[sz][];
        for(int i=0 ;i<sz; i++){
            _contentorg[i] = _content[i];
        }
        
        tb.setFont(new java.awt.Font("Courier New", 0, 14));
        tb.setModel(new javax.swing.table.DefaultTableModel(
            _contentorg,
            new String [] {
                "", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "ASCII"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb.setRowHeight(20);
        tb.setShowHorizontalLines(false);
        tb.setShowVerticalLines(false);
        int[] wid = new int[18];
        wid[0] = 70;
        for(int i= 1;i < wid.length-1 ; i++){
            wid[i] = 5;
        }
        wid[17] = 200;
        tb.setColumnWidths(wid);
        jScrollPane2.setViewportView(tb);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
}
