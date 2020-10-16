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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
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
    private String p;    
    private MainPage ui;
    private Table tb = new Table();
    public FileJPlane(String Path, MainPage ui) {
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.p = Path;
        this.ui = ui;
        initComponents();
    }
    public javax.swing.JPanel getPanel(){
        return jPanel1;
    }
    int sz=0;
    int csz = 0;
    public void initComponents(){
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Object[][] _content = new Object[100000][];
        try {
            File f = new File(p);
            Path path = Paths.get(p);
            // byte[] data = Files.readAllBytes(path);     
            // System.out.println(Integer.toHexString((int) data[0]).toUpperCase() + Integer.toHexString((int) data[1]).toUpperCase());
            //////////////////////////////////////////////
            ////////////////////////////////////////////////
            InputStream is = new FileInputStream(f);
            int value = 0;
            StringBuilder sbText = new StringBuilder();
            while ((value = is.read()) != -1) {
                int bytesCounter =1;
                Object[] con = new Object[18];
                String pad = "";
                for(int i=0 ; i < 7 -Integer.toHexString(sz).toUpperCase().length();i++) {
                    pad += "0";
                }
                JTextArea jp = new JTextArea(pad+Integer.toHexString(sz).toUpperCase()+"0");
                jp.setFont(new java.awt.Font("Courier New", 0, 14));
                jp.setBackground(new java.awt.Color(204, 204, 255));
                con[0] = jp;
                JTextArea jin = new JTextArea(String.format("%02X", value));
                jin.setFont(new java.awt.Font("Courier New", 0, 14));
                jin.setBackground(new java.awt.Color(204, 204, 255));
                con[bytesCounter] = jin;
                if (!Character.isISOControl(value)) {
                    sbText.append((char)value);
                }else {
                    sbText.append(".");
                }
                bytesCounter++;
                
                while ((value = is.read()) != -1) {
                    //convert to hex value with "X" formatter
                    JTextArea j = new JTextArea(String.format("%02X", value));
                    j.setFont(new java.awt.Font("Courier New", 0, 14));
                    j.setBackground(new java.awt.Color(204, 204, 255));
                    con[bytesCounter] = j;

                    //If the chracater is not convertable, just print a dot symbol "."
                    if (!Character.isISOControl(value)) {
                        sbText.append((char)value);
                    }else {
                        sbText.append(".");
                    }

                    //if 16 bytes are read, reset the counter,
                    //clear the StringBuilder for formatting purpose only.
                    if(bytesCounter==16){
                        JTextArea jpt = new JTextArea(sbText.toString());
                        jpt.setFont(new java.awt.Font("Courier New", 0, 14));
                        jpt.setBackground(new java.awt.Color(204, 204, 255));
                        con[17] = jpt;
                        
                        sbText.setLength(0);
                        bytesCounter=0;
                        break;
                    }else{
                        bytesCounter++;
                    }
                }
                if(bytesCounter!=0){
                    //add spaces more formatting purpose only
                    csz = bytesCounter;
                    for(; bytesCounter<16; bytesCounter++){
                        JTextArea jpt = new JTextArea(sbText.toString());
                        jpt.setFont(new java.awt.Font("Courier New", 0, 14));
                        jpt.setBackground(new java.awt.Color(204, 204, 255));
                        con[17] = jpt;
                    }
                }
                _content[sz] = con;
                sz++;
                
            }
            //System.out.print(sbResult);
            is.close();
        } catch (Exception e1) { e1.printStackTrace(); }
        
        Object[][] _contentorg = new Object[sz][];
        for(int i=0 ;i<sz; i++){
            _contentorg[i] = _content[i];
            //System.out.println(_content[i][17]);
        }
        //System.out.println(sz);
        //System.out.println(csz);
        class TextBoxRender implements TableCellRenderer {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int r, int c) {
                //System.out.println(r);
                //System.out.println(c);
                if((r>=sz-1&&c>=csz-1)&&(c!=17)){
                    return null;
                }
                JTextArea lb1 = (JTextArea) _contentorg[r][c];
                lb1.setBackground(new java.awt.Color(204, 204, 255));
                return ((Component) lb1);
            }
        }
        
        
        tb.setFont(new java.awt.Font("Courier New", 0, 14));
        tb.setModel(new javax.swing.table.DefaultTableModel(
            _contentorg,
            new String [] {
                "", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "ASCII"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb.setDefaultRenderer(Object.class, new TextBoxRender());
        //tb.getColumn("ASCII").setCellRenderer(new TextBoxRender(0,0,0));
        tb.setBackground(new java.awt.Color(204, 204, 255));
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
        JTextField j = new JTextField();
        j.setEditable(false);
        j.setBackground(Color.yellow);
        DefaultCellEditor doubleclick = new DefaultCellEditor(j);
        doubleclick.setClickCountToStart(2);

        //set the editor as default on every column
        for (int i = 0; i < tb.getColumnCount(); i++) {
            tb.setDefaultEditor(tb.getColumnClass(i), doubleclick);
        } 
        mouseEvent m = new mouseEvent(tb, _contentorg, ui,sz,csz);
        tb.addMouseListener(m);
    }
    
}