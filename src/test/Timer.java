/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OMAR
 */
 public class Timer extends Thread implements Serializable
    {
        private int counter;
        private int maxValue;
        boolean inable;
        boolean isAlive;
        
        Timer()
        {
            this.isAlive = true;
        }
        
        public void turnOn()
        {
            this.inable = true;
            this.interrupt();
        }
        
        public void setMaxValue(int maxValue)
        {
            this.maxValue = maxValue;
        }
        
        public void reset()
        {
            this.counter = this.maxValue;
        }
        
        
        public int getCounter()
        {
            return this.counter;
        }
        
        @Override
       public void run()
       {
           while(this.isAlive)
           {
               while(!this.inable)
               {
                   try {
                       Thread.sleep(10000000);
                   } catch (InterruptedException ex) {
                      
                   }
               }
               try{
                   Thread.sleep(1000);
                   this.counter--;
               System.out.println(this.counter);
               }catch(InterruptedException ex){
               this.counter = this.maxValue;
               }
               
           }
       }
        
    }
