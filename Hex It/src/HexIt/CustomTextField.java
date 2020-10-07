/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HexIt;

/**
 *
 * @author udit gupta
 */
public class CustomTextField {
    
    public javax.swing.JTextField Text;
    
    CustomTextField(){
        Text = new javax.swing.JTextField();
        Text.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        Text.setMinimumSize(new java.awt.Dimension(15, 15));
        Text.setPreferredSize(new java.awt.Dimension(15, 15));
    }
    
    public javax.swing.JTextField getObject(){
        return Text;
    }
    
    public void setText(String b){
        Text.setText(b);
    }
}
