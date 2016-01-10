package org.cytoscape.isomorphismInspector.internal.logic;

import java.util.Comparator;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.jgrapht.Graph;

/**
 * @author SrikanthB
 *
 */

public class EdgeLabelEquivalenceComparator implements Comparator<CyEdge> {

    CyNetwork network1;
    CyNetwork network2;
    String label1;
    String label2;
    
    public EdgeLabelEquivalenceComparator(CyNetwork network1, String label1, CyNetwork network2, String label2) {
        this.network1 = network1;
        this.network2 = network2;
        this.label1 = label1;
        this.label2 = label2;
    }

    public int equivalenceCompare(CyEdge e1, CyEdge e2, Graph<CyNode, CyEdge> g1, Graph<CyNode, CyEdge> g2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // compare e1 and e2 , return (check source and target of edges)
        // get the label from network1 and network2
        CyTable edgeTable1 = network1.getDefaultEdgeTable();
        CyRow row1 = edgeTable1.getRow(e1.getSUID());
        Object o1 = row1.getRaw(label1);
        
        CyTable edgeTable2= network2.getDefaultEdgeTable();
        CyRow row2 = edgeTable2.getRow(e2.getSUID());
        Object o2 = row2.getRaw(label2);
        
        if(o1.getClass() != o2.getClass()){
            System.out.println("Not supported yet. Both Labels should be of same type.");
            throw new UnsupportedOperationException("Not supported yet. Both Labels should be of same type.");
        }
        if(o1 instanceof Number){
            Number n1 = (Number)o1;
            Number n2 = (Number)o2;
            // TODO: what if the label is double and the labels are 1.4 and 1.3
            return n1.intValue()-n2.intValue();
        }
        else if(o1 instanceof String){
            String s1 = (String)o1;
            String s2 = (String)o2;
            return s1.compareTo(s2);
        }
        else{
            System.out.println("Not supported yet. Labels other than string and Number are not supported yet");
            throw new UnsupportedOperationException("Not supported yet. Labels other than string and Number are not supported yet");
        }
    }

    
    public int equivalenceHashcode(CyEdge e, Graph<CyNode, CyEdge> g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // return what ?
        return e.hashCode();
    }

    @Override
    public int compare(CyEdge o1, CyEdge o2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return equivalenceCompare(o1, o2, null, null);
    }
    
}
