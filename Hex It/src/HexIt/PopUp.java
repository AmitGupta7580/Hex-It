/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HexIt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author udit gupta
 */
public class PopUp extends JPopupMenu{
    
    private Object[][] content;
    private int row,col,sz,csz;
    private Table tb;
    public FileJPlane file;
    private MainPage ui;
    private javax.swing.JMenuItem insetitm,deleteitm,edtitm,sendConvter,sendCalc,sendCalc2;
    
    PopUp(MainPage ui, int row, int col, Table tb, Object[][] content, int sz, int csz,FileJPlane file){
        this.ui = ui;
        this.row = row;
        this.file=file;
        this.sz = sz;
        this.csz = csz;
        this.col = col;
        this.tb = tb;
        this.content = content;
        insetitm = new javax.swing.JMenuItem();
        insetitm.setText("Insert New Cell");
        insetitm.setSize(80, 25);
        insetitm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Insert cell : " + Integer.toString(row) + Integer.toString(col));
                if((col==0)||(col==17)){
            
                }
                else{
                    JTextField data = new JTextField(25);
                    JTextField num = new JTextField(1);

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("Data :"));
                    myPanel.add(data);
                    //myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("No. of Cell to insert :"));
                    myPanel.add(num);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
                    //System.out.println("X : " + data.getText() + "Y : " + num.getText());
                    if(inputvalid(data.getText())&&inputvalid(num.getText())){
                        
                    }
                }
            }
        });
        deleteitm = new javax.swing.JMenuItem();
        deleteitm.setText("Delete Cell    ");
        deleteitm.setSize(80, 25);
        deleteitm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                /*System.out.println("Delete cell : " + Integer.toString(row) + Integer.toString(col));
                if((col==0)||(col==17)){
                        
                }
                else{
                    // changing hex values.
                    int i=row,j=col;
                    while(true){
                        try{
                            if(j==16){
                                content[i][j] = content[i+1][1];
                                i++;
                                j=1;
                            }
                            else{
                                content[i][j] = content[i][j+1];
                                j++;
                            }
                        } catch (NullPointerException e){
                            JTextArea jta = (JTextArea) content[i][j]; 
                            jta.setText("00");
                            break;
                        } catch (ArrayIndexOutOfBoundsException e){
                            break;
                            
                        }
                    }
                    // changing ascii values.
                }*/
            }
        });
        edtitm = new javax.swing.JMenuItem();
        edtitm.setText("Edit Cell      ");
        edtitm.setSize(80, 25);
        edtitm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Command c=new Command(row,row);
                file.setprv(c);
                System.out.println("Edit cell : " + Integer.toString(row) + Integer.toString(col));
                if((col==0)||(col==17)){
                }
                else{
                    JOptionPane jop = new JOptionPane();
                    String change_value = jop.showInputDialog("Enter the Text"); 
                    if(inputvalid(change_value)){
                        JTextArea j = (JTextArea) content[row][col];                        
                        j.setFont(new java.awt.Font("Courier New", 0, 14));
                        String mod = j.getText();
                        j.setBackground(new java.awt.Color(204, 204, 255));
                        JTextArea js = (JTextArea) content[row][17];                        
                        js.setFont(new java.awt.Font("Courier New", 0, 14));
                        js.setBackground(new java.awt.Color(204, 204, 255));
                        String prev = js.getText();
                        String chan = prev.substring(0,col-1) + (char) Integer.parseInt(change_value, 16) + prev.substring(col);
                        j.setText(change_value);
                        js.setText(chan);
                        Save.ammend(file,ui,row, col, change_value,mod);
                    }
                }
                file.setnxt(c);
                file.insertCommand(c);
            }
        });
        sendConvter = new javax.swing.JMenuItem();
        sendConvter.setText("Send to Converter");
        sendConvter.setSize(80, 25);
        sendConvter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Send to conv : " + Integer.toString(row) + Integer.toString(col));
                if((col==0)||(col==17)){
                    JTextArea js = (JTextArea) content[row][17];
                    ui.flbytesendconverter(js.getText(),1);
                }
                else{
                    JTextArea j = (JTextArea) content[row][col];
                    ui.flbytesendconverter(j.getText(),0);
                }
            }
        });
        sendCalc = new javax.swing.JMenuItem();
        sendCalc.setText("Send to Calculater(1)");
        sendCalc.setSize(80, 25);
        sendCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("send to calc : " + Integer.toString(row) + Integer.toString(col));
                if((col==0)||(col==17)){
                    JTextArea js = (JTextArea) content[row][17];
                    ui.flbytesendcalculater(js.getText(),1);
                }
                else{
                    JTextArea j = (JTextArea) content[row][col];
                    ui.flbytesendcalculater(j.getText(),0);
                }
            }
        });
        sendCalc2 = new javax.swing.JMenuItem();
        sendCalc2.setText("Send to Calculater(2)");
        sendCalc2.setSize(80, 25);
        sendCalc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("send to calc : " + Integer.toString(row) + Integer.toString(col));
                if((col==0)||(col==17)){
                    JTextArea js = (JTextArea) content[row][17];
                    ui.flbytesendcalculater2(js.getText(),1);
                }
                else{
                    JTextArea j = (JTextArea) content[row][col];
                    ui.flbytesendcalculater2(j.getText(),0);
                }
            }
        });
        add(edtitm);
        addSeparator();
//        add(insetitm);
//        addSeparator();
//        add(deleteitm);
//        addSeparator();
        add(sendConvter);
        addSeparator();
        add(sendCalc);
        addSeparator();
        add(sendCalc2);
    }
    
    public boolean inputvalid(String txt){
        return true;
    }
    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        System.out.println("Insert cell : " + Integer.toString(row) + Integer.toString(col));
        
    } 
    public void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        System.out.println("Delete cell : " + Integer.toString(row) + Integer.toString(col));
    }
    
}


