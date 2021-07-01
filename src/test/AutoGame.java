/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;

/**
 *
 * @author عمير
 */
public class AutoGame  {

    ArrayList<Game> tutorial;
    ArrayList <PlayerMove> listMoves;
    int i;
    
    public AutoGame()
    {
        i=-1;
         tutorial=new ArrayList<Game>();
        listMoves=new ArrayList<PlayerMove>();

    }
    
    public void copy( ArrayList <PlayerMove> ListMoves)
    {
        for(int i=0;i<ListMoves.size();i++)
       listMoves.add(ListMoves.get(i));
    }
    
    public void init(GameRules r ,Grid grid)
    { 
        for (int count =0;count <listMoves.size();count++)
        { 
        Game g=new  guiGame();
        Grid ggg=new Grid();
        GameRules rr=new GameRules();
        rr.clonRules(r);
        g.rules=rr;
        g.InitGame();
        grid.reset();
      //  ggg.InitGrid(grid.Row, 1, grid.Col, 1);
         ggg.clonGrid(grid);
        g.grid=ggg;
                     
 
        for(int j=0;j<=count;j++)
        {
            g.CurrentMove=listMoves.get(j);
          
            
            g.ApplyPlayerMove();
        }
            tutorial.add(g);
      }
            
        
    }
    
    public boolean endTutorial()
    {
        return i>-1&&i<listMoves.size();
    }
    
    public Game next()
    {
        i++;
            if(i==tutorial.size())
            {
                i--;
            }

       return  tutorial.get(i);
        
    }
        public Game prev()
        {
            i--;
            if(i<=-1)
           i=0;

                   return  tutorial.get(i);

        }

    
}
