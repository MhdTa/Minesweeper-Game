/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Mohamed Taha
 */
public class guiGame extends Game{
       @Override
    public void InitGame()
    {
         super.InitGame();
         PlayerList[0] = new guiPlayer(Integer.toString(0));
         if(rules.typePlayers==1)
                PlayerList[1] = new guiPlayer(Integer.toString(1));
            else if(rules.typePlayers==2)
            {
                PlayerList[1] = new AutoPlayer(Integer.toString(1),rules.Row,rules.Col,rules.autoSheild,rules.timeRole);
                Thread thr = new Thread( PlayerList[1]);
                thr.setName("Auto Player");
                thr.start();
            }
         
//           for(int i=0; i<rules.CountPlayers; i++)
//        {
//           // System.out.println("Player name" + i + ": ");
//           // name = input.next();
//            if(i==0)
//            PlayerList[i] = new guiPlayer(Integer.toString(i));
//            else
//            {
//                if(rules.typePlayers==0)
//                    PlayerList[i] = new guiPlayer(Integer.toString(i));
//              else  if(rules.typePlayers==1)
//                    PlayerList[i] = new AutoPlayer(Integer.toString(i),rules.Row,rules.Col);
//                    
//            }
//                 
//        }
        PlayerList[0].Staute = PlayerStaute.Run;
        Currentplayer = PlayerList[0];
         

    }

    
    

   

    @Override
    public void restart() {
      this.InitGame();
      this.indexCurrentPlayer=0;

    }

 
}
