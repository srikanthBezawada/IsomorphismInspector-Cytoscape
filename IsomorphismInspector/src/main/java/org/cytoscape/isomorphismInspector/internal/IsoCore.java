package org.cytoscape.isomorphismInspector.internal;

import java.util.List;
import java.util.Properties;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.application.swing.CytoPanelState;
import org.cytoscape.isomorphismInspector.internal.results.ResultsGUI;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.view.model.CyNetworkView;
import org.jgrapht.GraphMapping;

/**
 * @author SrikanthB
 *
 */

public class IsoCore {
    public CyNetwork network;
    public CyNetworkView view;
    public CyApplicationManager cyApplicationManager;
    public CySwingApplication cyDesktopService;
    public CyServiceRegistrar cyServiceRegistrar;
    public CyActivator cyactivator;
    private static IsoUI startmenu;
    public IsoCore(CyActivator cyactivator){
        this.cyactivator = cyactivator;
        this.cyApplicationManager = cyactivator.cyApplicationManager;
        this.cyDesktopService = cyactivator.cyDesktopService;
        this.cyServiceRegistrar = cyactivator.cyServiceRegistrar;
        startmenu = createIsoStartMenu();
        updatecurrentnetwork();
    }
    public void updatecurrentnetwork() {
            //get the network view object
        if (view == null) {
            view = null;
            network = null;
        }
        else {
            view = cyApplicationManager.getCurrentNetworkView();
            //get the network object; this contains the graph  
            network = view.getModel();
        }
    }

    public void closecore() {
        network = null;
        view = null;
    }

    public IsoUI createIsoStartMenu() {
        startmenu = new IsoUI(cyactivator, this);
        cyServiceRegistrar.registerService(startmenu, CytoPanelComponent.class, new Properties());
        CytoPanel cytopanelwest = cyDesktopService.getCytoPanel(CytoPanelName.WEST);
        int index = cytopanelwest.indexOfComponent(startmenu);
        cytopanelwest.setSelectedIndex(index);
        return startmenu;
    }

    public void closeIsoStartMenu(IsoUI menu) {
        cyServiceRegistrar.unregisterService(menu, CytoPanelComponent.class);
    }

   
    public CyApplicationManager getCyApplicationManager() {
        return this.cyApplicationManager;
    }

    public CySwingApplication getCyDesktopService() {
        return this.cyDesktopService;
    }
    
    public static IsoUI getTiDieStartMenu(){
        return startmenu;
    }
    
    public ResultsGUI createResultsPanel(GraphMapping<CyNode, CyEdge> mapping, CyNetwork net1, CyNetwork net2) {
		ResultsGUI resultsPanel = new ResultsGUI(this);
		cyServiceRegistrar.registerService(resultsPanel, CytoPanelComponent.class, new Properties());
		CytoPanel panelEast = cyDesktopService.getCytoPanel(CytoPanelName.EAST);
		panelEast.setState(CytoPanelState.DOCK);
		panelEast.setSelectedIndex(panelEast.indexOfComponent(resultsPanel));
//		visualizers.add(visualizer);
		resultsPanel.setEnabled(mapping, net1, net2);
                return resultsPanel;
	}
    
    public void closeCurrentResultPanel(ResultsGUI resultPanel) {
        cyServiceRegistrar.unregisterService(resultPanel, CytoPanelComponent.class);
    }
    
}
