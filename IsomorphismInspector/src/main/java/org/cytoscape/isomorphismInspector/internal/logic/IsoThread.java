package org.cytoscape.isomorphismInspector.internal.logic;

import java.util.List;
import org.cytoscape.isomorphismInspector.internal.IsoUI;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.experimental.isomorphism.AdaptiveIsomorphismInspectorFactory;
import org.jgrapht.experimental.isomorphism.GraphIsomorphismInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

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
//        DefaultDirectedGraph<CyNode, CyEdge> g1 = new DefaultDirectedGraph<CyNode, CyEdge>(CyEdge.class);//SimpleGraph<CyNode, CyEdge>(CyEdge.class)
        UndirectedGraph<CyNode, CyEdge> g1 = new SimpleGraph<CyNode, CyEdge>(CyEdge.class);//SimpleGraph<CyNode, CyEdge>(CyEdge.class)
        /*
          1) Change simple graph so that you need not remove self-loops later      
          2) Edge type <Object> to <CyEdge>, and edge factory fails
        */        
        List<CyNode> nodeList1 = network1.getNodeList();
        List<CyEdge> edgeList1 = network1.getEdgeList();
        for(CyNode n : nodeList1){
            g1.addVertex(n);
        }
        for(CyEdge e : edgeList1){
            if(e.getSource().equals(e.getTarget())){
                continue; // removing self-loops
            }
            g1.addEdge(e.getSource(), e.getTarget(),e);
        }
        
        UndirectedGraph<CyNode, CyEdge> g2 = new SimpleGraph<CyNode, CyEdge>(CyEdge.class);
        List<CyNode> nodeList2 = network2.getNodeList();
        List<CyEdge> edgeList2 = network2.getEdgeList();
        for(CyNode n : nodeList2){
            g2.addVertex(n);
        }
        for(CyEdge e : edgeList2){
            if(e.getSource().equals(e.getTarget())){
                continue; // removing self-loops
            }
            g2.addEdge(e.getSource(), e.getTarget(), e);
        }
        
        //GraphIsomorphismInspector iso = AdaptiveIsomorphismInspectorFactory.createIsomorphismInspector(g1, g2, null, null);// dafault comparators
        GraphIsomorphismInspector iso = AdaptiveIsomorphismInspectorFactory.createIsomorphismInspector(g1, g2, null, null);
        boolean isoResult = iso.isIsomorphic();
  
        if (isoResult) {
            System.out.println("Graphs are isomorphic.");
        } else {
            System.out.println("Graphs are NOT isomorphic.");
        }
        menu.endComputation();
    }
    
}
