/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.gui;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.softech.bookstoremanagement.database.models.Users;
import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Linh
 */
public class CashierMain extends javax.swing.JFrame {
    // private tab for the main tab panel 

    private CashierProfileManagement tabCashierProfileManagement;

    /**
     * Private tabs for the main tab pane
     */
    private CashierGenerateReceipt tabCashierGenerateReceipt;
    
    /*
    Application theme
     */
    private String theme;
    private String themeConfigFilePath = "theme.properties";
    
    /*
    Application language settings
     */
    private String bundlePath = "com.softech.bookstoremanagement.gui.Bundle";
    private String languageConfigFilePath = "language.properties";

    private static Users userInfo = null;
    private static String userInfoFilePath = "signin_info/signin_info.bin";

    private static Users readUserInfo(String userInfoFilePath) {
        System.out.println("This is readUserInfo");
        Users userInfo = null;
        try ( FileInputStream f = new FileInputStream(new File(userInfoFilePath));  ObjectInputStream o = new ObjectInputStream(f)) {
            userInfo = (Users) o.readObject();
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(SignInUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            userInfo = null;
            ex.printStackTrace();
        } catch (IOException ex) {
//            Logger.getLogger(SignInUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            userInfo = null;
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(SignInUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            userInfo = null;
            ex.printStackTrace();
        }
        return userInfo;
    }

    private void writeUserNameToolbar() {
        userInfo = readUserInfo(userInfoFilePath);
        String userFullName = StringUtils.capitalize(userInfo.getRole()) + ": " + userInfo.getFirstName() + " " + userInfo.getLastName();
        lblUserFullName.setText(userFullName);
    }

    /**
     * Creates new form CashierMain
     */
    public CashierMain() {
        initComponents();
        writeUserNameToolbar();
        mniLightTheme.setActionCommand("Light");
        mniDarkTheme.setActionCommand("Dark");
        
        this.setLanguage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dilSignOut = new javax.swing.JDialog();
        radGroupThemes = new javax.swing.ButtonGroup();
        radGroupLanguages = new javax.swing.ButtonGroup();
        tblCashier = new javax.swing.JToolBar();
        btnCreateReceipt = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnProfile = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUserFullName = new javax.swing.JLabel();
        tplCashier = new javax.swing.JTabbedPane();
        mnbCashier = new javax.swing.JMenuBar();
        mnuSystem = new javax.swing.JMenu();
        mnuThemes = new javax.swing.JMenu();
        mniLightTheme = new javax.swing.JRadioButtonMenuItem();
        mniDarkTheme = new javax.swing.JRadioButtonMenuItem();
        mnuLanguages = new javax.swing.JMenu();
        mniEnglish = new javax.swing.JRadioButtonMenuItem();
        mniVietnamese = new javax.swing.JRadioButtonMenuItem();
        mniSignOut = new javax.swing.JMenuItem();
        mniExit = new javax.swing.JMenuItem();
        mnuCashierTools = new javax.swing.JMenu();
        mniCreateReceipts = new javax.swing.JMenuItem();
        mniProfileMan = new javax.swing.JMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mniAboutUs = new javax.swing.JMenuItem();

        javax.swing.GroupLayout dilSignOutLayout = new javax.swing.GroupLayout(dilSignOut.getContentPane());
        dilSignOut.getContentPane().setLayout(dilSignOutLayout);
        dilSignOutLayout.setHorizontalGroup(
            dilSignOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        dilSignOutLayout.setVerticalGroup(
            dilSignOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/softech/bookstoremanagement/gui/Bundle"); // NOI18N
        setTitle(bundle.getString("CashierMain.title")); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 750));
        setSize(new java.awt.Dimension(1000, 750));

        tblCashier.setRollover(true);

        btnCreateReceipt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-bill-48.png"))); // NOI18N
        btnCreateReceipt.setText(bundle.getString("CashierMain.btnCreateReceipt.text")); // NOI18N
        btnCreateReceipt.setFocusable(false);
        btnCreateReceipt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCreateReceipt.setPreferredSize(new java.awt.Dimension(140, 73));
        btnCreateReceipt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCreateReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateReceiptActionPerformed(evt);
            }
        });
        tblCashier.add(btnCreateReceipt);
        tblCashier.add(jSeparator1);

        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-client-management-48.png"))); // NOI18N
        btnProfile.setText(bundle.getString("CashierMain.btnProfile.text")); // NOI18N
        btnProfile.setFocusable(false);
        btnProfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfile.setPreferredSize(new java.awt.Dimension(140, 81));
        btnProfile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });
        tblCashier.add(btnProfile);
        tblCashier.add(jSeparator2);

        jLabel1.setText(bundle.getString("CashierMain.jLabel1.text")); // NOI18N

        lblUserFullName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblUserFullName))
                .addContainerGap(642, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUserFullName)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        tblCashier.add(jPanel1);

        mnuSystem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-administrative-tools-18.png"))); // NOI18N
        mnuSystem.setText(bundle.getString("CashierMain.mnuSystem.text")); // NOI18N

        mnuThemes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/theme-menu-18.png"))); // NOI18N
        mnuThemes.setText(bundle.getString("CashierMain.mnuThemes.text")); // NOI18N

        radGroupThemes.add(mniLightTheme);
        mniLightTheme.setText(bundle.getString("CashierMain.mniLightTheme.text")); // NOI18N
        mniLightTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/light-theme-18.png"))); // NOI18N
        mniLightTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLightThemeActionPerformed(evt);
            }
        });
        mnuThemes.add(mniLightTheme);

        radGroupThemes.add(mniDarkTheme);
        mniDarkTheme.setText(bundle.getString("CashierMain.mniDarkTheme.text")); // NOI18N
        mniDarkTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/dark-theme-18.png"))); // NOI18N
        mniDarkTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDarkThemeActionPerformed(evt);
            }
        });
        mnuThemes.add(mniDarkTheme);

        mnuSystem.add(mnuThemes);

        mnuLanguages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/languages-2-18.png"))); // NOI18N
        mnuLanguages.setText(bundle.getString("CashierMain.mnuLanguages.text")); // NOI18N

        radGroupLanguages.add(mniEnglish);
        mniEnglish.setText(bundle.getString("CashierMain.mniEnglish.text")); // NOI18N
        mniEnglish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/uk-flag-18.png"))); // NOI18N
        mniEnglish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEnglishActionPerformed(evt);
            }
        });
        mnuLanguages.add(mniEnglish);

        radGroupLanguages.add(mniVietnamese);
        mniVietnamese.setText(bundle.getString("CashierMain.mniVietnamese.text")); // NOI18N
        mniVietnamese.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/vn-flag-18.png"))); // NOI18N
        mniVietnamese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVietnameseActionPerformed(evt);
            }
        });
        mnuLanguages.add(mniVietnamese);

        mnuSystem.add(mnuLanguages);

        mniSignOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-import-18.png"))); // NOI18N
        mniSignOut.setText(bundle.getString("CashierMain.mniSignOut.text")); // NOI18N
        mniSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSignOutActionPerformed(evt);
            }
        });
        mnuSystem.add(mniSignOut);

        mniExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-shutdown-18.png"))); // NOI18N
        mniExit.setText(bundle.getString("CashierMain.mniExit.text")); // NOI18N
        mniExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniExitActionPerformed(evt);
            }
        });
        mnuSystem.add(mniExit);

        mnbCashier.add(mnuSystem);

        mnuCashierTools.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-office-18.png"))); // NOI18N
        mnuCashierTools.setText(bundle.getString("CashierMain.mnuCashierTools.text")); // NOI18N
        mnuCashierTools.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCashierToolsActionPerformed(evt);
            }
        });

        mniCreateReceipts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-bill-18.png"))); // NOI18N
        mniCreateReceipts.setText(bundle.getString("CashierMain.mniCreateReceipts.text")); // NOI18N
        mniCreateReceipts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCreateReceiptsActionPerformed(evt);
            }
        });
        mnuCashierTools.add(mniCreateReceipts);

        mniProfileMan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-client-management-18.png"))); // NOI18N
        mniProfileMan.setText(bundle.getString("CashierMain.mniProfileMan.text")); // NOI18N
        mniProfileMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProfileManActionPerformed(evt);
            }
        });
        mnuCashierTools.add(mniProfileMan);

        mnbCashier.add(mnuCashierTools);

        mnuHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-help-18.png"))); // NOI18N
        mnuHelp.setText(bundle.getString("CashierMain.mnuHelp.text")); // NOI18N

        mniAboutUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-people-working-together-18.png"))); // NOI18N
        mniAboutUs.setText(bundle.getString("CashierMain.mniAboutUs.text")); // NOI18N
        mniAboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAboutUsActionPerformed(evt);
            }
        });
        mnuHelp.add(mniAboutUs);

        mnbCashier.add(mnuHelp);

        setJMenuBar(mnbCashier);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tplCashier)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tblCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tplCashier))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuCashierToolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCashierToolsActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_mnuCashierToolsActionPerformed

    private void mniCreateReceiptsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCreateReceiptsActionPerformed
        // TODO add your handling code here:
        if (tabCashierGenerateReceipt == null) {
            tabCashierGenerateReceipt = new CashierGenerateReceipt();
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-bill-18.png"));
            
            String language = "";
            Configurations languageConfigs = new Configurations();

            try {
                Configuration languageConfig = languageConfigs.properties(new File(languageConfigFilePath));
                language = languageConfig.getString("language");
            } catch (ConfigurationException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            if (language.equals("vi")) {
                tplCashier.addTab("Tạo Hóa Đơn", icon, tabCashierGenerateReceipt);
            } else {
                tplCashier.addTab("Generate Receipt", icon, tabCashierGenerateReceipt);
            }             
            
        }
        tplCashier.setSelectedComponent(tabCashierGenerateReceipt);

    }//GEN-LAST:event_mniCreateReceiptsActionPerformed

    private void mniExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mniExitActionPerformed

    private void mniProfileManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniProfileManActionPerformed
        if (tabCashierProfileManagement == null) {
            tabCashierProfileManagement = new CashierProfileManagement();
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-client-management-18.png"));
            
            String language = "";
            Configurations languageConfigs = new Configurations();

            try {
                Configuration languageConfig = languageConfigs.properties(new File(languageConfigFilePath));
                language = languageConfig.getString("language");
            } catch (ConfigurationException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            if (language.equals("vi")) {
                tplCashier.addTab("Tài Khoản", icon, tabCashierProfileManagement);
            } else {
                tplCashier.addTab("Profile Management", icon, tabCashierProfileManagement);
            }            
            
        }
        tplCashier.setSelectedComponent(tabCashierProfileManagement);


    }//GEN-LAST:event_mniProfileManActionPerformed

    private void btnCreateReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateReceiptActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if (tabCashierGenerateReceipt == null) {
            tabCashierGenerateReceipt = new CashierGenerateReceipt();
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-bill-18.png"));
            String language = "";
            Configurations languageConfigs = new Configurations();

            try {
                Configuration languageConfig = languageConfigs.properties(new File(languageConfigFilePath));
                language = languageConfig.getString("language");
            } catch (ConfigurationException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            if (language.equals("vi")) {
                tplCashier.addTab("Tạo Hóa Đơn", icon, tabCashierGenerateReceipt);
            } else {
                tplCashier.addTab("Generate Receipt", icon, tabCashierGenerateReceipt);
            } 
        }
        tplCashier.setSelectedComponent(tabCashierGenerateReceipt);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnCreateReceiptActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if (tabCashierProfileManagement == null) {
            tabCashierProfileManagement = new CashierProfileManagement();
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/softech/bookstoremanagement/icons/icons8-client-management-18.png"));
            String language = "";
            Configurations languageConfigs = new Configurations();

            try {
                Configuration languageConfig = languageConfigs.properties(new File(languageConfigFilePath));
                language = languageConfig.getString("language");
            } catch (ConfigurationException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            if (language.equals("vi")) {
                tplCashier.addTab("Tài Khoản", icon, tabCashierProfileManagement);
            } else {
                tplCashier.addTab("Profile Management", icon, tabCashierProfileManagement);
            }
        }
        tplCashier.setSelectedComponent(tabCashierProfileManagement);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }//GEN-LAST:event_btnProfileActionPerformed

    private void mniAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAboutUsActionPerformed
        // TODO add your handling code here:
        AboutUs aboutUs = new AboutUs(this, true);

        aboutUs.setVisible(true);
    }//GEN-LAST:event_mniAboutUsActionPerformed

    private void mniSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSignOutActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(dilSignOut, "Are you sure?", "Sign Out Confirm", JOptionPane.YES_NO_OPTION);
        if (a == JOptionPane.YES_OPTION) {
            dispose();
            try {
                File file = new File("signin_info/signin_info.bin");
                if (file.delete()) {
                    System.out.println(file.getName() + " is deleted!");
                } else {
                    System.out.println("Delete operation is failed.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            SignIn logOut = new SignIn();
            logOut.setVisible(true);
        }
    }//GEN-LAST:event_mniSignOutActionPerformed

    private void changeTheme() {
        theme = radGroupThemes.getSelection().getActionCommand();
        Configurations configs = new Configurations();
        try {
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder
                    = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName(themeConfigFilePath)
                                    .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
            Configuration config = builder.getConfiguration();
            config.setProperty("theme", theme.toString());
            builder.save();
        } catch (ConfigurationException ex) {
//            Logger.getLogger(ManagerMain.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        if (theme.equals("Light")) {
            try {
                UIManager.setLookAndFeel(new FlatArcOrangeIJTheme());
                SwingUtilities.updateComponentTreeUI(this);
                this.pack();
            } catch (UnsupportedLookAndFeelException ex) {
//                Logger.getLogger(ChangeLookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        } else if (theme.equals("Dark")) {
            try {
                UIManager.setLookAndFeel(new FlatNordIJTheme());
                SwingUtilities.updateComponentTreeUI(this);
                this.pack();
            } catch (UnsupportedLookAndFeelException ex) {
//                Logger.getLogger(ChangeLookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }
    
    private void mniLightThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLightThemeActionPerformed
        // TODO add your handling code here:
        this.changeTheme();
    }//GEN-LAST:event_mniLightThemeActionPerformed

    private void mniDarkThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDarkThemeActionPerformed
        // TODO add your handling code here:
        this.changeTheme();
    }//GEN-LAST:event_mniDarkThemeActionPerformed

    private void mniEnglishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEnglishActionPerformed
        // TODO add your handling code here:
        this.toEnglish();
    }//GEN-LAST:event_mniEnglishActionPerformed

    private void mniVietnameseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVietnameseActionPerformed
        // TODO add your handling code here:
        this.toVietnamese();
    }//GEN-LAST:event_mniVietnameseActionPerformed

    private void toVietnamese() {
        Locale locale = new Locale("vi", "VN");
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundlePath, locale);

        try {
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder
                    = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName(languageConfigFilePath)
                                    .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
            Configuration config = builder.getConfiguration();
            config.setProperty("language", "vi");
            builder.save();

        } catch (ConfigurationException ex) {
//            Logger.getLogger(ManagerMain.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        mnuThemes.setText(resourceBundle.getString("CashierMain.mnuThemes.text"));
        mnuSystem.setText(resourceBundle.getString("CashierMain.mnuSystem.text"));
        jLabel1.setText(resourceBundle.getString("CashierMain.jLabel1.text"));
        mnuLanguages.setText(resourceBundle.getString("CashierMain.mnuLanguages.text"));
        btnProfile.setText(resourceBundle.getString("CashierMain.btnProfile.text"));
        btnCreateReceipt.setText(resourceBundle.getString("CashierMain.btnCreateReceipt.text"));
        mniAboutUs.setText(resourceBundle.getString("CashierMain.mniAboutUs.text"));
        mnuHelp.setText(resourceBundle.getString("CashierMain.mnuHelp.text"));
        mniProfileMan.setText(resourceBundle.getString("CashierMain.mniProfileMan.text"));
        mniVietnamese.setText(resourceBundle.getString("CashierMain.mniVietnamese.text"));
        mniCreateReceipts.setText(resourceBundle.getString("CashierMain.mniCreateReceipts.text"));
        mnuCashierTools.setText(resourceBundle.getString("CashierMain.mnuCashierTools.text"));
        mniEnglish.setText(resourceBundle.getString("CashierMain.mniEnglish.text"));
        mniExit.setText(resourceBundle.getString("CashierMain.mniExit.text"));
        mniSignOut.setText(resourceBundle.getString("CashierMain.mniSignOut.text"));
        mniDarkTheme.setText(resourceBundle.getString("CashierMain.mniDarkTheme.text"));
        mniLightTheme.setText(resourceBundle.getString("CashierMain.mniLightTheme.text"));
        
        if (tabCashierGenerateReceipt != null) {
            tabCashierGenerateReceipt.setLanguage();
            int tabIndex = tplCashier.indexOfTab("Generate Receipt");
            tplCashier.setTitleAt(tabIndex, "Tạo Hóa Đơn");
        }
        
        if (tabCashierProfileManagement != null) {
            tabCashierProfileManagement.setLanguage();
            int tabIndex = tplCashier.indexOfTab("Profile Management");
            tplCashier.setTitleAt(tabIndex, "Tài Khoản");
        }        
        
    }
    
    private void toEnglish() {
        Locale locale = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundlePath, locale);

        try {
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder
                    = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName(languageConfigFilePath)
                                    .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
            Configuration config = builder.getConfiguration();
            config.setProperty("language", "en");
            builder.save();

        } catch (ConfigurationException ex) {
//            Logger.getLogger(ManagerMain.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        mnuThemes.setText(resourceBundle.getString("CashierMain.mnuThemes.text"));
        mnuSystem.setText(resourceBundle.getString("CashierMain.mnuSystem.text"));
        jLabel1.setText(resourceBundle.getString("CashierMain.jLabel1.text"));
        mnuLanguages.setText(resourceBundle.getString("CashierMain.mnuLanguages.text"));
        btnProfile.setText(resourceBundle.getString("CashierMain.btnProfile.text"));
        btnCreateReceipt.setText(resourceBundle.getString("CashierMain.btnCreateReceipt.text"));
        mniAboutUs.setText(resourceBundle.getString("CashierMain.mniAboutUs.text"));
        mnuHelp.setText(resourceBundle.getString("CashierMain.mnuHelp.text"));
        mniProfileMan.setText(resourceBundle.getString("CashierMain.mniProfileMan.text"));
        mniVietnamese.setText(resourceBundle.getString("CashierMain.mniVietnamese.text"));
        mniCreateReceipts.setText(resourceBundle.getString("CashierMain.mniCreateReceipts.text"));
        mnuCashierTools.setText(resourceBundle.getString("CashierMain.mnuCashierTools.text"));
        mniEnglish.setText(resourceBundle.getString("CashierMain.mniEnglish.text"));
        mniExit.setText(resourceBundle.getString("CashierMain.mniExit.text"));
        mniSignOut.setText(resourceBundle.getString("CashierMain.mniSignOut.text"));
        mniDarkTheme.setText(resourceBundle.getString("CashierMain.mniDarkTheme.text"));
        mniLightTheme.setText(resourceBundle.getString("CashierMain.mniLightTheme.text"));
        
        if (tabCashierGenerateReceipt != null) {
            tabCashierGenerateReceipt.setLanguage();
            int tabIndex = tplCashier.indexOfTab("Tạo Hóa Đơn");
            tplCashier.setTitleAt(tabIndex, "Generate Receipt");
        }
        
        if (tabCashierProfileManagement != null) {
            tabCashierProfileManagement.setLanguage();
            int tabIndex = tplCashier.indexOfTab("Tài Khoản");
            tplCashier.setTitleAt(tabIndex, "Profile Management");
        }
        
    }
    
    private void setLanguage() {
        Locale locale;
        String language = "";
        Configurations languageConfigs = new Configurations();
        
        try {
            Configuration languageConfig = languageConfigs.properties(new File(languageConfigFilePath));
            language = languageConfig.getString("language");
        } catch (ConfigurationException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        if (language.equals("vi")) {
            locale = new Locale("vi", "VN");
        } else {
            locale = Locale.getDefault();
        }
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundlePath, locale);
        
        mnuThemes.setText(resourceBundle.getString("CashierMain.mnuThemes.text"));
        mnuSystem.setText(resourceBundle.getString("CashierMain.mnuSystem.text"));
        jLabel1.setText(resourceBundle.getString("CashierMain.jLabel1.text"));
        mnuLanguages.setText(resourceBundle.getString("CashierMain.mnuLanguages.text"));
        btnProfile.setText(resourceBundle.getString("CashierMain.btnProfile.text"));
        btnCreateReceipt.setText(resourceBundle.getString("CashierMain.btnCreateReceipt.text"));
        mniAboutUs.setText(resourceBundle.getString("CashierMain.mniAboutUs.text"));
        mnuHelp.setText(resourceBundle.getString("CashierMain.mnuHelp.text"));
        mniProfileMan.setText(resourceBundle.getString("CashierMain.mniProfileMan.text"));
        mniVietnamese.setText(resourceBundle.getString("CashierMain.mniVietnamese.text"));
        mniCreateReceipts.setText(resourceBundle.getString("CashierMain.mniCreateReceipts.text"));
        mnuCashierTools.setText(resourceBundle.getString("CashierMain.mnuCashierTools.text"));
        mniEnglish.setText(resourceBundle.getString("CashierMain.mniEnglish.text"));
        mniExit.setText(resourceBundle.getString("CashierMain.mniExit.text"));
        mniSignOut.setText(resourceBundle.getString("CashierMain.mniSignOut.text"));
        mniDarkTheme.setText(resourceBundle.getString("CashierMain.mniDarkTheme.text"));
        mniLightTheme.setText(resourceBundle.getString("CashierMain.mniLightTheme.text"));
        
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CashierMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CashierMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CashierMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CashierMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }

        try {
            /*
            Flatlaf look and feel
             */
            UIManager.setLookAndFeel(new FlatArcOrangeIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashierMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateReceipt;
    private javax.swing.JButton btnProfile;
    private javax.swing.JDialog dilSignOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JLabel lblUserFullName;
    private javax.swing.JMenuBar mnbCashier;
    private javax.swing.JMenuItem mniAboutUs;
    private javax.swing.JMenuItem mniCreateReceipts;
    private javax.swing.JRadioButtonMenuItem mniDarkTheme;
    private javax.swing.JRadioButtonMenuItem mniEnglish;
    private javax.swing.JMenuItem mniExit;
    private javax.swing.JRadioButtonMenuItem mniLightTheme;
    private javax.swing.JMenuItem mniProfileMan;
    private javax.swing.JMenuItem mniSignOut;
    private javax.swing.JRadioButtonMenuItem mniVietnamese;
    private javax.swing.JMenu mnuCashierTools;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenu mnuLanguages;
    private javax.swing.JMenu mnuSystem;
    private javax.swing.JMenu mnuThemes;
    private javax.swing.ButtonGroup radGroupLanguages;
    private javax.swing.ButtonGroup radGroupThemes;
    private javax.swing.JToolBar tblCashier;
    private javax.swing.JTabbedPane tplCashier;
    // End of variables declaration//GEN-END:variables
}
