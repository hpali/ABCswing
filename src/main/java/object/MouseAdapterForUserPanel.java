package object;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import panels.UsersPanel;
import object.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class MouseAdapterForUserPanel extends MouseAdapter {

    private ArrayList<User> users = new ArrayList<User>();

    int row = -1;
    int column = -1;
    //UserDeleteUpdate userDelete = new UserDeleteUpdate(userDAO);
    String userName = "";

    public MouseAdapterForUserPanel(ArrayList<User> lusers) {
        this.users = lusers;
    }

    /* public MouseAdapterForUserPanel(UserDeleteUpdate userdao) {
     this.userDelete = userdao;
     }*/
    public void mousePressed(MouseEvent e) {
        UsersPanel usersPanel = (UsersPanel) e.getSource();
        userName = usersPanel.getsUsername();
    }

    public String getUserName(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        row = target.getSelectedRow();
        //   column = target.getSelectedColumn();
        Component valami = target.getComponentAt(row, column);
        return (String) target.getValueAt(row, 0);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
