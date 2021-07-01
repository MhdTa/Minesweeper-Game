/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Mohamed Taha
 */
public class guiPlayer extends Player {

    public guiPlayer(String Name) {
        super(Name);
    }
    
    @Override
    public void setCurrentMove(PlayerMove move)
    {
        this.currentMove = move;
    }
    
     @Override
    public PlayerMove GetPlayerMove(PlayerMove mov) 
    {
        PlayerMove move = null;
        if(this.currentMove != null)
        {
            move = new PlayerMove(this.currentMove.player,this.currentMove.square,this.currentMove.typeMove);
            this.currentMove = null;
        }
        return move;
    }

    @Override
    public void run() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
  
    
}
