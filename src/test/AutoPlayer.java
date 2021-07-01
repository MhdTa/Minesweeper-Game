/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class AutoPlayer extends Player{
    Square[][] grid;
    Random rand = new Random();
    int row, col;
    boolean isUseShield;
    int time;
    
    ArrayList<PlayerMove> moves;
   

    AutoPlayer(String name, int row, int col, boolean isUseShield , int time) {
        this.isUseShield = isUseShield;
        this.Name = name;
        setGridSize(row, col);
        this.generateMoves();
        this.time=time;
    }
    
    public void setGridSize(int row, int col)
    {
        this.row = row;
        this.col = col;
        this.grid = new Square[row][col];
        
    }
    
//    void editGrid(Square[][] grid)
//    {
//        for(int i=0; i<row; i++)
//            for(int j=0; j<col; j++)
//                this.grid[i][j].clone(grid[i][j]);
//    }
    
    
    public void generateMoves()
    {
        moves = new ArrayList<PlayerMove>();
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                moves.add(new PlayerMove(this, new Square(this, i, j), TypeMove.Reveal));
    }
    
    @Override
    public void setCurrentMove(PlayerMove move)
    {
        
    }
    
    
      @Override
    public void run()
    { Random r=new Random();
//    int []y=new int[4];
//    y[0]=1000;
//    y[1]=2000;
//    y[2]=3000;
//    y[3]=12000;
        int x;
        while(true)
        {
            
            if (!isUseShield)
                this.shildsNom=0;
            
            System.out.println("Auto...");
            if(this.Staute == PlayerStaute.Wait)
                try {
                    Thread.sleep(1000);
            } catch (InterruptedException ex) {
                
            }
            
            if(this.Staute == PlayerStaute.Lose || this.Staute == PlayerStaute.Win)
                return;
            
            if(this.Staute == PlayerStaute.Run)
            {x=r.nextInt(time+1)*1000;
                if(this.currentMove == null)
                {
            try {
                sleep(x);
                
                
            } catch (InterruptedException ex) {     }
            this.currentMove =  moves.remove(rand.nextInt(moves.size()));
                }
            }
            
        }
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
    
}