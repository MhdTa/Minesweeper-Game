/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author hp
 */
public abstract class Game extends Thread implements Serializable{

    Player[] PlayerList;
    Player Currentplayer;
    int indexCurrentPlayer;
    String scoreBoard="Data\\ScoreBoard.bin";
    String data = "Data\\tutorials";
    String loadPaths = "Data\\loadPaths.bin";
    String tutorialPaths = "Data\\tutorialPaths.bin";
    Grid grid;
    GameRules rules;
    PlayerMove CurrentMove;
    GameStaute gameStaute;
    Timer timer;
    ArrayList <PlayerMove> listMoves;
    scoreBoard board;
    
   
    
    public singleRules createSingleRules(GameRules rules) {
        return new singleRules(rules);
    }

    public multiRules createMultieRules(int type, GameRules rules) {
        return new multiRules(type, rules);
    }

    public Game() {
        this.rules = new GameRules();
        this.timer = new Timer();
        timer.setMaxValue(10);
        timer.reset();
        listMoves=new ArrayList<PlayerMove>();
        this.board = new scoreBoard();
        
        //this.timer.start();

    }
    
    
   

    public class singleRules extends GameRules {

        public singleRules(GameRules rules) {
            this.Col = rules.Col;
            this.MinesNumber = rules.MinesNumber;
            this.Row = rules.Row;

            this.CountPlayers = 1;
            this.countShields=rules.countShields;

        }

        @Override
        public void setRules(GameRules rules) {
            super.setRules(rules);
            this.CountPlayers = 1;

        }

        @Override
        public PlayerStaute SetCurrentPlayerStaute(ResultMove result) {
            if (result.square.getSquareStaute() == SquareStaute.OpenMine && result.square.player.shildsNom == -1) {
                return PlayerStaute.Lose;
            }

            return PlayerStaute.Wait;

        }

        @Override
        public Player ChangeCurrentPlayer(Player[] Players, int index) {
            Currentplayer.Staute = PlayerStaute.Run;
            return Currentplayer;

        }

        @Override
        public int ChangeIndexCurrentPlayer(Player[] Players, int index) {
            if (Players[0].Staute == PlayerStaute.Lose) {
                return -1;
            }
            return 0;
        }

        @Override
        public void SetCountPlayers(int countPlayers) {
            this.CountPlayers = 1;
        }

        @Override
        public int wenScore() {
            int sum = 0;

            for (int i = 0; i < Row; i++) {
                for (int j = 0; j < Col; j++) {
                    if (grid.getSquare(i, j).getSquareStaute() == SquareStaute.Closed) {
                        sum++;
                    }
                    if (grid.getSquare(i, j).getSquareStaute() == SquareStaute.Closed && !grid.getSquare(i, j).IsMine()) {
                        return 0;
                    }
                }
            }
            Currentplayer.Staute = PlayerStaute.Win;
            return sum * 100;
        }

        @Override
        public void boom() {
            for (int i = 0; i < Row; i++) {
                for (int j = 0; j < Col; j++) {
                    if (grid.getSquare(i, j).IsMine() && grid.getSquare(i, j).getSquareStaute() != SquareStaute.Marked) {
                        grid.getSquare(i, j).staute = SquareStaute.OpenMine;
                    }
                    if (grid.getSquare(i, j).getSquareStaute() == SquareStaute.Marked && !grid.getSquare(i, j).IsMine()) {
                        grid.getSquare(i, j).staute = SquareStaute.MarkedFalse;
                    }
                }
            }

        }

        @Override
        public boolean endGame(int indexCurrentPlaye, Grid grid) {
            if (indexCurrentPlaye == -1) {
                indexCurrentPlayer = 0;
                return true;

            }
            return super.endGame(0, grid);
        }

        @Override
        public String ShowScore() {
            String s = new String();
            if (PlayerList[0].Score <= 10) {
                s = ("Playe 1 Score:" + Integer.toString(Currentplayer.Score));
            }
            if (PlayerList[0].Staute == PlayerStaute.Lose) {
                s = "Game Over\nScore: " + Integer.toString(Currentplayer.Score);
            }
            if (PlayerList[0].Staute == PlayerStaute.Win) {
                s = " win (: \nScore: " + Integer.toString(Currentplayer.Score);
            }
            if(PlayerList[0].shildsNom==-1)
             s+="\nplayer shields: 0";
            else
               s+="\nplayer shields: "+PlayerList[0].shildsNom;  
             
                 
            return s;
        }

    }

