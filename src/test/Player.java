/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public abstract class Player extends Thread implements Serializable{
    
    String Name;
    int Score;
    PlayerStaute Staute;
    int shildsNom;
     PlayerMove currentMove;

    
    //تهيئة اللاعب 
    public Player(String name)
    {
         shildsNom=0;
        this.Score = 0;
        this.Name = name;
        this.Staute = PlayerStaute.Wait;
    }
    
    public Player()
    {
         shildsNom=0;
        this.Score = 0;
        this.Staute = PlayerStaute.Wait;
    }
    public void reset()
    {
        Score=0;
    }
   public void clonplyer(Player p)
   {
      // this. Name=p.Name;
       
    this. Score=p.Score;
    this. Staute=p.Staute;
    
    this. shildsNom=p.shildsNom;
     this. currentMove=null;
   }
    
   public void wenscor() 
   {if(this.shildsNom==-1)
       
       return ;
   else
       Score+= shildsNom*50;
   }
    public void SetPlayerName(String name)
    {
        this.Name = name;
    }
    
    
    
    //يقوم اللاعب بحركة
    public abstract PlayerMove GetPlayerMove(PlayerMove move);
  
    
    //يقوم بالتعديل على السكور اما زيادة او نقصان
    public void ChangeScore(int score)
    {
        this.Score +=score;
    }
    
    public void ChangeStaute(PlayerStaute staute)
    {
        this.Staute = staute;
    }
    public void increaseShield()
    {
         shildsNom++;
    }
    public void decreaseShield()
    {
     shildsNom--;
    }
    
    public void setCurrentMove(PlayerMove move)
    {
       this.currentMove = move;
    }
    
    public PlayerMove getCurrentMove()
    {
        return null;
    }
    
}
