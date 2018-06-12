/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import common.RootWindow;
import dao.DBManager;
import dao.DbConfig;
import dao.FunctionDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.JMenuItem;
import object.Function;
import object.User;
import panels.FunctionListPanel;
import panels.FunctionManagerPanel;
import panels.LoginWindowPanel;
import panels.NewFunctionPanel;
import panels.NewRolePanel;
import panels.NewUserPanel;
import panels.SaveToXmlJDOMPanel;
import panels.SaveToXmlPanel;
import panels.UserManagerPanel;
import panels.UsersPanel;
import panels.XmlToDBHibernatePanel;
import panels.XmlToDBPanel;
import util.Utility;

/**
 *
 * @author Admin
 */
public class MainWindow extends RootWindow {
    
    private RootWindow selectorWindow;
    private NewUserPanel newuser;
    private ResourceBundle languageProp;
    private Properties systemConfig;
    private DbConfig dbConfig;
    private DBManager dbManager;
    private User activeUser;
    private Locale mainLocale;
    private FunctionDAO functionDAO;
    private Map<JMenuItem, String> allMenuItems = new HashMap<>();
    private ArrayList<JMenuItem> memuItemList = new ArrayList<JMenuItem>();
    
    public MainWindow() {
        initComponents();
        afterInitComponents();
    }
    
