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

public class NodeLabelEquivalenceComparator implements Comparator<CyNode> {

    CyNetwork network1;
    CyNetwork network2;
    String label1;
    String label2;
    
    public NodeLabelEquivalenceComparator(CyNetwork network1, String label1, CyNetwork network2, String label2) {
        this.network1 = network1;
        this.network2 = network2;
        this.label1 = label1;
        this.label2 = label2;
    }
    
    public int equivalenceCompare(CyNode n1, CyNode n2, Graph<CyNode, CyEdge> g1, Graph<CyNode, CyEdge> g2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // compare n1 and n2 , return
        CyTable nodeTable1 = network1.getDefaultNodeTable();
        CyRow row1 = nodeTable1.getRow(n1.getSUID());
        Object o1 = row1.getRaw(label1);
        
        CyTable nodeTable2 = network2.getDefaultNodeTable();
        CyRow row2 = nodeTable2.getRow(n2.getSUID());
        Object o2 = row2.getRaw(label2);
        
        if(o1.getClass() != o2.getClass()){
            System.out.println("Not supported yet. Both Labels should be of same type.");
            throw new UnsupportedOperationException("Not supported yet. Both Labels should be of same type.");
        }
        if(o1 instanceof Number){
            Number nn1 = (Number)o1;
            Number nn2 = (Number)o2;
            // TODO: what if the label is double and the labels are 1.4 and 1.3
            return nn1.intValue()-nn2.intValue();
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

    public int equivalenceHashcode(CyNode n, Graph<CyNode, CyEdge> g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // return what ?
        return n.hashCode();
    }

    @Override
    public int compare(CyNode o1, CyNode o2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return equivalenceCompare(o1, o2, null, null);
    }
    
}
