/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Role;

/**
 *
 * @author Admin
 */
public class RoleDAO extends AbstractDao {

    public RoleDAO(DbConfig config) {
        super(config);
    }

    public RoleDAO(String databaseurl, String username, String password) {
        super(databaseurl, username, password);
    }

    public boolean isIndexExist(int index) {
        boolean bExist = false;
        try {
            PreparedStatement allRoles = getConnection().prepareStatement("SELECT * FROM role WHERE name = " + index);
            ResultSet crs = allRoles.executeQuery();
            bExist = crs.first();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bExist;
    }

    public Role getRole(String name) {
        Role role = null;
        boolean roleExist = false;
        PreparedStatement prepStatement = null;
        ResultSet crs = null;
        try {
            prepStatement = getConnection().prepareStatement("SELECT * FROM role WHERE name = ?");
            prepStatement.setString(1, name);

            crs = prepStatement.executeQuery();
            roleExist = crs.first();
            if (roleExist) {
                role = new Role(crs.getInt("id"), crs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
                if (crs != null) {
                    crs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return role;
    }

    public ArrayList<Role> getAllRoles() {
        ResultSet crs = null;
        ArrayList<Role> roles = new ArrayList<Role>();
        PreparedStatement allRoles = null;
        try {
            allRoles = getConnection().prepareStatement("SELECT * FROM role");
            crs = allRoles.executeQuery();
            while (crs.next()) {
                Role r = new Role(crs.getString("NAME"));
                roles.add(r);
            }
            allRoles.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (allRoles != null) {
                    allRoles.close();
                }
                if (crs != null) {
                    crs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

    public Vector<Role> getRoleVector() {
        ResultSet crs = null;
        Vector<Role> roles = new Vector<Role>();
        PreparedStatement allRoles = null;
        try {
            allRoles = getConnection().prepareStatement("SELECT * FROM role");
            crs = allRoles.executeQuery();
            while (crs.next()) {
                Role r = new Role(crs.getString("NAME"));
                roles.add(r);
            }
            allRoles.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (allRoles != null) {
                    allRoles.close();
                }
                if (crs != null) {
                    crs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

    public boolean existRole(Role newrole) {
        ArrayList<Role> roles = getAllRoles();
        for (Role role : roles) {
            if (newrole.equals(role)) {
                System.out.println("VAN ILYEN ROLE");
                return true;
            }
        }
        return false;
    }

    public void doRoleInsert(Role role) {
        if (!existRole(role)) {
            PreparedStatement rolesQueryINSERT = null;
            try {
                String Query = "Insert INTO role (name) VALUES (?)";
                rolesQueryINSERT = getConnection().prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
                rolesQueryINSERT.setString(1, role.getName());

                ResultSet generatedKey = rolesQueryINSERT.getGeneratedKeys();
                if (generatedKey.next()) {
                    role.setName(generatedKey.getString(1));
                }
                rolesQueryINSERT.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (rolesQueryINSERT != null) {
                    try {
                        rolesQueryINSERT.close();
                    } catch (SQLException ex) {
                    }
                }
            }
        }
    }

    public void doRoleUPDATE(Role role) {
        PreparedStatement roleQueryUPDATE = null;
        try {
            String Query = "UPDATE role SET name=? WHERE name=?";
            roleQueryUPDATE = getConnection().prepareStatement(Query);
            roleQueryUPDATE.setString(1, role.getName());
            roleQueryUPDATE.executeUpdate();
            roleQueryUPDATE.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (roleQueryUPDATE != null) {
                try {
                    roleQueryUPDATE.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void doRoleDELETE(Role role) {
        PreparedStatement roleQueryDELETE = null;
        try {
            String Query = "DELETE FROM role WHERE name =?";
            roleQueryDELETE = getConnection().prepareStatement(Query);
            roleQueryDELETE.setString(1, role.getName());
            roleQueryDELETE.executeUpdate();
            roleQueryDELETE.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (roleQueryDELETE != null) {
                try {
                    roleQueryDELETE.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
