/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;

/**
 *
 * @author hp
 */
public class PlayerMove implements Serializable{
    
    Player player;
    Square square;
    TypeMove typeMove;
    
    public PlayerMove(Player player, Square square, TypeMove type)
    {
        this.player = player;
        this.square = square;
        this.typeMove = type;
        
    }
      public PlayerMove()
    {
      
        
    }

    public TypeMove getTypeMove()
    {
        return typeMove;
    }
    
    public Square getSquare()
    {
        return this.square;
    }
    
    public Player getPlayer()
    {
        return this.player;
    }
    
}