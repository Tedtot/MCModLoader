package com.mcmodloader;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class UI extends javax.swing.JFrame {

    private boolean readyToDownload;
    private Settings settings;
    private CFModpackChooser cfmc;

    public UI() {
        readyToDownload = false;
        initComponents();
    }

    private void initComponents() {

        launcherbuttons = new javax.swing.ButtonGroup();
        downloadbutton = new javax.swing.JButton();
        launcherbutton1 = new javax.swing.JRadioButton();
        launcherbutton2 = new javax.swing.JRadioButton();
        launcherbutton3 = new javax.swing.JRadioButton();
        choosedirectorybutton = new javax.swing.JButton();
        moddirectorylabel = new javax.swing.JLabel();
        actionlabel = new javax.swing.JLabel();
        launcherlabel = new javax.swing.JLabel();
        settingsbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Minecraft Mod Loader - v1.1");

        downloadbutton.setText("Download Mods");
        downloadbutton.setFocusable(false);
        downloadbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (downloadbutton.getText().equals("Create mods folder")) {
                    File modsFolder = new File("C:/Users/" + System.getProperty("user.name") + path + "/mods");
                    modsFolder.mkdirs();
                    setDefault();
                    // moddirectorylabel.setText(modsFolder.getAbsolutePath());
                    setModDirectoryLabel(modsFolder.getAbsolutePath());
                    return;
                }

                if (readyToDownload == false)// launcherbuttons.getSelection() == null)
                    return;

                Console console = new Console();

                Thread t1 = new Thread() {
                    public void run() {
                        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
                        downloadbuttonActionPerformed();

                        console.setWindowCloseable();
                        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                        readyToDownload = false;
                        moddirectorylabel.setText("None");
                        launcherbuttons.clearSelection();
                    }
                };

                t1.start();
            }
        });

        launcherbuttons.add(launcherbutton1);
        launcherbutton1.setText("Minecraft Launcher");
        launcherbutton1.setFocusable(false);
        launcherbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launcherbutton1ActionPerformed(evt);
            }
        });

        launcherbuttons.add(launcherbutton2);
        launcherbutton2.setText("CurseForge");
        launcherbutton2.setFocusable(false);
        launcherbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launcherbutton2ActionPerformed(evt);
            }
        });
        launcherbuttons.add(launcherbutton3);
        launcherbutton3.setText("Other");
        launcherbutton3.setFocusable(false);
        launcherbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launcherbutton3ActionPerformed(evt);
            }
        });

        choosedirectorybutton.setText("Choose File");
        choosedirectorybutton.setFocusable(false);
        choosedirectorybutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosedirectorybuttonActionPerformed(evt);
            }
        });

        moddirectorylabel.setText("None");

        actionlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actionlabel.setMinimumSize(new java.awt.Dimension(0, 16));

        launcherlabel.setText("Launcher");

        settingsbutton.setText("Settings");
        settingsbutton.setFocusable(false);

        settingsbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(123, 123, 123)
                                                                .addComponent(downloadbutton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 154,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(choosedirectorybutton)))
                                                .addGap(0, 117, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(actionlabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(launcherbutton3)
                                                                        .addComponent(launcherbutton2))
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(launcherbutton1)
                                                                        .addComponent(launcherlabel))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(settingsbutton)
                                                                .addGap(8, 8, 8))
                                                        .addComponent(moddirectorylabel,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE))))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(launcherlabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(launcherbutton1))
                                        .addComponent(settingsbutton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(launcherbutton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(launcherbutton3)
                                .addGap(52, 52, 52)
                                .addComponent(actionlabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(downloadbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33,
                                        Short.MAX_VALUE)
                                .addComponent(choosedirectorybutton, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(moddirectorylabel)
                                .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private String path = "";
    private Downloader downloader = new Downloader();

    private void downloadbuttonActionPerformed() {
        // File modsFolder = new File(moddirectorylabel.getText());
        File modsFolder = new File(path);
        if (modsFolder.exists() && modsFolder.getName().equals("mods")) {
            downloader.runDownloader(modsFolder.getAbsolutePath());
        }
    }

    private void choosedirectorybuttonActionPerformed(java.awt.event.ActionEvent evt) {
        if (launcherbuttons.getSelection() == null)
            return;

        File file = new File("C:/Users/" + System.getProperty("user.name") + path);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(file);
        int result = chooser.showOpenDialog(chooser);
        if (result == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().getName().equals("mods")) {
            File selectedFile = chooser.getSelectedFile();
            // moddirectorylabel.setText(selectedFile.getAbsolutePath());
            setModDirectoryLabel(selectedFile.getAbsolutePath());
            readyToDownload = true;
        }
    }

    private void launcherbutton1ActionPerformed(java.awt.event.ActionEvent evt) {
        path = "/AppData/Roaming/.minecraft";
        File minecraftPath = new File("C:/Users/" + System.getProperty("user.name") + path + "/mods");
        if (!minecraftPath.exists()) {
            actionlabel.setText("Minecraft mods folder not found");
            downloadbutton.setText("Create mods folder");
            moddirectorylabel.setText("Set mods folder path");
            return;
        }
        // moddirectorylabel.setText(minecraftPath.getAbsolutePath());
        setModDirectoryLabel(minecraftPath.getAbsolutePath());
        readyToDownload = true;
    }

    private void launcherbutton2ActionPerformed(java.awt.event.ActionEvent evt) {
        path = "/curseforge/minecraft/Instances";
        setDefault();

        File curseForgePath = new File("C:/Users/" + System.getProperty("user.name") + path);

        if (!curseForgePath.exists() || curseForgePath.listFiles().length == 0) {
            moddirectorylabel.setText("No modpacks found");
            return;
        }

        moddirectorylabel.setText("Choose modpack from dropdown");
        if (cfmc != null)
            cfmc.dispose();

        cfmc = new CFModpackChooser(getWidth() + getX() - 10, getY() + 99, curseForgePath, this);
        cfmc.setVisible(true);
    }

    private void launcherbutton3ActionPerformed(java.awt.event.ActionEvent evt) {
        path = "";
        moddirectorylabel.setText("Set mods folder path");
        setDefault();
    }

    private void settingsbuttonActionPerformed(java.awt.event.ActionEvent evt) {
        if (settings != null)
            settings.dispose();
        settings = new Settings(getWidth() + getX() - 10, getY());
        settings.setVisible(true);
    }

    private void setDefault() {
        actionlabel.setText("");
        downloadbutton.setText("Download Mods");
    }

    public void curseForgeSetModpack(String directory) { // Shit method but im lazy
        String pathStart = "C:/Users/" + System.getProperty("user.name");
        path = directory;
        // moddirectorylabel.setText(pathStart + directory + "/mods");
        setModDirectoryLabel(pathStart + directory + "/mods");
        readyToDownload = true;
    }

    private void setModDirectoryLabel(String newText) {
        if (newText.length() > 71) {
            newText = newText.substring(0, 71) + "...";
        }
        moddirectorylabel.setText(newText);
        path = newText;
    }

    public static void main(String args[]) {

        try {
            // for (javax.swing.UIManager.LookAndFeelInfo info :
            // javax.swing.UIManager.getInstalledLookAndFeels()) {
            // if ("Nimbus".equals(info.getName())) {
            // javax.swing.UIManager.setLookAndFeel(info.getClassName());
            // break;
            // }
            // }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton choosedirectorybutton;
    private javax.swing.JLabel actionlabel;
    private javax.swing.JButton downloadbutton;
    private javax.swing.JRadioButton launcherbutton1;
    private javax.swing.JRadioButton launcherbutton2;
    private javax.swing.JRadioButton launcherbutton3;
    private javax.swing.ButtonGroup launcherbuttons;
    private javax.swing.JLabel launcherlabel;
    private javax.swing.JLabel moddirectorylabel;
    private javax.swing.JButton settingsbutton;
    // End of variables declaration
}
