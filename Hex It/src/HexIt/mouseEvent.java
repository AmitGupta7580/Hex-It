/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HexIt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Utilities;

/**
 *
 * @author udit gupta
 */
public class mouseEvent extends MouseAdapter {
    
    private MainPage ui;
    private Table tb;
    private Object[][] content;
    private int sz,csz;
    private static int cp=0,rp=0,tp=0;
    private DefaultHighlighter.DefaultHighlightPainter yellowPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
    mouseEvent(Table t, Object[][] con, MainPage ui, int sz, int csz){
        this.tb = t;
        this.content = con;
        this.ui = ui;
        this.sz = sz;
        this.csz = csz;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int row = tb.getSelectedRow();
        int col = tb.getSelectedColumn();
        if(tp==0){
            JTextArea r1 = (JTextArea) content[rp][17];
            Highlighter higr1 = r1.getHighlighter();
            JTextArea r2 = (JTextArea) content[rp][cp];
            Highlighter higr2 = r2.getHighlighter();
            higr1.removeAllHighlights();
            higr2.removeAllHighlights();
        }
        else if(tp==1){
            for(int i=1;i<=17;i++){
                JTextArea r1 = (JTextArea) content[rp][i];
                Highlighter higr1 = r1.getHighlighter();
                higr1.removeAllHighlights();
            }
        }
        else if(tp==2){
            for(int i=0;i<=17;i++){
                JTextArea r1 = (JTextArea) content[rp][i];
                Highlighter higr1 = r1.getHighlighter();
                higr1.removeAllHighlights();
            }
        }
        if(col>0&&col<17){
            tp = 0;
            JTextArea s1 = (JTextArea) content[row][17];
            Highlighter higs1 = s1.getHighlighter();
            JTextArea s2 = (JTextArea) content[row][col];
            Highlighter higs2 = s2.getHighlighter();
            try {
                higs1.addHighlight(col-1, col, yellowPainter);
                higs2.addHighlight(0, 2, yellowPainter);
            } catch (BadLocationException ex) {
                Logger.getLogger(FileJPlane.class.getName()).log(Level.SEVERE, null, ex);
            }
            rp = row;
            cp = col;
            int add = (row)*16 + col-1;
            String addressNormal = Integer.toHexString(add).toUpperCase();
            String padding = "";
            for(int i=0;i<8-addressNormal.length();i++){
                padding += "0";
            }
            ui.setCurrentAddressNormal(padding + addressNormal);
            ui.setCurrentAddressHex(s2.getText());
            ui.setCurrentAddressAscii(String.valueOf((char) Integer.parseInt(s2.getText(), 16)));
            ui.setCurrentAddressDec(Integer.toString(Integer.parseInt(s2.getText(), 16)));
            ui.setCurrentAddressBin(Integer.toBinaryString(Integer.parseInt(s2.getText(), 16)));
        }
        else if(col==17){
            tp = 1;
            rp = row;
            System.out.println(row);
            for(int i=1;i<17;i++){
                JTextArea sp = (JTextArea) content[row][i];
                Highlighter higsp = sp.getHighlighter();
                try {
                    higsp.addHighlight(0, 2, yellowPainter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(mouseEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JTextArea s1 = (JTextArea) content[row][17];
            Highlighter higs1 = s1.getHighlighter();
            int caretPosition = s1.getCaretPosition();
            try {
                System.out.println(caretPosition);
                higs1.addHighlight(caretPosition, 16, yellowPainter);
            } catch (BadLocationException ex) {
                Logger.getLogger(mouseEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
            JTextArea s2 = (JTextArea) content[row][0];
            ui.setCurrentAddressNormal("*"+s2.getText());
            ui.setCurrentAddressAscii(s1.getText());
            
            StringBuffer sh = new StringBuffer();
            StringBuffer sd = new StringBuffer();
            StringBuffer sb = new StringBuffer();
            char ch[] = s1.getText().toCharArray();
            for(int i = 0; i < ch.length; i++) {
                String hexString = Integer.toHexString(ch[i]);
                String decString = Integer.toString(Integer.parseInt(hexString, 16));
                String binString = Integer.toBinaryString(Integer.parseInt(hexString, 16));
                binString += " ";
                sh.append(hexString);
                sd.append(decString);
                sb.append(binString);
            }
            String hex = sh.toString();
            String dec = sd.toString();
            String bin = sb.toString();
            ui.setCurrentAddressHex(hex);
            ui.setCurrentAddressDec(dec);
            ui.setCurrentAddressBin(bin);
        }
        else{
            tp = 2;
            rp = row;
            System.out.println(row);
            for(int i=1;i<17;i++){
                JTextArea sp = (JTextArea) content[row][i];
                Highlighter higsp = sp.getHighlighter();
                try {
                    higsp.addHighlight(0, 2, yellowPainter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(mouseEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JTextArea s1 = (JTextArea) content[row][17];
            Highlighter higs1 = s1.getHighlighter();
            JTextArea s2 = (JTextArea) content[row][0];
            Highlighter higs2 = s2.getHighlighter();
            try {
                higs1.addHighlight(0, 16, yellowPainter);
                higs2.addHighlight(0, 8, yellowPainter);
            } catch (BadLocationException ex) {
                Logger.getLogger(mouseEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ui.setCurrentAddressNormal("*"+s2.getText());
            ui.setCurrentAddressAscii(s1.getText());
            
            StringBuffer sh = new StringBuffer();
            StringBuffer sd = new StringBuffer();
            StringBuffer sb = new StringBuffer();
            char ch[] = s1.getText().toCharArray();
            for(int i = 0; i < ch.length; i++) {
                String hexString = Integer.toHexString(ch[i]);
                String decString = Integer.toString(Integer.parseInt(hexString, 16));
                String binString = Integer.toBinaryString(Integer.parseInt(hexString, 16));
                binString += " ";
                sh.append(hexString);
                sd.append(decString);
                sb.append(binString);
            }
            String hex = sh.toString();
            String dec = sd.toString();
            String bin = sb.toString();
            ui.setCurrentAddressHex(hex);
            ui.setCurrentAddressDec(dec);
            ui.setCurrentAddressBin(bin);
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
        System.out.println("Mouse Moved");
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse draged");
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        int row = tb.getSelectedRow();
        int col = tb.getSelectedColumn();
        if(tp==0){
            JTextArea r1 = (JTextArea) content[rp][17];
            Highlighter higr1 = r1.getHighlighter();
            JTextArea r2 = (JTextArea) content[rp][cp];
            Highlighter higr2 = r2.getHighlighter();
            higr1.removeAllHighlights();
            higr2.removeAllHighlights();
        }
        else if(tp==1){
            for(int i=1;i<=17;i++){
                JTextArea r1 = (JTextArea) content[rp][i];
                Highlighter higr1 = r1.getHighlighter();
                higr1.removeAllHighlights();
            }
        }
        else if(tp==2){
            for(int i=0;i<=17;i++){
                JTextArea r1 = (JTextArea) content[rp][i];
                Highlighter higr1 = r1.getHighlighter();
                higr1.removeAllHighlights();
            }
        }
        if(col>0&&col<17){
            tp = 0;
            JTextArea s1 = (JTextArea) content[row][17];
            Highlighter higs1 = s1.getHighlighter();
            JTextArea s2 = (JTextArea) content[row][col];
            Highlighter higs2 = s2.getHighlighter();
            try {
                higs1.addHighlight(col-1, col, yellowPainter);
                higs2.addHighlight(0, 2, yellowPainter);
            } catch (BadLocationException ex) {
                Logger.getLogger(FileJPlane.class.getName()).log(Level.SEVERE, null, ex);
            }
            rp = row;
            cp = col;
            int add = (row)*16 + col-1;
            String addressNormal = Integer.toHexString(add).toUpperCase();
            String padding = "";
            for(int i=0;i<8-addressNormal.length();i++){
                padding += "0";
            }
            ui.setCurrentAddressNormal(padding + addressNormal);
            ui.setCurrentAddressHex(s2.getText());
            ui.setCurrentAddressAscii(String.valueOf((char) Integer.parseInt(s2.getText(), 16)));
            ui.setCurrentAddressDec(Integer.toString(Integer.parseInt(s2.getText(), 16)));
            ui.setCurrentAddressBin(Integer.toBinaryString(Integer.parseInt(s2.getText(), 16)));
        }
        else if(col==17){
            tp = 1;
            rp = row;
            System.out.println(row);
            for(int i=1;i<17;i++){
                JTextArea sp = (JTextArea) content[row][i];
                Highlighter higsp = sp.getHighlighter();
                try {
                    higsp.addHighlight(0, 2, yellowPainter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(mouseEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JTextArea s1 = (JTextArea) content[row][17];
            Highlighter higs1 = s1.getHighlighter();
            int caretPosition = s1.getCaretPosition();
            try {
                System.out.println(caretPosition);
                higs1.addHighlight(caretPosition, 16, yellowPainter);
            } catch (BadLocationException ex) {
                Logger.getLogger(mouseEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
            JTextArea s2 = (JTextArea) content[row][0];
            ui.setCurrentAddressNormal("*"+s2.getText());
            ui.setCurrentAddressAscii(s1.getText());
            
            StringBuffer sh = new StringBuffer();
            StringBuffer sd = new StringBuffer();
            StringBuffer sb = new StringBuffer();
            char ch[] = s1.getText().toCharArray();
            for(int i = 0; i < ch.length; i++) {
                String hexString = Integer.toHexString(ch[i]);
                String decString = Integer.toString(Integer.parseInt(hexString, 16));
                String binString = Integer.toBinaryString(Integer.parseInt(hexString, 16));
                binString += " ";
                sh.append(hexString);
                sd.append(decString);
                sb.append(binString);
            }
            String hex = sh.toString();
            String dec = sd.toString();
            String bin = sb.toString();
            ui.setCurrentAddressHex(hex);
            ui.setCurrentAddressDec(dec);
            ui.setCurrentAddressBin(bin);
        }
        else{
            tp = 2;
            rp = row;
            System.out.println(row);
            for(int i=1;i<17;i++){
                JTextArea sp = (JTextArea) content[row][i];
                Highlighter higsp = sp.getHighlighter();
                try {
                    higsp.addHighlight(0, 2, yellowPainter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(mouseEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JTextArea s1 = (JTextArea) content[row][17];
            Highlighter higs1 = s1.getHighlighter();
            JTextArea s2 = (JTextArea) content[row][0];
            Highlighter higs2 = s2.getHighlighter();
            try {
                higs1.addHighlight(0, 16, yellowPainter);
                higs2.addHighlight(0, 8, yellowPainter);
            } catch (BadLocationException ex) {
                Logger.getLogger(mouseEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ui.setCurrentAddressNormal("*"+s2.getText());
            ui.setCurrentAddressAscii(s1.getText());
            
            StringBuffer sh = new StringBuffer();
            StringBuffer sd = new StringBuffer();
            StringBuffer sb = new StringBuffer();
            char ch[] = s1.getText().toCharArray();
            for(int i = 0; i < ch.length; i++) {
                String hexString = Integer.toHexString(ch[i]);
                String decString = Integer.toString(Integer.parseInt(hexString, 16));
                String binString = Integer.toBinaryString(Integer.parseInt(hexString, 16));
                binString += " ";
                sh.append(hexString);
                sd.append(decString);
                sb.append(binString);
            }
            String hex = sh.toString();
            String dec = sd.toString();
            String bin = sb.toString();
            ui.setCurrentAddressHex(hex);
            ui.setCurrentAddressDec(dec);
            ui.setCurrentAddressBin(bin);
        }
        int r = tb.rowAtPoint(e.getPoint());
        if (r >= 0 && r < tb.getRowCount()) {
            tb.setRowSelectionInterval(r, r);
        } else {
            tb.clearSelection();
        }

        int rowindex = tb.getSelectedRow();
        if (rowindex < 0)
            return;
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
            boolean includeSpacing = false;
            Rectangle rect = tb.getCellRect(row, col, includeSpacing);
            PopUp pm = new PopUp(ui,row,col,tb,content,sz,csz);
            pm.show(e.getComponent(), rect.x+10, rect.y+20);
        }
    }
    
    public void mouseClicked(MouseEvent e) {
//        if(e.getClickCount()==2){
//            System.out.println("Double clicked");
//            int row = tb.getSelectedRow();
//            int col = tb.getSelectedColumn();
//            JTextArea r1 = (JTextArea) content[row][col];
//            r1.setEditable(false);
//        }
//        else{
//            System.out.println("Single clicked");
//            Point p = new Point(e.getX(),e.getY());
//            Component r = tb.getComponentAt(p);
//            System.out.println(r);
//        }
    }
}
