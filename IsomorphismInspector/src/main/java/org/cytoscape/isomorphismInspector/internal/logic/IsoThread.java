package org.cytoscape.isomorphismInspector.internal.logic;

import org.cytoscape.isomorphismInspector.internal.IsoUI;
import org.cytoscape.model.CyNetwork;

/**
 * @author SrikanthB
 *
 */

public class IsoThread extends Thread{
    public CyNetwork network1;
    public CyNetwork network2;
    IsoUI menu;    
    
    public IsoThread(IsoUI menu, CyNetwork network1, CyNetwork network2){
        this.menu = menu;
        this.network1 = network1;
        this.network2 = network2;
    }
   
    public void run(){
        menu.startComputation();
        
        
        
        
        
        menu.endComputation();
    }
    
}
