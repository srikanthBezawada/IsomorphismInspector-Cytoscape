package org.cytoscape.isomorphismInspector.internal;


import java.awt.event.ActionEvent;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;

/**
 * @author SrikanthB
 *
 */

public class MenuAction extends AbstractCyAction {
    public CyApplicationManager cyApplicationManager;
    public CySwingApplication cyDesktopService;
    public CyActivator cyactivator;
    public MenuAction(CyApplicationManager cyApplicationManager, final String menuTitle, CyActivator cyactivator) {
        super(menuTitle, cyApplicationManager, null, null);
        setPreferredMenu("Apps");
        this.cyactivator = cyactivator;
        this.cyApplicationManager = cyApplicationManager;
        this.cyDesktopService = cyactivator.getcytoscapeDesktopService();
    }

    public void actionPerformed(ActionEvent e) {
        IsoCore isocore = new IsoCore(cyactivator);
    }
}
