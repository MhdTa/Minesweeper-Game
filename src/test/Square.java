/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author hp
 */
public class Square implements Serializable{
    
    static int count;
    int ID;
    boolean mine;
    int row, col;
    String Color;
    SquareStaute staute;
    int arroundMines;
    Player player;
    boolean shield;
    //Button b ;
    
    public SquareStaute getSquareStaute()
    {
        return staute;
    }
    //تهيئة المربع
    public Square(int x,int y)
    {
        this.ID = Square.count++;
        this.mine = false;
        this.staute = SquareStaute.Closed;
        this.arroundMines = 0;
        this.row = x;
        this.col = y; 
        this.shield=false;
    }
      //تهيئة المربع
    public Square(Player player,int x,int y)
    {
        this.ID = Square.count++;
        this.mine = false;
        this.staute = SquareStaute.Closed;
        this.arroundMines = 0;
        this.row = x;
        this.col = y; 
        this.player = player;
        this.shield=false;

    }
    
    public int GetID()
    {
        return this.ID;
    }
    public void reSet()
    {
      
        
                this.staute = SquareStaute.Closed;
        this.player =null;
    }
    
    public void increaseArroundMines()
    {
        arroundMines++;
    }
    
    
     public  Square clonSquare(Square s1)
    {
        this.row=s1.row;
        this.col=s1.col;
       // this.player=null;
       this.ID=s1.ID;
       this.mine=s1.mine; 
       this. Color=s1.Color;
       this. staute=s1.staute;
       this. arroundMines=s1.arroundMines;
       this. shield=s1.shield;
      return this;  
    }
    //التعديل على المربع حسب نوع الحركة واللاعب
    public ResultMove ApplyChange(TypeMove type, Player player)
    {
        
        this.player = player;
       // System.out.println("player: "+player.Name);
        if(type == TypeMove.Mark)
            this.staute = (this.staute == SquareStaute.Marked)?SquareStaute.Closed:SquareStaute.Marked;
        else 
        {
            if(this.mine) {
                this.staute = SquareStaute.OpenMine;

            }
            else if(this.arroundMines == 0)
                this.staute = SquareStaute.OpenEmpty;
            else this.staute = SquareStaute.OpenNumber;
        }
        return new ResultMove(this, type);
        
    }
    
    //هل المربع يحوي لغم؟ 
    public boolean IsMine()
    {
        
        return mine;
    }
    
    //يضع لغم في المربع
    public boolean PutMine()
    {
        
        if(this.mine)
            return false;
        return this.mine = true;
        
    }
    
    public int getRow()
    {
        return this.row;
    }
    
    public int getCol()
    {
        return this.col;
    }
    
}
