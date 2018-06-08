package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import common.RootWindow;
import util.Utility;
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
import object.Function;
import object.User;

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
    private Map<JMenuItem, String> allMenuItems = new HashMap<JMenuItem, String>();
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
     //   doMenuItemsDisabled();
        test();
        refresh();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        rootMenu = new javax.swing.JMenu();
        menuItemLogout = new javax.swing.JMenuItem();
        menuItemLogin = new javax.swing.JMenuItem();
        menuUsers = new javax.swing.JMenu();
        userlistItem = new javax.swing.JMenuItem();
        newuserItem = new javax.swing.JMenuItem();
        usermanagerItem = new javax.swing.JMenuItem();
        roleMenu = new javax.swing.JMenu();
        rolelistItem = new javax.swing.JMenuItem();
        newroleItem = new javax.swing.JMenuItem();
        rolemanagerItem = new javax.swing.JMenuItem();
        menuXmlJson = new javax.swing.JMenu();
        itemSaveToXml = new javax.swing.JMenuItem();
        itemSaveToXmlJDOM = new javax.swing.JRadioButtonMenuItem();
        functionMenu = new javax.swing.JMenu();
        functionlistItem = new javax.swing.JMenuItem();
        newfunctionItem = new javax.swing.JMenuItem();
        functionmanagerItem = new javax.swing.JMenuItem();
        menuDB = new javax.swing.JMenu();
        xmlToDbMenuItem = new javax.swing.JMenuItem();
        menuHibernate = new javax.swing.JMenu();
        miHibRevEng = new javax.swing.JMenuItem();
        miXmlToDBHibernate = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rootMenu.setText("Root");

        menuItemLogout.setText("Logout");
        menuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLogoutActionPerformed(evt);
            }
        });
        rootMenu.add(menuItemLogout);

        menuItemLogin.setText("Login");
        menuItemLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLoginActionPerformed(evt);
            }
        });
        rootMenu.add(menuItemLogin);

        jMenuBar1.add(rootMenu);

        menuUsers.setText("Users");

        userlistItem.setText("User list");
        userlistItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlistItemActionPerformed(evt);
            }
        });
        menuUsers.add(userlistItem);

        newuserItem.setText("New user");
        newuserItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newuserItemActionPerformed(evt);
            }
        });
        menuUsers.add(newuserItem);

        usermanagerItem.setText("User manager");
        usermanagerItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usermanagerItemActionPerformed(evt);
            }
        });
        menuUsers.add(usermanagerItem);

        jMenuBar1.add(menuUsers);

        roleMenu.setText("Roles");

        rolelistItem.setText("Role list");
        roleMenu.add(rolelistItem);

        newroleItem.setText("New role");
        newroleItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newroleItemActionPerformed(evt);
            }
        });
        roleMenu.add(newroleItem);

        rolemanagerItem.setText("Role manager");
        roleMenu.add(rolemanagerItem);

        jMenuBar1.add(roleMenu);

        menuXmlJson.setText("Xml/Json");

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

        functionlistItem.setText("Function list");
        functionlistItem.setActionCommand("Function List");
        functionlistItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                functionlistItemActionPerformed(evt);
            }
        });
        functionMenu.add(functionlistItem);

        newfunctionItem.setText("New function");
        newfunctionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newfunctionItemActionPerformed(evt);
            }
        });
        functionMenu.add(newfunctionItem);

        functionmanagerItem.setText("Function manager");
        functionmanagerItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                functionmanagerItemActionPerformed(evt);
            }
        });
        functionMenu.add(functionmanagerItem);

        jMenuBar1.add(functionMenu);

        menuDB.setText("DatenBank");

        xmlToDbMenuItem.setText("XML to DB");
        xmlToDbMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlToDbMenuItemActionPerformed(evt);
            }
        });
        menuDB.add(xmlToDbMenuItem);

        jMenuBar1.add(menuDB);

        menuHibernate.setText("Hibernate");

        miHibRevEng.setText("Reverse Engineering Wizard");
        menuHibernate.add(miHibRevEng);

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
                        .addGap(0, 453, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void test() {
        menuHibernate.setEnabled(true);
        miHibRevEng.setEnabled(true);
        miXmlToDBHibernate.setEnabled(true);
    }

    public void addToMenuItemList() {
        memuItemList.add(newuserItem);
        memuItemList.add(menuItemLogin);
        memuItemList.add(newfunctionItem);
        memuItemList.add(usermanagerItem);
        memuItemList.add(functionmanagerItem);
        memuItemList.add(itemSaveToXml);
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

    private void newuserItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newuserItemActionPerformed
        selectorWindow = new NewUserPanel(this);
    }//GEN-LAST:event_newuserItemActionPerformed

    private void menuItemLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLoginActionPerformed
        selectorWindow = new LoginWindowPanel(this);
    }//GEN-LAST:event_menuItemLoginActionPerformed

    private void newroleItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newroleItemActionPerformed
        selectorWindow = new NewRolePanel(this);
    }//GEN-LAST:event_newroleItemActionPerformed

    private void newfunctionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newfunctionItemActionPerformed
        selectorWindow = new NewFunctionPanel(this);
    }//GEN-LAST:event_newfunctionItemActionPerformed

    private void userlistItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlistItemActionPerformed
        selectorWindow = new UsersPanel(this);
    }//GEN-LAST:event_userlistItemActionPerformed

    private void usermanagerItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usermanagerItemActionPerformed
        selectorWindow = new UserManagerPanel(this);
    }//GEN-LAST:event_usermanagerItemActionPerformed

    private void menuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLogoutActionPerformed
        if (activeUser != null) {
            setActiveUser(null);
            systemConfig = Utility.getProperties("config.properties");
            dbConfig = null;
            dbManager = null;
            afterInitComponents();
        }
    }//GEN-LAST:event_menuItemLogoutActionPerformed

    public void addFunctionsToDB() {

        for (JMenuItem menuItem : memuItemList) {
            functionDAO.doFunctionInsert(new Function(menuItem.getText()));
        }
    }

    private void functionmanagerItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_functionmanagerItemActionPerformed
        selectorWindow = new FunctionManagerPanel(this);
    }//GEN-LAST:event_functionmanagerItemActionPerformed

    private void functionlistItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_functionlistItemActionPerformed
        selectorWindow = new FunctionListPanel(this);
    }//GEN-LAST:event_functionlistItemActionPerformed

    private void itemSaveToXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSaveToXmlActionPerformed
        selectorWindow = new SaveToXmlPanel(this);
    }//GEN-LAST:event_itemSaveToXmlActionPerformed

    private void itemSaveToXmlJDOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSaveToXmlJDOMActionPerformed
        selectorWindow = new SaveToXmlJDOMPanel(this);
    }//GEN-LAST:event_itemSaveToXmlJDOMActionPerformed

    private void xmlToDbMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlToDbMenuItemActionPerformed
        selectorWindow = new XmlToDBPanel(this);
    }//GEN-LAST:event_xmlToDbMenuItemActionPerformed

    private void miXmlToDBHibernateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miXmlToDBHibernateActionPerformed
        selectorWindow = new XmlToDBHibernatePanel(this);
    }//GEN-LAST:event_miXmlToDBHibernateActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu functionMenu;
    private javax.swing.JMenuItem functionlistItem;
    private javax.swing.JMenuItem functionmanagerItem;
    private javax.swing.JMenuItem itemSaveToXml;
    private javax.swing.JRadioButtonMenuItem itemSaveToXmlJDOM;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu menuDB;
    private javax.swing.JMenu menuHibernate;
    private javax.swing.JMenuItem menuItemLogin;
    private javax.swing.JMenuItem menuItemLogout;
    private javax.swing.JMenu menuUsers;
    private javax.swing.JMenu menuXmlJson;
    private javax.swing.JMenuItem miHibRevEng;
    private javax.swing.JMenuItem miXmlToDBHibernate;
    private javax.swing.JMenuItem newfunctionItem;
    private javax.swing.JMenuItem newroleItem;
    private javax.swing.JMenuItem newuserItem;
    private javax.swing.JMenu roleMenu;
    private javax.swing.JMenuItem rolelistItem;
    private javax.swing.JMenuItem rolemanagerItem;
    private javax.swing.JMenu rootMenu;
    private javax.swing.JMenuItem userlistItem;
    private javax.swing.JMenuItem usermanagerItem;
    private javax.swing.JMenuItem xmlToDbMenuItem;
    // End of variables declaration//GEN-END:variables

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
        itemSaveToXml.setText(getLanguageProp().getString("itemSaveToXml"));
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

}
