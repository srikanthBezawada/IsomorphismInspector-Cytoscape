package org.cytoscape.isomorphismInspector.internal;

import javax.swing.DefaultComboBoxModel;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.events.NetworkAddedEvent;
import org.cytoscape.model.events.NetworkAddedListener;
import org.cytoscape.model.events.NetworkDestroyedEvent;
import org.cytoscape.model.events.NetworkDestroyedListener;

/**
 * @author SrikanthB
 *
 */


public class NetworkEventsListener implements NetworkAddedListener, NetworkDestroyedListener{
    public void handleEvent(NetworkAddedEvent e){
        CyNetwork net = e.getNetwork();
        String title = net.getRow(net).get("name", String.class);
        IsoUI menu = IsoCore.getTiDieStartMenu();
        ((DefaultComboBoxModel)menu.networkComboBox1.getModel()).addElement(title);
        ((DefaultComboBoxModel)menu.networkComboBox2.getModel()).addElement(title);         
    }
    
    public void handleEvent(NetworkDestroyedEvent e){
        IsoUI menu = IsoCore.getTiDieStartMenu();
        menu.updateNetworkList();
    }
}