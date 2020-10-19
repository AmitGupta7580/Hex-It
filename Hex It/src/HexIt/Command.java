/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HexIt;

/**
 *
 * @author acer
 */
public class Command {
    int srow,erow;
    String snxt="",sprev="";
    Command(int sr,int er){
        this.erow=er;
        this.srow=sr;
    }
}
