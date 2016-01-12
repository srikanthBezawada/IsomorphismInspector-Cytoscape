/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cytoscape.isomorphismInspector.internal.results;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.isomorphismInspector.internal.CyActivator;
import org.cytoscape.isomorphismInspector.internal.IsoCore;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;
import org.jgrapht.GraphMapping;

/**
 *
 * @author fshaik
 */
public class ResultsGUI extends javax.swing.JPanel implements CytoPanelComponent{

    private IsoCore core;
    private GraphMapping<CyNode, CyEdge> mapping;
    private CyNetwork net1;
    private CyNetwork net2;
    private JTable tbl;
    /**
     * Creates new form ResultsGUI
     */
    public ResultsGUI(IsoCore core) {
        this.core=core;
        initComponents();
    }
    
    public void setEnabled(GraphMapping<CyNode, CyEdge> mapping, CyNetwork net1, CyNetwork net2){
        super.setVisible(false);
        this.mapping = mapping;
        this.net1 = net1;
        this.net2 = net2;
        populateResults();
        super.setVisible(true);
    }
    
    private void populateResults(){
        // create object of table and table model
        tbl = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }   
        };

        // add header of the table
        // get the network names
        String header[] = new String[] { net1.getRow(net1).get(CyNetwork.NAME, String.class),
           net2.getRow(net2).get(CyNetwork.NAME, String.class) };

        // add header in table model     
        dtm.setColumnIdentifiers(header);
            //set model into the table object
            tbl.setModel(dtm);

        // add row dynamically into the table
        List<CyNode> net1NodeList = net1.getNodeList();
        for (CyNode n1: net1NodeList) {
            CyNode n2 = mapping.getVertexCorrespondence(n1, true);
            dtm.addRow(new Object[] { net1.getRow(n1).get(CyNetwork.NAME, String.class)
            , net2.getRow(n2).get(CyNetwork.NAME, String.class) });
        }
       
        // attach listener
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        this.jScrollPane2.setViewportView(tbl);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Node mapping from Network 1 to Network 2"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        jButton1.setText("Close Results Panel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("An Isomorphic mapping from Network 1 to Network 2");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        core.closeCurrentResultPanel(this);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getComponent() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this;
    }

    @Override
    public CytoPanelName getCytoPanelName() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return CytoPanelName.EAST;
    }

    @Override
    public String getTitle() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return CyActivator.APP_NAME+" Results";
    }

    @Override
    public Icon getIcon() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    
    public void setResult(String s){
        this.jLabel2.setText(s);
    }

    private void tableMouseReleased(java.awt.event.MouseEvent evt){
        int[] selections = tbl.getSelectedRows();
        CyTable nodeTable1 = net1.getDefaultNodeTable();
        for(CyNode n1 : net1.getNodeList()){	
            CyRow row = nodeTable1.getRow(n1.getSUID());
            row.set("selected", false);
        }
        CyTable nodeTable2 = net2.getDefaultNodeTable();
        for(CyNode n2 : net2.getNodeList()){	
            CyRow row = nodeTable2.getRow(n2.getSUID());
            row.set("selected", false);
        }
        // select the correspoding node in the net1
        // select the correspoding node in the net2
        for(int i=0;i<selections.length; i++){
            CyNode node1 = net1.getNodeList().get(selections[i]);
            nodeTable1.getRow(node1.getSUID()).set("selected", true);
            CyNode node2 = mapping.getVertexCorrespondence(node1, true);
            nodeTable2.getRow(node2.getSUID()).set("selected", true);
        }
        //update views
        Collection<CyNetworkView> c1 = CyActivator.getNetworkViewManager().getNetworkViews(net1);
        Iterator<CyNetworkView> it1 = c1.iterator();
        while(it1.hasNext())
            it1.next().updateView();
        Collection<CyNetworkView> c2 = CyActivator.getNetworkViewManager().getNetworkViews(net2);
        Iterator<CyNetworkView> it2 = c2.iterator();
        while(it2.hasNext())
            it2.next().updateView();
        
    }
    
}
