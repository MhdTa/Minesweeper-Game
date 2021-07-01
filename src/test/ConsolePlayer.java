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
public class ConsolePlayer extends Player{

    ConsolePlayer(String name) {
        
        super(name);
        
    }

    
    
    
    @Override
    public PlayerMove GetPlayerMove(PlayerMove mov) 
    {
        
        /*int row = 0, col = 0 ,typeMove = 0;
        Scanner input = new Scanner(System.in);
        boolean flage = false;
        do
        {
            try
            {
                row = input.nextInt();
                col = input.nextInt();
                typeMove = input.nextInt();
                flage = true;
            }
            catch(Exception ex)
            {
                System.out.println("");  
            }
        }while(!flage);
             
        Square square = new Square(row-1, col-1);
        square.player = this;
        return new PlayerMove(this, square, (typeMove == 0)?TypeMove.Reveal:TypeMove.Mark);*/
        PlayerMove move = null;
        if(this.currentMove != null)
        {
            move = new PlayerMove(this.currentMove.player,this.currentMove.square,this.currentMove.typeMove);
            this.currentMove = null;
        }
        return move;
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
