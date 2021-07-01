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
public class illegalRulesExeption extends Exception{
    
    String message = new String();
    
    public illegalRulesExeption(String message)
    {
        this.message = message;
    }
    
    @Override
    public String toString()
    {
        return this.message;
    }
    
}
