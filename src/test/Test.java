/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void con()
    {
             
     Scanner input = new Scanner(System.in);
      fileGame file=new fileGame();
      ConsoleGame game = new ConsoleGame();
        game.rules.initRules(5, 5, 5);
        game.rules = game.createSingleRules( game.rules);
        game.rules.SetCountPlayers(1);
           System.out.println("load Game:");
        int aa;
         game.InitGame();
      aa=input.nextInt();
    if(aa==1)
    game=(ConsoleGame) file.loadGame("omai111r");
        
    
        game.Show();
        PlayerMove move;
        
  
        
        while(game.gameStaute == GameStaute.run){

        int x,y;
         int row = 0, col = 0 ,typeMove = 0 ,save=0;
        boolean flage = false;
        do
        {
            try
            {
                row = input.nextInt();
                col = input.nextInt();
                typeMove = input.nextInt();
                save=input.nextInt();
                flage = true;
            }
            catch(Exception ex)
            {
                System.out.println("");  
            }
        }while(!flage);
             
        Square square = new Square(row-1, col-1);
        square.player = game.Currentplayer;
        move=new PlayerMove(game.Currentplayer, square, (typeMove == 0)?TypeMove.Reveal:TypeMove.Mark);
          
           game.CurrentMove=move;
     if(save==1)
//         file.saveGame(game, "omai111r");
        //  game.getNextMove();
        
        if(game.AcceptPlayerMove())
        {
            game.ApplyPlayerMove();
            game.changePlayer();
        }
        
        game.Show();
        
        
        }
      
    }
     
    public static void tooo()
    {
        Scanner input = new Scanner(System.in);
      fileGame file=new fileGame(); 
        int r;
        AutoGame auto=new AutoGame();
     ConsoleGame game = new ConsoleGame();    
    game=(ConsoleGame) file.loadGame("omai111r");
        auto.copy(game.listMoves);
        auto.init(game.rules,game.grid);
        
        while(true)
        {
            
         System.out.println("next");
         r = input.nextInt();
          if(r==1)
          game=(ConsoleGame) auto.next();
          else  game=(ConsoleGame) auto.prev(); 
          game.Show();
        }
    }
    public static void main(String[] args)  {
        // TODO code application logic here
   
// con();
// tooo() ;    
        
        
        
        
////       
 gui game2 =new gui();
 
// //game2.setGame(game);
      game2.run(args);


    }
    
}
