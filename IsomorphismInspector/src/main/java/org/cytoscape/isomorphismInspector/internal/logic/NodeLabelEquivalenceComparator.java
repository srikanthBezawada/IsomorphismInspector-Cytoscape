package org.cytoscape.isomorphismInspector.internal.logic;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.jgrapht.Graph;
import org.jgrapht.experimental.equivalence.EquivalenceComparator;

/**
 * @author SrikanthB
 *
 */

public class NodeLabelEquivalenceComparator implements EquivalenceComparator<CyNode, Graph<CyNode, CyEdge>> {

    @Override
    public boolean equivalenceCompare(CyNode n1, CyNode n2, Graph<CyNode, CyEdge> g1, Graph<CyNode, CyEdge> g2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // compare n1 and n2 , return
        return n1.getSUID() == n2.getSUID();
    }

    @Override
    public int equivalenceHashcode(CyNode n, Graph<CyNode, CyEdge> g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // return what ?
        return n.hashCode();
    }
    
}
