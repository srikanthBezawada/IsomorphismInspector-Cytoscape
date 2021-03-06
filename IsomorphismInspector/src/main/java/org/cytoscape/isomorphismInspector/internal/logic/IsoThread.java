package org.cytoscape.isomorphismInspector.internal.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.cytoscape.isomorphismInspector.internal.IsoUI;
import org.cytoscape.isomorphismInspector.internal.results.ResultsGUI;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.jgrapht.GraphMapping;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
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
    private int mappingcount=0;
    
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
        long startTime = System.currentTimeMillis();
        menu.startComputation();
        UndirectedGraph<CyNode, CyEdge> g1 = new SimpleGraph<CyNode, CyEdge>(CyEdge.class);   
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
        
        VF2SubgraphIsomorphismInspector<CyNode, CyEdge> vf2 =
            new VF2SubgraphIsomorphismInspector<CyNode, CyEdge>(g1, g2, nodeComp, edgeComp);
 
        System.out.println();
        System.out.println("------------------Graph Isomorphism------------------");
        if (vf2.isomorphismExists()) {
            menu.endComputation("<html>Graphs are isomorphic.<br><html>");
            System.out.println("Graphs are isomorphic.");
            mappingcount=1;
            Iterator<GraphMapping<CyNode, CyEdge>> iter = vf2.getMappings();
//            System.out.println("Priting an isomorphic mapping of the graphs");
            GraphMapping<CyNode, CyEdge> mapping = iter.next();
            ResultsGUI resultsPanel = menu.isocore.createResultsPanel(mapping, network1, network2);
//            System.out.println(mapping);
//            System.out.println();
            System.out.println("Counting number of isomorphic mappings...");
            CountMappings countThread = new CountMappings(iter, network1, network2, resultsPanel);
            countThread.start();
            try {
                // take attribute from user
//                String timeToWait = JOptionPane.showInputDialog(null, 
//                        "Enter time to wait in secnds to compute total number of mappings");
                // waiting 5 seconds to return from the counting thread
                countThread.join(5000);
                if(countThread.isAlive()){
                    countThread.stopalgo();
                    resultsPanel.setResult("There are ATLEAST "+mappingcount+" number of isomorphic mappings");
                    System.out.println("There are ATLEAST ["+mappingcount+"] number of isomorphic mappings");
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(IsoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            menu.endComputation("<html>Graphs are NOT isomorphic.<br><html>");
            System.out.println("Graphs are NOT isomorphic.");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to execute (ms): "+(endTime-startTime));
        System.out.println("------------------Graph Isomorphism------------------");
        
    }
    
    private class CountMappings extends Thread{
        boolean stop = false;
        Iterator<GraphMapping<CyNode, CyEdge>> iter;
        ResultsGUI resultsPanel;
        CyNetwork net1;
        CyNetwork net2;
        
        public CountMappings(Iterator<GraphMapping<CyNode, CyEdge>> iter, CyNetwork net1, 
                CyNetwork net2, ResultsGUI resultsPanel) {
            this.iter = iter;
            this.resultsPanel = resultsPanel;
            this.net1 = net1;
            this.net2 = net2;
        }
        
        @Override
        public void run(){
            while(iter.hasNext()){
                if(stop)
                    return;
                mappingcount++;
                GraphMapping<CyNode, CyEdge> mapping = iter.next();
                //populate table
                DefaultTableModel dtm = (DefaultTableModel) resultsPanel.getTable().getModel();
                
                // add column dynamically into the table
                List<CyNode> net1NodeList = net1.getNodeList();
                List<ResultsGUI.cellData> data = new ArrayList<ResultsGUI.cellData>();
                for (CyNode n1: net1NodeList) {
                    CyNode n2 = mapping.getVertexCorrespondence(n1, true);
                    if(n2 != null){
                        data.add(new ResultsGUI.cellData(net1, net2, n1, n2));
                    }
                }
                dtm.addColumn("Mapping "+ mappingcount, data.toArray());
                if(stop)
                    return;
            }
            if(stop)
                return;
            resultsPanel.setResult("Number of isomorphisms found = "+mappingcount);
            System.out.println("There are ["+mappingcount+"] number of isomorphic mappings");
            System.out.println("------------------Graph Isomorphism------------------");
        }
        
        public void stopalgo(){
            stop = true;
        }
    }
}
