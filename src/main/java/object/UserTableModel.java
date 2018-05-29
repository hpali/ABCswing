/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class UserTableModel extends AbstractTableModel {

    private String[] columnNames = {"User name", "Password", "Full Name", "Role"};
    private ArrayList<User> users = new ArrayList<>();

    public UserTableModel(ArrayList<User> userlist) {
        this.users.addAll(userlist);
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object returnValue = null;
        User user = users.get(rowIndex);

        switch (columnIndex) {
            case 0:
                //returnValue = rowIndex + 1;
                returnValue = user.getUsername();
                break;
            case 1:
                returnValue = user.getPassword();
                break;
            case 2:
                returnValue = user.getFullname();
                break;
            case 3:
                returnValue = user.getFullname();
                break;

        }

        return returnValue;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);

        switch (columnIndex) {
            case 0:
                user.setUsername((String) value);
                break;
            case 1:
                user.setPassword((String) value);
                break;
            case 2:
                user.setFullname((String) value);
                break;
            case 3:
                user.setUsername((String) value);
                break;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }
}