    public class multiRules extends GameRules {

        @Override
        public boolean endGame(int indexCurrentPlayer, Grid grid) {
            if (indexCurrentPlayer == -1) {
                return true;
            }
            SquareStaute staute;
            for (int i = 0; i < Row; i++) {
                for (int j = 0; j < Col; j++) {
                    staute = grid.getSquare(i, j).getSquareStaute();
                    if (!grid.getSquare(i, j).IsMine() && (staute == SquareStaute.Closed)/*|| staute == SquareStaute.Marked)*/) {
                        return false;
                    }
                }
            }
            return true;
        }

        public multiRules(int type, GameRules rules) {
            // super();
            this.Col = rules.Col;
            this.MinesNumber = rules.MinesNumber;
            this.Row = rules.Row;
            this.score = score;
            this.CountPlayers = 2;
            this.typePlayers = type;
            this.autoSheild=rules.autoSheild;
            this.timeRole=rules.timeRole;
            this.countShields=rules.countShields;
        }

        public void setMulti(int count, int typePlayer) {

            this.CountPlayers = count;
            this.typePlayers = typePlayer;
        }

        @Override
        public void setRules(GameRules rules) {
            super.setRules(rules);
            this.CountPlayers = 2;

        }

        @Override
        public PlayerStaute SetCurrentPlayerStaute(ResultMove result) {

            return PlayerStaute.Wait;
        }

        @Override
        public int ChangeIndexCurrentPlayer(Player[] Players, int index) {
            ++index;

            for (int i = index; i < CountPlayers; i++) {
                if (Players[i].Staute == PlayerStaute.Wait) {
                    return i;
                }
            }

            for (int i = 0; i < index; i++) {
                if (Players[i].Staute == PlayerStaute.Wait) {
                    return i;
                }
            }

            return -1;
        }

        @Override
        public Player ChangeCurrentPlayer(Player[] Players, int index) {

            Players[index].Staute = PlayerStaute.Run;
            return Players[index];

        }

        @Override
        public int wenScore() {

            return 0;
        }

        @Override
        public void boom() {
            for (int i = 0; i < Row; i++) {
                for (int j = 0; j < Col; j++) {
                    if (grid.getSquare(i, j).getSquareStaute() == SquareStaute.Marked && !grid.getSquare(i, j).IsMine()) {
                        grid.getSquare(i, j).staute = SquareStaute.MarkedFalse;
                    }
                }
            }

        }

        @Override
        public String ShowScore() {
            String s = new String();
            String s1 = new String();
            String s2 = new String();
            if (PlayerList[0].Score <= 10) {
                s = ("Playe 1 Score:" + Integer.toString(PlayerList[0].Score));
            }
            if (PlayerList[1].Score <= 10) {
                s += ("\t\tPlaye 2 Score:" + Integer.toString(PlayerList[1].Score));
            }
            if (gameStaute == GameStaute.over) {
                if (PlayerList[0].Score > PlayerList[1].Score) {
                    s = "\t\t\tPlayer 1 Win!!";
                    s += "\nPlaye 1 Score:" + Integer.toString(PlayerList[0].Score) + "\t\tPlaye 2 Score:" + Integer.toString(PlayerList[1].Score);
                    PlayerList[0].Staute = PlayerStaute.Win;
                } else if (PlayerList[1].Score > PlayerList[0].Score) {
                    s = "\t\t\tPlayer 2 Win!!";
                    s += "\nPlaye 1 Score:" + Integer.toString(PlayerList[0].Score) + "\t\tPlaye 2 Score:" + Integer.toString(PlayerList[1].Score);
                    PlayerList[1].Staute = PlayerStaute.Win;
                } else {
                    s = "draw ):";
                }
            }
            if(PlayerList[0].shildsNom==-1 && PlayerList[1].shildsNom!=-1 )
             s+="\nplayer1 shilds: 0"+"\tpalyer2 shields: "+PlayerList[1].shildsNom;
            else if(PlayerList[1].shildsNom==-1 && PlayerList[0].shildsNom!=-1)
                s+="\nplayer1 shilds"+PlayerList[0].shildsNom+"\tpalyer2 shields: 0"; 
            else if(PlayerList[0].shildsNom==-1 && PlayerList[1].shildsNom==-1)
                 s+="\nplayer1 shilds: 0"+"\tpalyer2 shields: 0"; 
            else
               s+="\nplayer1 shilds: "+PlayerList[0].shildsNom+"\tpalyer2 shields: "+PlayerList[1].shildsNom; 
            if(PlayerList[0].Staute==PlayerStaute.Run)
                s+="\n\t\tpalyer 1 play";
            if(PlayerList[1].Staute==PlayerStaute.Run)
                 s+="\n\t\tpalyer 2 play";
             s+="\nTimer: "+timer.getCounter();
            return s;
        }
    }

