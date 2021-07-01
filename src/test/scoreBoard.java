/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author OMAR
 */
public class scoreBoard implements Serializable {
    ArrayList<Record> record;

    public scoreBoard() {
          record=new ArrayList<Record>();

    }
    public void addRecord(Record r)
    {
        record.add(r);
    }
            
    
}
