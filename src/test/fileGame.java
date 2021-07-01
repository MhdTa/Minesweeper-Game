/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author عمير
 */
public class fileGame {
    
    
    PathsFile tutorialPaths;
    
 public void saveGame(Game game, String nameFile,String p)  
 {
      
     try
     {
         FileOutputStream file=new FileOutputStream(new File (nameFile));
         ObjectOutputStream ObjectFile=new ObjectOutputStream(file);
         
         ObjectFile.writeObject(game);
         file.close();
         ObjectFile.close();
         PathsFile temp = new PathsFile();
       temp.paths =   loadPaths(p);
       if(temp.paths==null)
           temp.paths = new ArrayList<String>();
             temp.paths.add(nameFile);
         savePaths(temp, p);
         
     } catch (FileNotFoundException ex) { System.out.println("erore"); } 
        catch (IOException ex) {     System.out.println("erore"); }    
 }
    
    
public Game loadGame(String nameFile)
{Game game=null;
     try {
         FileInputStream file=new FileInputStream(new File (nameFile));
         ObjectInputStream ObjectFile=new ObjectInputStream(file);
         game=(Game)ObjectFile.readObject();
     } catch (FileNotFoundException ex) {} catch (IOException ex) {}
     catch (ClassNotFoundException ex) {}
       
         
         
         
         
   return game; 
}

public void saveRecord(scoreBoard s, String nameFile)  
 {
     try
     {
         FileOutputStream file=new FileOutputStream(new File (nameFile));
         ObjectOutputStream ObjectFile=new ObjectOutputStream(file);
         
         ObjectFile.writeObject(s);
         file.close();
         ObjectFile.close();
         
     } catch (FileNotFoundException ex) { System.out.println("erore"); } 
        catch (IOException ex) {     System.out.println("erore"); }    
 }
    
    
public scoreBoard  loadRecord(String nameFile)
{scoreBoard s=null;
     try {
         FileInputStream file=new FileInputStream(new File (nameFile));
         ObjectInputStream ObjectFile=new ObjectInputStream(file);
         s=(scoreBoard) ObjectFile.readObject();
     } catch (FileNotFoundException ex) {} catch (IOException ex) {}
     catch (ClassNotFoundException ex) {}
     return s; 
}

public ArrayList<String> loadPaths(String p) 
{ PathsFile loadPaths = null;
    try{
   
     FileInputStream file=new FileInputStream(new File (p));
         ObjectInputStream ObjectFile=new ObjectInputStream(file);
         loadPaths = (PathsFile)ObjectFile.readObject();
    } catch (FileNotFoundException ex) {} catch (IOException ex) {}
     catch (ClassNotFoundException ex) {}
         return loadPaths==null?null:loadPaths.paths;
    
}
public void savePaths(PathsFile s ,String p)  
 {
     try
     {
         FileOutputStream file=new FileOutputStream(new File (p));
         ObjectOutputStream ObjectFile=new ObjectOutputStream(file);
         
         ObjectFile.writeObject(s);
         file.close();
         ObjectFile.close();
         
     } catch (FileNotFoundException ex) { System.out.println("erore"); } 
        catch (IOException ex) {     System.out.println("erore"); }    
 }






}