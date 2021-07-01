/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;

/**
 *
 * @author hp
 */
public class ResultMove implements Serializable{
    
    TypeMove typaMove;
    Square square;
    
    public ResultMove(Square square, TypeMove type)
    {
        this.square = square;
        this.typaMove = type;
    }
    
}
