/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author OMAR
 */
class Record implements Serializable
    {
      
        int row,col;
        int mines;
        int shields;
        String path;
        
        String getGridSize()
        {
            return row + " * " + col;
            
        }
        
    }
