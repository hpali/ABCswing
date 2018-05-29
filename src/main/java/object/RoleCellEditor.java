/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Admin
 */
public class RoleCellEditor extends AbstractCellEditor
        implements TableCellEditor, ActionListener {

    private Role role;
    private ArrayList<Role> roles;

    public RoleCellEditor(ArrayList<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Object getCellEditorValue() {
        return this.role;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<Role> comboRole = (JComboBox<Role>) e.getSource();
        this.role = (Role) comboRole.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof Role) {
            this.role = (Role) value;
        }

        JComboBox<Role> comboRole = new JComboBox<Role>();

        for (Role role : roles) {
            comboRole.addItem(role);
        }

        comboRole.setSelectedItem(role);
        comboRole.addActionListener(this);

        if (isSelected) {
            comboRole.setBackground(table.getSelectionBackground());
        } else {
            comboRole.setBackground(table.getSelectionForeground());
        }

        return comboRole;
    }

}
