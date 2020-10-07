/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HexIt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.*;
import javax.swing.GroupLayout;

/**
 *
 * @author udit gupta
 */
public class FileTabs {
    private javax.swing.JPanel jPanel3;
    private javax.swing.GroupLayout jPanel3Layout;
    private GroupLayout.ParallelGroup horizontalgrp;
    private GroupLayout.SequentialGroup verticalgrp;
    FileTabs(){
        jPanel3 = new javax.swing.JPanel();
        jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        horizontalgrp = jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        verticalgrp = jPanel3Layout.createSequentialGroup();
        verticalgrp.addContainerGap();
    }
    public javax.swing.JPanel getPanel(){
        return jPanel3;
    }
    public void setContent(String filePath){
        try {
            File f = new File(filePath);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            CustomTextField T;
            byte[] chunk = null;
            int readStatus = 0;
            while (true) {
                GroupLayout.SequentialGroup horgrp = jPanel3Layout.createSequentialGroup();
                GroupLayout.ParallelGroup vergrp = jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
                chunk = new byte[16];
                readStatus = bis.read(chunk, 0, 16);
                char[] line = new char[16];

                if (readStatus == -1)
                    break;
                
                for (byte i=0; i < readStatus; i++) {
                    T = new CustomTextField();
                    int readByte = (chunk[i] < 0) ? (-1 * (int) chunk[i]) : chunk[i];
                    String paddingZero = (readByte < 16) ? "0" : "";
                    T.setText(paddingZero + Integer.toHexString(readByte).toUpperCase());
                    horgrp.addComponent(T.getObject(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                    horgrp.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
                    vergrp.addComponent(T.getObject(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                    line[i] = (readByte >= 33 && readByte <= 126) ? (char) readByte : (char) 46;
                    
                }
                horizontalgrp.addGroup(horgrp);
                verticalgrp.addGroup(vergrp);
                verticalgrp.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
                //We add some padding to print the text line right below the one above.
                /*String padding = new String();
                if (readStatus < 16) {
                    for (byte i=0; i < 16-readStatus; i++) {
                        padding += " ";
                    }
                }

                Content += (padding + new String(line));
                Content += "\n";*/
                
            }
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(horizontalgrp)
                    .addContainerGap(413, Short.MAX_VALUE)
                )
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(verticalgrp)
            );
        } catch (Exception e1) { e1.printStackTrace(); }
    }
}