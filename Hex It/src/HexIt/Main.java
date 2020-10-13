/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HexIt;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author udit gupta
 */

class Main{
    public static void main(String args[]) {
        try {
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
       // handle exception
        }
        catch (ClassNotFoundException e) {
       // handle exception
        }
        catch (InstantiationException e) {
       // handle exception
        }
        catch (IllegalAccessException e) {
       // handle exception
        }
        MainPage Ui = new MainPage();
        Ui.setVisible(true);
        
    }
}