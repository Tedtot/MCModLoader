package com.mcmodloader;

public class Settings extends javax.swing.JFrame {

    Downloader downloader = new Downloader();

    public Settings(int x, int y) {
        setLocation(x, y);
        initComponents();
    }

    private void initComponents() {
        setResizable(false);
        setTitle("Settings");

        downloadlinklabel = new javax.swing.JLabel();
        downloadtextbox = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        downloadlinklabel.setText("Downloading zip from (Enter to confirm):");

        downloadtextbox.setText("https://github.com/Tedtot/MinecraftMods/archive/refs/heads/main.zip");
        downloadtextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadtextboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(downloadlinklabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(downloadtextbox, javax.swing.GroupLayout.DEFAULT_SIZE, 488,
                                                Short.MAX_VALUE))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(downloadlinklabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(downloadtextbox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }

    private void downloadtextboxActionPerformed(java.awt.event.ActionEvent evt) {
        downloader.setDownloadSource(downloadtextbox.getText());
        dispose();
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel downloadlinklabel;
    private javax.swing.JFormattedTextField downloadtextbox;
    // End of variables declaration
}
