package org.cytoscape.isomorphismInspector.internal;

import java.awt.Component;
import java.awt.event.ItemListener;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.isomorphismInspector.internal.logic.IsoThread;
import org.cytoscape.model.CyNetwork;

/**
 * @author SrikanthB
 *
 */


public class IsoUI extends javax.swing.JPanel implements CytoPanelComponent{

    /**
     * Creates new form IsoUI
     */
    private IsoCore isocore;
    public IsoThread logicThread;
    CyApplicationManager cyApplicationManager;
    CySwingApplication cyDesktopService;
    public CyActivator cyactivator;
    public IsoUI(CyActivator cyactivator, IsoCore isocore) {
        initComponents();
        this.cyactivator = cyactivator;
        this.isocore = isocore;
        cyApplicationManager = isocore.getCyApplicationManager();
        cyDesktopService = isocore.getCyDesktopService();
        updateNetworkList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headingLabel = new javax.swing.JLabel();
        networkPanel2 = new javax.swing.JPanel();
        netVariable1 = new javax.swing.JLabel();
        networkComboBox2 = new javax.swing.JComboBox();
        startB = new javax.swing.JButton();
        helpExitPanel = new javax.swing.JPanel();
        helpB = new javax.swing.JButton();
        exitB = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusBar = new javax.swing.JProgressBar();
        statusLabel = new javax.swing.JLabel();
        networkPanel1 = new javax.swing.JPanel();
        netVariable2 = new javax.swing.JLabel();
        networkComboBox1 = new javax.swing.JComboBox();

        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        headingLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        headingLabel.setForeground(new java.awt.Color(255, 0, 51));
        headingLabel.setText("IsomorphismChecker");

        networkPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Select second network"));

        netVariable1.setText("Network 2");

        javax.swing.GroupLayout networkPanel2Layout = new javax.swing.GroupLayout(networkPanel2);
        networkPanel2.setLayout(networkPanel2Layout);
        networkPanel2Layout.setHorizontalGroup(
            networkPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(netVariable1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(networkComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        networkPanel2Layout.setVerticalGroup(
            networkPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(networkPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(netVariable1)
                    .addComponent(networkComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        startB.setText("Find if selected networks are isomorphic");
        startB.setToolTipText("");
        startB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        startB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBActionPerformed(evt);
            }
        });

        helpExitPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        helpB.setForeground(new java.awt.Color(0, 200, 0));
        helpB.setText("Help");
        helpB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpBActionPerformed(evt);
            }
        });

        exitB.setForeground(new java.awt.Color(200, 0, 0));
        exitB.setText("Exit");
        exitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout helpExitPanelLayout = new javax.swing.GroupLayout(helpExitPanel);
        helpExitPanel.setLayout(helpExitPanelLayout);
        helpExitPanelLayout.setHorizontalGroup(
            helpExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpExitPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(helpB, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(exitB, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        helpExitPanelLayout.setVerticalGroup(
            helpExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, helpExitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(helpExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(helpB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        statusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Status bar"));

        statusLabel.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        statusLabel.setText("status");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        networkPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Select first network"));

        netVariable2.setText("Network 1");

        javax.swing.GroupLayout networkPanel1Layout = new javax.swing.GroupLayout(networkPanel1);
        networkPanel1.setLayout(networkPanel1Layout);
        networkPanel1Layout.setHorizontalGroup(
            networkPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(netVariable2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(networkComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        networkPanel1Layout.setVerticalGroup(
            networkPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(networkPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(netVariable2)
                    .addComponent(networkComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(helpExitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(networkPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startB, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(networkPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headingLabel)
                .addGap(20, 20, 20)
                .addComponent(networkPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(networkPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(startB, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(helpExitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBActionPerformed
        CyNetwork net1 = getSelectedNetwork1();
        CyNetwork net2 = getSelectedNetwork2();
        logicThread = new IsoThread(this, net1, net2);
        logicThread.start();
        // validate input conditions
        
        
        
        
        /*
        if(currentnetwork != null){
            currentnetworkview = cyApplicationManager.getCurrentNetworkView();
            logicThread = new CliqueThread(this, currentnetwork, currentnetworkview, YESbutton.isSelected());
            logicThread.start();
        } else{
            startB.setEnabled(false);
            JOptionPane.showMessageDialog(null, "IMPORT a network first! ", "No Network found ", JOptionPane.WARNING_MESSAGE);
            return;
        }*/
    }//GEN-LAST:event_startBActionPerformed

    private void helpBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpBActionPerformed
        IsoHelp help = new IsoHelp();
        help.setText(1);
        help.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_helpBActionPerformed

    private void exitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBActionPerformed
        // TODO add your handling code here:
        isocore.closecore();
        isocore.closeCliqueStartMenu();
    }//GEN-LAST:event_exitBActionPerformed
    @Override
    public Icon getIcon() {
        return null;
    }
    
    @Override
    public String getTitle() {
        return "MClique";
    }
    
    @Override
    public CytoPanelName getCytoPanelName() {
        return CytoPanelName.WEST;
    }
    
    public Component getComponent() {
        return this;
    }
    
    public void startComputation(){
        startB.setEnabled(false);
        statusBar.setIndeterminate(true);
        statusBar.setVisible(true);
        statusLabel.setText("Checking if graphs are isomorphic ...");
    }
    
    public void endComputation(){
        statusBar.setIndeterminate(false);
        statusLabel.setText("<html> Have fun with isomorphism ! <br> You might want to recompute with different inputs <html>");
        startB.setEnabled(true);
    }
    
    public void calculatingresult(String msg){
        statusLabel.setText(msg);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitB;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JButton helpB;
    private javax.swing.JPanel helpExitPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel netVariable1;
    private javax.swing.JLabel netVariable2;
    protected javax.swing.JComboBox networkComboBox1;
    protected javax.swing.JComboBox networkComboBox2;
    private javax.swing.JPanel networkPanel1;
    private javax.swing.JPanel networkPanel2;
    private javax.swing.JButton startB;
    private javax.swing.JProgressBar statusBar;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    protected void updateNetworkList() {
        final Set<CyNetwork> networks = CyActivator.getcyNetworkManager().getNetworkSet();
        final SortedSet<String> networkNames = new TreeSet<String>();

        for (CyNetwork net : networks)
                networkNames.add(net.getRow(net).get("name", String.class));

        // Clear the comboBox
        
        networkComboBox1.setModel(new DefaultComboBoxModel());
        networkComboBox2.setModel(new DefaultComboBoxModel());
        
        for (String name : networkNames){
            networkComboBox1.addItem(name);
            networkComboBox2.addItem(name);
        }

        CyNetwork currNetwork = this.cyApplicationManager.getCurrentNetwork();
        if (currNetwork != null) {
                String networkTitle = currNetwork.getRow(currNetwork).get("name", String.class);
                networkComboBox1.setSelectedItem(networkTitle);			
                networkComboBox1.setSelectedItem(networkTitle);		
        }
                
    }
    
    public void addItemListener1(final ItemListener newListener1) {
        networkComboBox1.addItemListener(newListener1);
    }
    
    public void addItemListener2(final ItemListener newListener2) {
        networkComboBox2.addItemListener(newListener2);
    }
    
    public CyNetwork getSelectedNetwork1() {
        for (CyNetwork net : CyActivator.getcyNetworkManager().getNetworkSet()) {
                String networkTitle = net.getRow(net).get("name", String.class);
                if (networkTitle.equals(networkComboBox1.getSelectedItem()))
                        return net;
        }

        return null;
    }
    
    public CyNetwork getSelectedNetwork2() {
        for (CyNetwork net : CyActivator.getcyNetworkManager().getNetworkSet()) {
                String networkTitle = net.getRow(net).get("name", String.class);
                if (networkTitle.equals(networkComboBox2.getSelectedItem()))
                        return net;
        }

        return null;
    }
    
}
