/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Scanner;


/**
 *
 * @author hp
 */
public class ConsoleGame extends Game{

    @Override
    public void InitGame()
    {
         super.InitGame();
         PlayerList[0] = new ConsolePlayer(Integer.toString(0));
         if(rules.typePlayers==1)
                PlayerList[1] = new ConsolePlayer(Integer.toString(1));
            else if(rules.typePlayers==2)
            {
                PlayerList[1] = new AutoPlayer(Integer.toString(1),rules.Row,rules.Col,rules.autoSheild,0);
                Thread thr = new Thread( PlayerList[1]);
                thr.setName("Auto Player");
                thr.start();
            }
        PlayerList[0].Staute = PlayerStaute.Run;
        Currentplayer = PlayerList[0];
         
    }
    

    public void Show()
    {
        SquareStaute s;
        for(int i=0; i<grid.Row; i++)
        {
            System.out.print("\n");
            for(int j=0; j<grid.Col; j++)
            {
                s = grid.getSquare(i, j).getSquareStaute();
                if(grid.grid[i][j].shield)
                {
                     System.out.print("sh ");
                     continue;
                }
              
                switch(s)
                {
                    case OpenMine:
                        System.out.print("mi ");
                        break;
                    case OpenNumber:
                            System.out.print(grid.getSquare(i, j).arroundMines+"  ");
                            break;
                    case OpenEmpty:
                            System.out.print("oe ");
                        break;
                    case Marked:
                        System.out.print("ma ");
                        break;
                    case Closed:
                        System.out.print("cl "); 
                         break;
                             
                    
                }
                
            }
        }
        System.out.println("\nname: " + Currentplayer.Name
                + "\tScore: " + Currentplayer.Score + "NomShield:" + Currentplayer.shildsNom );
      //  System.out.println(rules.ShowScore());
    }

    @Override
    public void restart() {
        this.InitGame();
        Show();
        
    }
    
    
    
}
