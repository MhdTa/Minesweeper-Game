/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Mohamed Taha
 */
public class GameRules implements Serializable{
  
        int MinesNumber;
        int Row,Col;
        int CountPlayers;
        int typePlayers;
        Score score;
        int timeRole;
        boolean autoSheild;
       int  countShields;
        
       
      public void clonRules(GameRules r)
      {
        this. MinesNumber=r.MinesNumber;
        this. Row=r.Row;
        this.Col=r.Col;
        this. CountPlayers=r.CountPlayers;
        this. typePlayers=r.typePlayers;
        this. score=r.score;
        this. timeRole=r.timeRole;
        this. autoSheild=r.autoSheild;
        this. countShields=r.CountPlayers;
      }
       
        public void setRules(GameRules rules)
        {
            this.MinesNumber = rules.MinesNumber;
            this.Row = rules.Row;
            this.Col = rules.Col;
        }
        
        public void initRules(int MinesNumber,int Row,int Col)
        {
            this.Col = Col;
            this.Row = Row;
            this.MinesNumber = MinesNumber;
            
        }
        public GameRules()
        {
            this.Col=6;
            this.Row=8;
            this.MinesNumber=8;
            this.timeRole=10;
            countShields=5;
        }
        
        public void setTimeRole(int timeRole)
        {
            this.timeRole = timeRole;
        }
        
        public void setAutoSheild(boolean bool)
        {
            this.autoSheild = bool;
        }
        
        //تحديد أبعاد الغريد
        public void SetSizeGrid(int row, int col)
        {
            
            this.Row = row;
            this.Col = col;
        }
        
        public void SetCountPlayers(int countPlayers)
        {
            this.CountPlayers = countPlayers;
        }
        
        //تحديد عدد الألغام
        public void SetMinesNumber(int nom)
        {
            this.MinesNumber = nom;
        }
        
        
        // لازم ينعملو أوفر لودنغ
        public int NewScore(ArrayList<ResultMove> results)
        {
            int sum = 0;
            int len = results.size();
            SquareStaute staute = results.get(0).square.getSquareStaute();
            
            if(results.get(0).typaMove==TypeMove.Reveal)
        {   // إذا المربع فيو رقم زيد الرقم للسكور
            if( staute == SquareStaute.OpenNumber)
                sum += results.get(0).square.arroundMines;
         //إذا المربع فاضي زيد عشرة للسكور وزيد واحد على كل مربع عم ينفتح تلقائيا
            else if(staute == SquareStaute.OpenEmpty)
                sum += score.empty.getValue() + len - 1;
            //open mine
            // else if(staute == SquareStaute.)
            else{
                sum+=score.mine.getValue();
                if(results.get(0).square.player.shildsNom!=0)
                {
                      sum+=score.shield.getValue();
                      
                }               
                if(results.get(0).square.player.shildsNom!=-1)

                results.get(0).square.player.decreaseShield();
            }
                
           
        }
        //إذا الحركة تأشير
        else
        {
            //إذا أشر على مربع فيو لغم
           if(results.get(0).square.IsMine() ) 
           {
               if(results.get(0).square.staute==SquareStaute.Marked)
                   sum+=score.markMine.getValue();
              else if(results.get(0).square.staute==SquareStaute.Closed)
                   sum-=score.markMine.getValue();;
           }
              
           //إذا أشر على مربع مافيو لغم
           else 
           if(results.get(0).square.staute==SquareStaute.Marked)
               sum += score.markEmpty.getValue();
           
        }
            
            return sum;
        }
        
        public PlayerStaute SetCurrentPlayerStaute(ResultMove result)
        {
           
//            if(result.square.getSquareStaute() == SquareStaute.OpenMine)       
//                return PlayerStaute.Lose;
            return PlayerStaute.Wait;
       
        }
        
        public Player ChangeCurrentPlayer(Player[] Players, int index)
        {

//            Players[index].Staute = PlayerStaute.Run;
           return Players[index];
                   
        }
        
        public int ChangeIndexCurrentPlayer(Player[] Players, int index)
        {
//          ++index;
//          
//          for(int i=index; i<CountPlayers; i++)
//          {
//              if(Players[i].Staute == PlayerStaute.Wait)
//                  return i;
//          }
//          
//          for(int i=0; i<index; i++)
//             if(Players[i].Staute == PlayerStaute.Wait)
//                  return i;
          
          return -1;
        }
    
        public boolean endGame(int indexCurrentPlayer, Grid grid )
        {
            
            SquareStaute staute;
            for(int i=0; i<Row; i++)
                for(int j=0; j<Col; j++)
                {
                    staute = grid.getSquare(i, j).getSquareStaute();
                    if(!grid.getSquare(i, j).IsMine() && (staute == SquareStaute.Closed )/*|| staute == SquareStaute.Marked*/)
                            
                        return false;
                }
            return true;
        }
        
        public int wenScore()
        {
            
            
            return 0;
        }
        
   
        public void boom()
        {
            
        }
        
       
        
        public String ShowScore()
        {
            return "";
        }
    
}
