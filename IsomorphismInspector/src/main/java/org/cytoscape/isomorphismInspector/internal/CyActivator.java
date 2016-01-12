package org.cytoscape.isomorphismInspector.internal;

import java.util.Properties;
import org.cytoscape.app.CyAppAdapter;
import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.events.NetworkAddedListener;
import org.cytoscape.model.events.NetworkDestroyedListener;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.osgi.framework.BundleContext;

/**
 * @author SrikanthB
 *
 */


public class CyActivator extends AbstractCyActivator {
    private static CyAppAdapter appAdapter;
    private static CyEventHelper eventHelper;
    public static CyApplicationManager cyApplicationManager;
    public CySwingApplication cyDesktopService;
    public CyServiceRegistrar cyServiceRegistrar;
    public MenuAction menuaction;
    public static CyNetworkFactory networkFactory;
    public static CyNetworkManager networkManager;
    public static CyNetworkViewFactory networkViewFactory;
    public static CyNetworkViewManager networkViewManager;
    public static CySwingAppAdapter adapter;
    
    public CyActivator() {
        super();
    }
    
    public void start(BundleContext context) throws Exception {
        String version = new String(" 1.0");
        this.appAdapter = getService(context, CyAppAdapter.class);
        this.networkViewManager = getService(context, CyNetworkViewManager.class);
        this.networkViewFactory = getService(context, CyNetworkViewFactory.class);
        this.networkFactory = getService(context, CyNetworkFactory.class);
        this.networkManager = getService(context, CyNetworkManager.class);
        this.cyApplicationManager = getService(context, CyApplicationManager.class);
        this.cyDesktopService = getService(context, CySwingApplication.class);
        this.cyServiceRegistrar = getService(context, CyServiceRegistrar.class);
        this.eventHelper = getService(context, CyEventHelper.class);
        this.adapter = getService(context,CySwingAppAdapter.class);
        menuaction = new MenuAction(cyApplicationManager, "Isomorphic ? " + version, this);
        
        NetworkEventsListener networkEventsListener = new NetworkEventsListener();
        registerService(context,networkEventsListener,NetworkAddedListener.class, new Properties());
        registerService(context,networkEventsListener,NetworkDestroyedListener.class, new Properties());       
        registerAllServices(context, menuaction, new Properties());
    }

    public CyServiceRegistrar getcyServiceRegistrar() {
        return cyServiceRegistrar;
    }

    public static CyApplicationManager getcyApplicationManager() {
        return cyApplicationManager;
    }
    
    public static CyNetworkManager getcyNetworkManager(){
        return networkManager;
    } 
    
    
    public CySwingApplication getcytoscapeDesktopService() {
        return cyDesktopService;
    }
    
    public MenuAction getmenuaction() {
        return menuaction;
    }
    
    public static CyAppAdapter getCyAppAdapter(){
        return appAdapter;
    }
    
    public static CyEventHelper getCyEventHelper(){
        return eventHelper;
    }
    
    public static CyNetworkViewManager getNetworkViewManager(){
        return networkViewManager;
    }

}