    ////////////////////////////////////////////////////////////
    public int countLoserPlayers() {
        int x = 0;
        for (int i = 0; i < PlayerList.length; i++) {
            if (PlayerList[i].Staute == PlayerStaute.Lose) {
                x++;
            }
        }
        return x;
    }

    //تهيئة اللعبة
    public void InitGame() {
        File f = new File(data);
        f.mkdirs();
        
          this.board = new scoreBoard();
        this.setName("Game");

        //تهيئة اللاعب
        this.gameStaute = GameStaute.run;

        this.grid = new Grid();
        this.grid.InitGrid(rules.Row, rules.Col, rules.MinesNumber, rules.countShields);

        PlayerList = new Player[rules.CountPlayers];
       // this.rules.timeRole = 8;
        this.timer.setMaxValue(rules.timeRole);
        this.timer.turnOn();
        
         //نهية الرقعة وضبط عدد الألغام والأبعاد من الرولز
    }

    int nomShields(ArrayList<ResultMove> results) {
        int x = 0;
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).square.shield && results.get(i).typaMove != TypeMove.Mark) {
                x++;
            }
        }
        return x;
    }

    //يقوم بأخذ حركة من اللاعب ويجعله يعيد اللعب طالما هناك خطأ ما ثم يطبق الحركة ويقوم بالتعديل المناسب 

    public void ApplyPlayerMove() {

        ArrayList<ResultMove> results = grid.ApplyPlayerMove(CurrentMove);
        listMoves.add(CurrentMove);
        Currentplayer.shildsNom += nomShields(results);
        Currentplayer.ChangeScore(rules.NewScore(results));
        Currentplayer.ChangeStaute(rules.SetCurrentPlayerStaute(results.get(0)));

    }

    public void changePlayer() {
        indexCurrentPlayer = rules.ChangeIndexCurrentPlayer(PlayerList, indexCurrentPlayer);
        if (rules.endGame(indexCurrentPlayer, grid)) {
            Currentplayer.ChangeScore(rules.wenScore());
            for (int i=0;i<rules.CountPlayers;i++)
                PlayerList[i].wenscor();
            gameStaute = GameStaute.over;
            rules.boom();
        } else {
            Currentplayer = rules.ChangeCurrentPlayer(PlayerList, indexCurrentPlayer);
            Currentplayer.interrupt();
        }
    }

    public void getNextMove() {
        CurrentMove = Currentplayer.GetPlayerMove(CurrentMove);
    }

    // يتأكد من صحة الحركة
    boolean AcceptPlayerMove() {
        if (CurrentMove == null) {
            return false;
        }
        return grid.AcceptPlayerMove(CurrentMove);


    }

    @Override
    public void run() {
        
        while(this.gameStaute == null)
        {
//            try {
//                //Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//            }
              System.out.println("dfwsdfsdf");
        }
        
        while (this.gameStaute == GameStaute.run) {
              try{
                 // System.out.println("while");
                  Thread.sleep(1000);
                  try{
                      CurrentMove = Currentplayer.GetPlayerMove(CurrentMove);
                      if (this.AcceptPlayerMove()) {
                          this.ApplyPlayerMove();
                          System.out.println("apply...");
                          this.changePlayer();
                      }
                  }catch(Exception ex){System.out.println("ex..............");}
                  
                  
                  
              }catch(InterruptedException ex){Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);}
                
            

        }
    }

    public GameStaute getGameStaute() {
        return this.gameStaute;
    }

    public abstract void restart();

}
