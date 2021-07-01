/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author hp
 */
public enum Score {
    
   empty(10), mine(-25),markMine(5),markEmpty(-1),shield(25);
int value;
    private Score(int value) {
        this.value = value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public int getValue()
    {
        return this.value;
    }
   
}
