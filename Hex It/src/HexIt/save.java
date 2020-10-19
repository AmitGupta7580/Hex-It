/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HexIt;

/**
 *
 * @author Divyansh
 */
 class Save {
   
  
  
   
   
   
   
   public static void ammend(FileJPlane obj,MainPage ui,int row,int col, String newval,String prevval)
   {int k=0;
       try{
           
       
           
           System.out.println("Mod Val"+prevval);
           
          
       
       
       String savedval= obj.hexeachbit[row][col-1];
       System.out.println("New Val"+newval);
        System.out.println("Saved Val"+savedval);
   
       if(newval.equals(savedval))
       {
         if(!(prevval.equals(savedval)))
         {
         obj.modcount--;}
       }
       else
       {
           if(prevval.equals(savedval))
           {
               obj.modcount++;
           }
       }
         
      System.out.println("ModCount:"+obj.modcount);
     
      ui.changeTabName(obj);
      
      }
       catch (Exception e1) { e1.printStackTrace(); }
    
}
 }










































