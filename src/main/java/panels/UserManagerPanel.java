/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import dao.RoleDAO;
import dao.UserDAO;
import dao.UserrolesDAO;
import java.util.ArrayList;
import main.MainWindow;
import common.RootWindow;
import object.MouseAdapterForUserPanel;
import object.Role;
import object.User;
import object.Userroles;

/**
 *
 * @author Admin
 */
public class UserManagerPanel extends RootWindow {

    MainWindow mainFrame;
    UserDAO userDAO;
    RoleDAO roleDAO;
    UserrolesDAO userrolesDAO;
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Role> roles = new ArrayList<Role>();
    int width;
    int height;

    public UserManagerPanel() {
        initComponents();
    }

    public UserManagerPanel(MainWindow mainwindow) {
        this.mainFrame = mainwindow;
        this.userDAO = mainFrame.getDbManager().getUserDao();
        this.userrolesDAO = mainFrame.getDbManager().getUserrolesDAO();
        this.roleDAO = mainFrame.getDbManager().getRoleDAO();
        users = userDAO.getAllUsers();
        roles = roleDAO.getAllRoles();
        panelAktuell = new UserPanel(users, roles, userrolesDAO, width, height);

        MouseAdapterForUserPanel myMA = new MouseAdapterForUserPanel(users);
        initComponents();
        afterInitComponents();
        this.width = this.getWidth();
        this.height = this.getHeight();
        panelAktuell.setSize(1000, 1000);
        //  setSize(width,height);
        setVisible(true);
    }

    private void afterInitComponents() {
        refresh();
        //    setSize(600, 600);
        //    setVisible(true);
    }

    private User getUser(ArrayList<User> userlist, String username) {
        for (User user : userlist) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAktuell = new UserPanel(users,roles,userrolesDAO,width,height);
        buUserManCancel = new javax.swing.JButton();
        buUserManDel = new javax.swing.JButton();
        buUserManUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelAktuellLayout = new javax.swing.GroupLayout(panelAktuell);
        panelAktuell.setLayout(panelAktuellLayout);
        panelAktuellLayout.setHorizontalGroup(
            panelAktuellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelAktuellLayout.setVerticalGroup(
            panelAktuellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        buUserManCancel.setText("Cancel");
        buUserManCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buUserManCancelActionPerformed(evt);
            }
        });

        buUserManDel.setText("Delete");
        buUserManDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buUserManDelActionPerformed(evt);
            }
        });

        buUserManUpdate.setText("Update");
        buUserManUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buUserManUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAktuell, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(buUserManCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(buUserManDel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(buUserManUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelAktuell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buUserManCancel)
                    .addComponent(buUserManDel)
                    .addComponent(buUserManUpdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buUserManCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buUserManCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buUserManCancelActionPerformed

    private void buUserManDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buUserManDelActionPerformed
        String sUserfordelete = ((UserPanel) panelAktuell).deleteSelectedUser();
        User userForDelete = getUser(users, sUserfordelete);
        Userroles userroles = userrolesDAO.getUserrole(userForDelete);
        userrolesDAO.doUserRolesDELETE(userroles);
        userDAO.doUserDELETE(userForDelete);
    }//GEN-LAST:event_buUserManDelActionPerformed

    private void buUserManUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buUserManUpdateActionPerformed
        User userForchange = ((UserPanel) panelAktuell).changeSelectedUser();
        int iUserrolesID = ((UserPanel) panelAktuell).getUserrolesIDfromAuthority();
        Userroles userroles = userrolesDAO.getUserrole(userForchange);
        userrolesDAO.doUserRolesUPDATE(userroles);
        userDAO.doUserUPDATE(userForchange);
    }//GEN-LAST:event_buUserManUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(UserManagerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserManagerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserManagerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserManagerPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserManagerPanel().setVisible(true);
            }
        });
    }

    @Override
    public void refresh() {
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buUserManCancel;
    private javax.swing.JButton buUserManDel;
    private javax.swing.JButton buUserManUpdate;
    private javax.swing.JPanel panelAktuell;
    // End of variables declaration//GEN-END:variables
}
