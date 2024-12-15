package com.mcmodloader;

import java.io.File;

public class CFModpackChooser extends javax.swing.JFrame {
    UI ui;

    public CFModpackChooser(int x, int y, File location, UI ui) {
        this.ui = ui;
        setLocation(x, y);
        initComponents(location);
    }

    private void initComponents(File location) {

        cfmodpackchooser = new javax.swing.JComboBox<>();
        cfmodpackchooserlabel = new javax.swing.JLabel();
        cfmodpackchooser.setFocusable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Curse Forge Modpacks");

        int modpackNumber = location.listFiles().length;

        String[] modpackNames = new String[modpackNumber + 1];
        modpackNames[0] = "[Choose Modpack]";
        for (int i = 0; i < modpackNumber; i++) {
            modpackNames[i + 1] = location.listFiles()[i].getName();
        }

        cfmodpackchooser.setModel(
                new javax.swing.DefaultComboBoxModel<>(modpackNames));

        cfmodpackchooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cfmodpackchooserActionPerformed(evt);
            }
        });

        cfmodpackchooserlabel.setText("Choose modpack:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cfmodpackchooserlabel)
                                        .addComponent(cfmodpackchooser, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cfmodpackchooserlabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cfmodpackchooser, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(147, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>

    private void cfmodpackchooserActionPerformed(java.awt.event.ActionEvent evt) {
        String currentSelection = cfmodpackchooser.getSelectedItem().toString();
        if (!currentSelection.equals("[Choose Modpack]")) {
            ui.curseForgeSetModpack("/curseforge/minecraft/Instances/" + currentSelection);
        }

    }

    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> cfmodpackchooser;
    private javax.swing.JLabel cfmodpackchooserlabel;
    // End of variables declaration
}
