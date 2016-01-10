package org.cytoscape.isomorphismInspector.internal.logic;

import java.util.Iterator;
import java.util.List;
import org.cytoscape.isomorphismInspector.internal.IsoUI;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.jgrapht.GraphMapping;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.isomorphism.VF2GraphIsomorphismInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

/**
 * @author SrikanthB
 *
 */

public class IsoThread extends Thread{
    public CyNetwork network1;
    public CyNetwork network2;
    private String nodelabel1;
    private String edgelabel1;
    private String nodelabel2;
    private String edgelabel2;
    
    IsoUI menu;    
    
    public IsoThread(IsoUI menu, CyNetwork network1, String nodelabel1, String edgelabel1, CyNetwork network2, String nodelabel2, String edgelabel2){
        this.menu = menu;
        this.network1 = network1;
        this.network2 = network2;
        this.nodelabel1 = nodelabel1;
        this.edgelabel1 = edgelabel1;
        this.nodelabel2 = nodelabel2;
        this.edgelabel2 = edgelabel2;
    }
   
    @Override
    public void run(){
        menu.startComputation();
//        DefaultDirectedGraph<CyNode, CyEdge> g1 = new DefaultDirectedGraph<CyNode, CyEdge>(CyEdge.class);//SimpleGraph<CyNode, CyEdge>(CyEdge.class)
        UndirectedGraph<CyNode, CyEdge> g1 = new SimpleGraph<CyNode, CyEdge>(CyEdge.class);//SimpleGraph<CyNode, CyEdge>(CyEdge.class)
        
        /* i GUESS WE NEED TO USE BELOW CODE FOR DIRECTED GRAPHS
        DefaultDirectedGraph<Integer, DefaultEdge> g2 =
            new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
        */
        
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
        // for null ; both nodelabel1 and nodelabel2 should be none. only one cant be null
        NodeLabelEquivalenceComparator nodeComp;
        EdgeLabelEquivalenceComparator edgeComp;
        if(nodelabel1.equals("None") && nodelabel2.equals("None"))
            nodeComp = null;
        else if(nodelabel1.equals("None") || nodelabel2.equals("None")){
            menu.endComputation("<html>Error! Not supported.<br>Specify both 'Node Label 1' and 'Node Label 2' or both as 'None'<br><html>");
            System.out.println("Not supported. Specify both 'Node Label 1' and 'Node Label 2' or both as 'None'");
            throw new UnsupportedOperationException("Not supported. Specify both 'Node Label 1' and 'Node Label 2' or both as 'None'");
        }
        else
            nodeComp = new NodeLabelEquivalenceComparator(network1, nodelabel1, network2, nodelabel2); 
        // same with edge comp
        if(edgelabel1.equals("None") && edgelabel2.equals("None"))
            edgeComp = null;
        else if(edgelabel1.equals("None") || edgelabel2.equals("None")){
            menu.endComputation("<html>Error! Not supported.<br>Specify both 'Edge Label 1' and 'Edge Label 2' or both as 'None'<br><html>");
            System.out.println("Not supported. Specify both 'Edge Label 1' and 'Edge Label 2' or both as 'None'");
            throw new UnsupportedOperationException("Not supported. Specify both 'Edge Label 1' and 'Edge Label 2' or both as 'None'");
        }
        else
            edgeComp = new EdgeLabelEquivalenceComparator(network1, edgelabel1, network2, edgelabel2);
        
        VF2GraphIsomorphismInspector<CyNode, CyEdge> vf2 =
            new VF2GraphIsomorphismInspector<CyNode, CyEdge>(g1, g2, nodeComp, edgeComp);        
 
        System.out.println();
        System.out.println("------------------Graph Isomorphism------------------");
        if (vf2.isomorphismExists()) {
            menu.endComputation("<html>Graphs are isomorphic.<br><html>");
            System.out.println("Graphs are isomorphic.");
        } else {
            menu.endComputation("<html>Graphs are NOT isomorphic.<br><html>");
            System.out.println("Graphs are NOT isomorphic.");
        }
        System.out.println("------------------Graph Isomorphism------------------");
        
    }
    
}
