/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hp
 */
public  class Grid implements Serializable{
    
    Square[][] grid;
    int Row;
    int Col;
  
    ArrayList<ResultMove> currentresults;
    
    
    public void InitGrid(int row, int col, int minesNom,int nomShields)
    {
        //تهيئة المصفوفة
        this.Row = row;
        this.Col = col;
            grid = new Square[row][col]; 
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                grid[i][j] = new Square(i, j);
        Square.count = 0;
        currentresults = new ArrayList<ResultMove>();
        
        SetMines(minesNom);
         setShield(nomShields);
    }
    public void reset()
    {
        for(int i=0;i<Row;i++)
            for(int j=0;j<Col;j++)
            grid[i][j].reSet();
        this. currentresults = new ArrayList<ResultMove>();
    }
    
    
    //يوزع الألغام على الرقعة
    void SetMines(int minesNom)
    {
        Random r = new Random();
        int count = 0;
        int x, y;
        while(count < minesNom)
        {
            if(grid[x = r.nextInt(Row)][y = r.nextInt(Col)].PutMine())
            {
                increaseArroundNumberMine(x, y);
                count++;                
            }
        }        
    }
    void setShield(int shieldNom)
    {
      Random r = new Random();
        int count = 0;
        int x, y;
        while(count <shieldNom)
        {
            if(!grid[x = r.nextInt(Row)][y = r.nextInt(Col)].mine&&!grid[x ][y ].shield)
            {
              grid[x][y].shield=true;
               count++;                
            }
        }        
    }
    public void clonGrid(Grid grid)
    {
        this.grid = new Square[grid.Row][grid.Col];
        this.Col = grid.Col;
        this.Row = grid.Row;
            for(int i=0;i<grid.Row;i++)
            for(int j=0;j<grid.Col;j++)
            {
                Square s=new Square(i, j);
                this.grid [i][j]=s.clonSquare(grid.grid[i][j]);
            }
 this. currentresults = new ArrayList<ResultMove>();
        
    }
    //يزود واحد لكل المربعات المجاورة
    void increaseArroundNumberMine(int x, int y)
    {
        int[] xx = new int[8];
        int[] yy = new int[8];
        yy[0] = 0; yy[1] = 0; yy[2] = 1; yy[3] = 1; yy[4] = 1; yy[5] = -1; yy[6] = -1; yy[7] = -1;
        xx[0] = 1; xx[1] = -1; xx[2] = 0; xx[3] = 1; xx[4] = -1; xx[5] = 1; xx[6] = 0; xx[7] = -1;
        
        for(int i=0; i<8; i++)
        {
            try
            {
                grid[x+xx[i]][y+yy[i]].increaseArroundMines();
            }
            catch(ArrayIndexOutOfBoundsException ex)
            {
                
            }
        }
    }
    
    // يطبق الحركة على مربع معين يحدد باحداثياته
    public ArrayList<ResultMove> ApplyPlayerMove(PlayerMove move)
    {
        int row = move.getSquare().getRow(), col = move.getSquare().getCol();
        currentresults.clear();
        currentresults.add(grid[row][col].ApplyChange(move.getTypeMove(), move.getPlayer()));
        
        FloodFill(row, col, move.getPlayer());
        
       
        
        return currentresults;
    }
    
    void FloodFill(int x, int y, Player player)
    {
        
        if(grid[x][y].getSquareStaute() == SquareStaute.OpenEmpty)
        {
            int[] xx = new int[8];
            int[] yy = new int[8];
            
            yy[0] = 0; yy[1] = 0; yy[2] = 1; yy[3] = 1; yy[4] = 1; yy[5] = -1; yy[6] = -1; yy[7] = -1;
            xx[0] = 1; xx[1] = -1; xx[2] = 0; xx[3] = 1; xx[4] = -1; xx[5] = 1; xx[6] = 0; xx[7] = -1;
            for(int i=0; i<8; i++)
            {
                try{
                if(grid[x+xx[i]][y+yy[i]].getSquareStaute() == SquareStaute.Closed)
                {
                    currentresults.add(grid[x+xx[i]][y+yy[i]].ApplyChange(TypeMove.FloodFillReveal, player));
                    
                    FloodFill(x+xx[i], y+yy[i], player);
                    
                }
                }catch(Exception ex)
            {

            }
            }
        }
        
        
    }
    
    
    public boolean AcceptPlayerMove(PlayerMove move)
    {
        int row, col;
        row = move.square.getRow();
        col = move.square.getCol();
        try{
//        if(grid[row][col].getSquareStaute() == SquareStaute.Closed)
//            return true;
//        if(grid[row][col].getSquareStaute() == SquareStaute.Marked && move.getTypeMove()==TypeMove.Mark && move.player.Name.equals(grid[row][col].player.Name))
//            return true;
          
            switch(move.typeMove)
            {
                case Mark:
                    if(grid[row][col].getSquareStaute() == SquareStaute.Marked && move.player.Name.equals(grid[row][col].player.Name))
                        return true;
                    if(grid[row][col].getSquareStaute() == SquareStaute.Closed)
                        return true;
                    break;
                case Reveal:
                    if(grid[row][col].getSquareStaute() == SquareStaute.Closed)
                        return true;
                    break;
            }
        }catch(ArrayIndexOutOfBoundsException ex)
        {
            return false;
        }
        return false;
    }
    
    public Square getSquare(int row, int col)
    {
        return this.grid[row][col];
    }
    
}
