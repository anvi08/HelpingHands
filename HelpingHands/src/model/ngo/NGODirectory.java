/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.ngo;

import java.util.ArrayList;
import model.causes.Cause;

/**
 *
 * @author abhis
 */
public class NGODirectory {
    public ArrayList<NGO> allNGO;  
    private NGO ngo;
    public NGODirectory(NGO ngo){
        
        this.ngo = ngo;
            
    }

    public ArrayList<NGO> getAllNGO() {
        return allNGO;
    }

    public void setAllNGO(ArrayList<NGO> allNGO) {
        this.allNGO = allNGO;
    }

    public NGO getNgo() {
        return ngo;
    }

    public void setNgo(NGO ngo) {
        this.ngo = ngo;
    }
    
    
    
}
