/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HexIt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author udit gupta
 */
public class mouseEvent extends MouseAdapter {
    
    private MainPage ui;
    private Table tb;
    private Object[][] content;
    private static int cp=0,rp=0;
    private DefaultHighlighter.DefaultHighlightPainter yellowPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
    mouseEvent(Table t, Object[][] con, MainPage ui){
        this.tb = t;
        this.content = con;
        this.ui = ui;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int row = tb.getSelectedRow();
        int col = tb.getSelectedColumn();
        JTextArea r1 = (JTextArea) content[rp][17];
        Highlighter higr1 = r1.getHighlighter();
        JTextArea r2 = (JTextArea) content[rp][cp];
        Highlighter higr2 = r2.getHighlighter();
        higr1.removeAllHighlights();
        higr2.removeAllHighlights();
        if(col>0&&col<17){
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
            ui.setCurrentAddressAscii((char) Integer.parseInt(s2.getText(), 16));
            ui.setCurrentAddressDec(Integer.toString(Integer.parseInt(s2.getText(), 16)));
            ui.setCurrentAddressBin(Integer.toBinaryString(Integer.parseInt(s2.getText(), 16)));
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
            //JPopupMenu pm = new popupmenu();
            //pm.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            System.out.println("Double clicked");
            int row = tb.getSelectedRow();
            int col = tb.getSelectedColumn();
            JTextArea r1 = (JTextArea) content[row][col];
            r1.setEditable(false);
        }
        else{
            System.out.println("Single clicked");
            Point p = new Point(e.getX(),e.getY());
            Component r = tb.getComponentAt(p);
            System.out.println(r);
        }
    }
}