    private void afterInitComponents() {
        systemConfig = Utility.getProperties("config.properties");
        
        dbConfig = new DbConfig(
                systemConfig.getProperty("databaseurl"),
                systemConfig.getProperty("username"),
                systemConfig.getProperty("password"));
        dbManager = DBManager.getInstance(dbConfig);
        functionDAO = dbManager.getFunctionDAO();
        mainLocale = new Locale("EN", "EN");
        loadLanguage(mainLocale);
        addToMenuItemList();
        addMenuitemToMap();
        addFunctionsToDB();
        doMenuItemsDisabled();
        test();
        refresh();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu6 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        rootMenu = new javax.swing.JMenu();
        menuItemLogout = new javax.swing.JCheckBoxMenuItem();
        menuItemLogin = new javax.swing.JCheckBoxMenuItem();
        menuUsers = new javax.swing.JMenu();
        userlistItem = new javax.swing.JCheckBoxMenuItem();
        newuserItem = new javax.swing.JCheckBoxMenuItem();
        usermanagerItem = new javax.swing.JCheckBoxMenuItem();
        roleMenu = new javax.swing.JMenu();
        rolelistItem = new javax.swing.JCheckBoxMenuItem();
        newroleItem = new javax.swing.JCheckBoxMenuItem();
        rolemanagerItem = new javax.swing.JCheckBoxMenuItem();
        menuXmlJson = new javax.swing.JMenu();
        itemSaveToXml = new javax.swing.JCheckBoxMenuItem();
        itemSaveToXmlJDOM = new javax.swing.JCheckBoxMenuItem();
        functionMenu = new javax.swing.JMenu();
        newfunctionItem = new javax.swing.JCheckBoxMenuItem();
        functionlistItem = new javax.swing.JCheckBoxMenuItem();
        functionmanagerItem = new javax.swing.JCheckBoxMenuItem();
        menuDB = new javax.swing.JMenu();
        xmlToDbMenuItem = new javax.swing.JCheckBoxMenuItem();
        menuHibernate = new javax.swing.JMenu();
        miHibRevEng = new javax.swing.JCheckBoxMenuItem();
        miXmlToDBHibernate = new javax.swing.JCheckBoxMenuItem();

        jMenu6.setText("jMenu6");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("jCheckBoxMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rootMenu.setText("Root");

        menuItemLogout.setSelected(true);
        menuItemLogout.setText("Logout");
        menuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLogoutActionPerformed(evt);
            }
        });
        rootMenu.add(menuItemLogout);

        menuItemLogin.setSelected(true);
        menuItemLogin.setText("Login");
        menuItemLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLoginActionPerformed(evt);
            }
        });
        rootMenu.add(menuItemLogin);

        jMenuBar1.add(rootMenu);

        menuUsers.setText("Users");

        userlistItem.setSelected(true);
        userlistItem.setText("User list");
        userlistItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlistItemActionPerformed(evt);
            }
        });
        menuUsers.add(userlistItem);

        newuserItem.setSelected(true);
        newuserItem.setText("New User");
        newuserItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newuserItemActionPerformed(evt);
            }
        });
        menuUsers.add(newuserItem);

        usermanagerItem.setSelected(true);
        usermanagerItem.setText("User Manager");
        usermanagerItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usermanagerItemActionPerformed(evt);
            }
        });
        menuUsers.add(usermanagerItem);

        jMenuBar1.add(menuUsers);

        roleMenu.setText("Roles");

        rolelistItem.setSelected(true);
        rolelistItem.setText("Role list");
        roleMenu.add(rolelistItem);

        newroleItem.setSelected(true);
        newroleItem.setText("New role");
        roleMenu.add(newroleItem);

        rolemanagerItem.setSelected(true);
        rolemanagerItem.setText("Role Manager");
        roleMenu.add(rolemanagerItem);

        jMenuBar1.add(roleMenu);

        menuXmlJson.setText("Xml/Json");

        itemSaveToXml.setSelected(true);
        itemSaveToXml.setText("Generate file JAXB");
        itemSaveToXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSaveToXmlActionPerformed(evt);
            }
        });
        menuXmlJson.add(itemSaveToXml);

        itemSaveToXmlJDOM.setSelected(true);
        itemSaveToXmlJDOM.setText("Generate file JDOM");
        itemSaveToXmlJDOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSaveToXmlJDOMActionPerformed(evt);
            }
        });
        menuXmlJson.add(itemSaveToXmlJDOM);

        jMenuBar1.add(menuXmlJson);

        functionMenu.setText("Function");

        newfunctionItem.setSelected(true);
        newfunctionItem.setText("New Function");
        newfunctionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newfunctionItemActionPerformed(evt);
            }
        });
        functionMenu.add(newfunctionItem);

        functionlistItem.setSelected(true);
        functionlistItem.setText("Function list");
        functionlistItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                functionlistItemActionPerformed(evt);
            }
        });
        functionMenu.add(functionlistItem);

        functionmanagerItem.setSelected(true);
        functionmanagerItem.setText("Function Manager");
        functionmanagerItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                functionmanagerItemActionPerformed(evt);
            }
        });
        functionMenu.add(functionmanagerItem);

        jMenuBar1.add(functionMenu);

        menuDB.setText("DatenBank");

        xmlToDbMenuItem.setSelected(true);
        xmlToDbMenuItem.setText("Xml to DB");
        xmlToDbMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlToDbMenuItemActionPerformed(evt);
            }
        });
        menuDB.add(xmlToDbMenuItem);

        jMenuBar1.add(menuDB);

        menuHibernate.setText("Hibernata");

        miHibRevEng.setSelected(true);
        miHibRevEng.setText("Reverse Engineering Wizard");
        miHibRevEng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miHibRevEngActionPerformed(evt);
            }
        });
        menuHibernate.add(miHibRevEng);

        miXmlToDBHibernate.setSelected(true);
        miXmlToDBHibernate.setText("Save Xml to db");
        miXmlToDBHibernate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miXmlToDBHibernateActionPerformed(evt);
            }
        });
        menuHibernate.add(miXmlToDBHibernate);

        jMenuBar1.add(menuHibernate);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLogoutActionPerformed
        if (activeUser != null) {
            setActiveUser(null);
            systemConfig = Utility.getProperties("config.properties");
            dbConfig = null;
            dbManager = null;
            afterInitComponents();
        }
    }//GEN-LAST:event_menuItemLogoutActionPerformed
    
    public void test() {
        menuHibernate.setEnabled(true);
        //miHibRevEng;
        miXmlToDBHibernate.setEnabled(true);
    }
    
    public void addFunctionsToDB() {
        
        for (JMenuItem menuItem : memuItemList) {
            functionDAO.doFunctionInsert(new Function(menuItem.getText()));
        }
    }

    private void menuItemLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLoginActionPerformed
        selectorWindow = new LoginWindowPanel(this);
    }//GEN-LAST:event_menuItemLoginActionPerformed

    private void userlistItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlistItemActionPerformed
        selectorWindow = new UsersPanel(this);
    }//GEN-LAST:event_userlistItemActionPerformed

    private void newuserItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newuserItemActionPerformed
        selectorWindow = new NewUserPanel(this);
    }//GEN-LAST:event_newuserItemActionPerformed

    private void usermanagerItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usermanagerItemActionPerformed
        selectorWindow = new UserManagerPanel(this);
    }//GEN-LAST:event_usermanagerItemActionPerformed

    private void itemSaveToXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSaveToXmlActionPerformed
        selectorWindow = new SaveToXmlPanel(this);
    }//GEN-LAST:event_itemSaveToXmlActionPerformed

    private void itemSaveToXmlJDOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSaveToXmlJDOMActionPerformed
        selectorWindow = new SaveToXmlJDOMPanel(this);
    }//GEN-LAST:event_itemSaveToXmlJDOMActionPerformed

    private void newfunctionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newfunctionItemActionPerformed
        selectorWindow = new NewFunctionPanel(this);
    }//GEN-LAST:event_newfunctionItemActionPerformed

    private void functionlistItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_functionlistItemActionPerformed
        selectorWindow = new FunctionListPanel(this);
    }//GEN-LAST:event_functionlistItemActionPerformed

    private void functionmanagerItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_functionmanagerItemActionPerformed
        selectorWindow = new FunctionManagerPanel(this);
    }//GEN-LAST:event_functionmanagerItemActionPerformed

    private void xmlToDbMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlToDbMenuItemActionPerformed
        selectorWindow = new XmlToDBPanel(this);
    }//GEN-LAST:event_xmlToDbMenuItemActionPerformed

    private void miHibRevEngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miHibRevEngActionPerformed
        selectorWindow = new XmlToDBHibernatePanel(this);
    }//GEN-LAST:event_miHibRevEngActionPerformed

    private void miXmlToDBHibernateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miXmlToDBHibernateActionPerformed
        selectorWindow = new XmlToDBHibernatePanel(this);
    }//GEN-LAST:event_miXmlToDBHibernateActionPerformed
    
    public void addToMenuItemList() {
        memuItemList.add(newuserItem);
        memuItemList.add(menuItemLogin);
        memuItemList.add(newfunctionItem);
        memuItemList.add(usermanagerItem);
        memuItemList.add(functionmanagerItem);
        memuItemList.add(itemSaveToXmlJDOM);
        memuItemList.add(menuItemLogout);
        memuItemList.add(newroleItem);
        memuItemList.add(rolemanagerItem);
        memuItemList.add(rolelistItem);
        memuItemList.add(menuXmlJson);
        memuItemList.add(menuUsers);
        memuItemList.add(userlistItem);
        memuItemList.add(functionlistItem);
        memuItemList.add(functionMenu);
        memuItemList.add(roleMenu);
        memuItemList.add(itemSaveToXmlJDOM);
        memuItemList.add(xmlToDbMenuItem);
        memuItemList.add(menuDB);
        memuItemList.add(menuHibernate);
        memuItemList.add(miHibRevEng);
        memuItemList.add(miXmlToDBHibernate);
        
    }
    
    public void addMenuitemToMap() {
        for (JMenuItem jMenuItem : memuItemList) {
            allMenuItems.put(jMenuItem, jMenuItem.getText());
        }
    }
    
    public void doMenuItemsDisabled() {
        for (Map.Entry<JMenuItem, String> menuitem : allMenuItems.entrySet()) {
            if (!menuitem.getValue().contains("Login")) {
                menuitem.getKey().setEnabled(false);
            } else {
                menuitem.getKey().setEnabled(true);
            }
        }
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow mainFrame = new MainWindow();
                mainFrame.setVisible(true);
            }
        });
    }
    
    @Override
    public void refresh() {
        loadLanguage(mainLocale);
        newuserItem.setText(getLanguageProp().getString("newuserItem"));
        menuItemLogin.setText(getLanguageProp().getString("menuItemLogin"));
        newfunctionItem.setText(getLanguageProp().getString("newfunctionItem"));
        userlistItem.setText(getLanguageProp().getString("userlistItem"));
        usermanagerItem.setText(getLanguageProp().getString("usermanagerItem"));
        functionmanagerItem.setText(getLanguageProp().getString("functionmanagerItem"));
        functionlistItem.setText(getLanguageProp().getString("functionlistItem"));
        itemSaveToXmlJDOM.setText(getLanguageProp().getString("itemSaveToXml"));
        functionMenu.setText(getLanguageProp().getString("functionMenu"));
        menuItemLogout.setText(getLanguageProp().getString("menuItemLogout"));
        roleMenu.setText(getLanguageProp().getString("roleMenu"));
        rolemanagerItem.setText(getLanguageProp().getString("rolemanagerItem"));
        newroleItem.setText(getLanguageProp().getString("newroleItem"));
        rolelistItem.setText(getLanguageProp().getString("rolelistItem"));
        menuXmlJson.setText(getLanguageProp().getString("menuXmlJson"));
        menuUsers.setText(getLanguageProp().getString("menuUsers"));
        itemSaveToXmlJDOM.setText(getLanguageProp().getString("itemSaveToXmlJDOM"));
        menuDB.setText(getLanguageProp().getString("menuDB"));
        xmlToDbMenuItem.setText(getLanguageProp().getString("xmlToDbMenuItem"));
        menuHibernate.setText(getLanguageProp().getString("menuHibernate"));
        miHibRevEng.setText(getLanguageProp().getString("miHibRevEng"));
        miXmlToDBHibernate.setText(getLanguageProp().getString("miXmlToDBHibernate"));
        
        if (newuser != null) {
            newuser.refresh();
        }
    }
    
    public DbConfig getDbConfig() {
        return dbConfig;
    }
    
    public void setDbConfig(DbConfig dbConfig) {
        this.dbConfig = dbConfig;
    }
    
    public DBManager getDbManager() {
        return dbManager;
    }
    
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
    
    public User getActiveUser() {
        return activeUser;
    }
    
    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }
    
    public void loadLanguage(Locale loc) {
        setLanguageProp(Utility.getBoundle("lang", loc));  // betölti a  meglévő nyelvi fajlokat
    }
    
    public ResourceBundle getLanguageProp() {
        return languageProp;
    }
    
    public void setLanguageProp(ResourceBundle languageProp) {
        this.languageProp = languageProp;
    }
    
    public Properties getSystemConfig() {
        return systemConfig;
    }
    
    public void setSystemConfig(Properties systemConfig) {
        this.systemConfig = systemConfig;
    }
    
    public Locale getMainLocale() {
        return mainLocale;
    }
    
    public void setMainLocale(Locale mainLocale) {
        this.mainLocale = mainLocale;
    }
    
    public Map<JMenuItem, String> getAllMenuItems() {
        return allMenuItems;
    }
    
    public void setAllMenuItems(Map<JMenuItem, String> allMenuItems) {
        this.allMenuItems = allMenuItems;
    }
    
    public ArrayList<JMenuItem> getMemuItemList() {
        return memuItemList;
    }
    
    public void setMemuItemList(ArrayList<JMenuItem> memuItemList) {
        this.memuItemList = memuItemList;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu functionMenu;
    private javax.swing.JCheckBoxMenuItem functionlistItem;
    private javax.swing.JCheckBoxMenuItem functionmanagerItem;
    private javax.swing.JCheckBoxMenuItem itemSaveToXml;
    private javax.swing.JCheckBoxMenuItem itemSaveToXmlJDOM;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuDB;
    private javax.swing.JMenu menuHibernate;
    private javax.swing.JCheckBoxMenuItem menuItemLogin;
    private javax.swing.JCheckBoxMenuItem menuItemLogout;
    private javax.swing.JMenu menuUsers;
    private javax.swing.JMenu menuXmlJson;
    private javax.swing.JCheckBoxMenuItem miHibRevEng;
    private javax.swing.JCheckBoxMenuItem miXmlToDBHibernate;
    private javax.swing.JCheckBoxMenuItem newfunctionItem;
    private javax.swing.JCheckBoxMenuItem newroleItem;
    private javax.swing.JCheckBoxMenuItem newuserItem;
    private javax.swing.JMenu roleMenu;
    private javax.swing.JCheckBoxMenuItem rolelistItem;
    private javax.swing.JCheckBoxMenuItem rolemanagerItem;
    private javax.swing.JMenu rootMenu;
    private javax.swing.JCheckBoxMenuItem userlistItem;
    private javax.swing.JCheckBoxMenuItem usermanagerItem;
    private javax.swing.JCheckBoxMenuItem xmlToDbMenuItem;
    // End of variables declaration//GEN-END:variables

}
