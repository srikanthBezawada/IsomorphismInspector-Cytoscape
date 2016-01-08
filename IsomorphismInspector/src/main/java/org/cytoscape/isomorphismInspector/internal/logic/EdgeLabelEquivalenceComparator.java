package org.cytoscape.isomorphismInspector.internal.logic;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.jgrapht.Graph;
import org.jgrapht.experimental.equivalence.EquivalenceComparator;

/**
 * @author SrikanthB
 *
 */

public class EdgeLabelEquivalenceComparator implements EquivalenceComparator<CyEdge, Graph<CyNode, CyEdge>> {

    @Override
    public boolean equivalenceCompare(CyEdge e1, CyEdge e2, Graph<CyNode, CyEdge> g1, Graph<CyNode, CyEdge> g2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // compare e1 and e2 , return (check source and target of edges)
        return e1.getSUID() == e2.getSUID();
    }

    @Override
    public int equivalenceHashcode(CyEdge e, Graph<CyNode, CyEdge> g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // return what ?
        return e.hashCode();
    }
    
}
