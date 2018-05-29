/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import dao.RoleDAO;
import dao.UserrolesDAO;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import object.MouseAdapterForUserPanel;
import object.Role;
import object.User;
import object.Userroles;

/**
 *
 * @author Admin
 */
public class UserPanel extends javax.swing.JPanel {

    private boolean DEBUG = false;
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Userroles> userroles = new ArrayList<Userroles>();
    ArrayList<Role> roles = new ArrayList<Role>();
    UserrolesDAO userrolesDAO;
    JComboBox box = new JComboBox();

    String sUsername;
    String sPassword;
    String sAuthirity;
    JTable table;

    int width;
    int height;

    public UserPanel(ArrayList<User> luserek, ArrayList<Role> roles, UserrolesDAO userrolesdao, int width, int height) {
        this.users = luserek;
        this.userrolesDAO = userrolesdao;
        this.roles = roles;
        this.width = width;
        this.height = height;
        initComponents();
        afterInit();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public String getsUsername() {
        return sUsername;
    }

    public void setsUsername(String sUsername) {
        this.sUsername = sUsername;
    }

    private void afterInit() {
        //super(new GridLayout(1, 0));
        for (Role role : roles) {
            box.addItem(role);
        }
        String[] columnNames = new String[]{"User", "Password", "Full Name", "Role"};
        /*{propLanguge.getProperty("username"),
         propLanguge.getProperty("password"),
         propLanguge.getProperty("fullname")};
         */
        /**
         * *******************************
         */

        /**
         * ******************************
         */
        String username = "";
        String[][] rowData = new String[users.size()][4];
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    rowData[i][j] = users.get(i).getUsername();
                } else if (j == 1) {
                    rowData[i][j] = users.get(i).getPassword();
                } else if (j == 2) {
                    rowData[i][j] = users.get(i).getFullname();
                } else if (j == 3) {
                    username = userrolesDAO.getRolesName(users.get(i));
                    rowData[i][j] = username;
                }
            }
        }

        // UserTable table = new UserTable(users);
        table = new JTable(new DefaultTableModel(rowData, columnNames));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        double h = 0.8 * height;
        //  table.setRowHeight((int)h);
        TableColumnModel tableColumnModel = table.getColumnModel();

        for (int i = 0; i < 4; i++) {
            tableColumnModel.getColumn(i).setPreferredWidth(300);
        }
        table.setColumnModel(tableColumnModel);
        setUpSportColumn(table, table.getColumnModel().getColumn(3));

        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // itt tudom beállítani,hogy hány sort töröljön ki

        //  table.setSize(getPreferredSize());
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (DEBUG) {
                    printDebugData(table);
                }
                JTable target = (JTable) e.getSource();
                MouseAdapterForUserPanel MyMause = new MouseAdapterForUserPanel(users);
                sUsername = MyMause.getUserName(e);
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(getPreferredSize());
        //Add the scroll pane to this panel.
        add(scrollPane);
        //  setSize(800, 900);
        setVisible(true);
        revalidate();
        table.setSize(700, 700);
        repaint();
    }

    public void setUsers(ArrayList<User> luserek) {
        this.users = luserek;
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    public String deleteSelectedUser() {
        int selectedRow = table.getSelectedRow();
        String userName = "";
        if (selectedRow != -1) {
            int row = table.convertRowIndexToModel(selectedRow);
            userName = (String) table.getModel().getValueAt(row, 0);
            ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
            System.out.println("Torlom:" + userName);
            table.repaint();
        }
        return userName;
    }

    /*   public Dimension getPreferredSize() {
     Dimension d = super.getPreferredSize();
     return new Dimension(100, d.height);
     }*/
    public User changeSelectedUser() {
        int selectedRow = table.getSelectedRow();
        User user = new User();
        if (selectedRow != -1) {
            int row = table.convertRowIndexToModel(selectedRow);
            table.repaint();
            user = new User((String) table.getModel().getValueAt(row, 0), (String) table.getModel().getValueAt(row, 1), (String) table.getModel().getValueAt(row, 2));
            //!!!   user.setsAuthority(userrolesDAO.getAuthorityArray((String) table.getModel().getValueAt(row, 3)));
        }
        return user;
    }

    public int getUserrolesIDfromAuthority() {
        int id = 2;
        String sAuthorit = "";
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int row = table.convertRowIndexToModel(selectedRow);
            sAuthorit = (String) table.getModel().getValueAt(row, 3);
        }
        id = userrolesDAO.getRolesID(sAuthorit);
        return id;
    }

    public void setUpSportColumn(JTable table, TableColumn sportColumn) {
        //Set up the editor for the sport cells.

        JComboBox comboBox = new JComboBox();
        for (Role role : roles) {
            comboBox.addItem(role.toString());
        }
        comboBox.setVisible(true);
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
