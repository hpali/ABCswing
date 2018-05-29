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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Function;
import object.Role;
import object.RoleFunction;

/**
 *
 * @author Admin
 */
public class RoleFunctionsDAO extends AbstractDao {

    public RoleFunctionsDAO(DbConfig config) {
        super(config);
    }

    public RoleFunctionsDAO(String databaseurl, String username, String password) {
        super(databaseurl, username, password);
    }

    public Vector<RoleFunction> getAllRoleFunction() {
        ResultSet crs = null;

        Vector<RoleFunction> roleFunctions = new Vector<RoleFunction>();
        PreparedStatement allRoleFunctions = null;
        try {
            allRoleFunctions = getConnection().prepareStatement("SELECT * FROM rolefunction");
            crs = allRoleFunctions.executeQuery();
            while (crs.next()) {
                RoleFunction rf = new RoleFunction();
                roleFunctions.add(rf);
            }
            allRoleFunctions.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (allRoleFunctions != null) {
                    allRoleFunctions.close();
                }
                if (crs != null) {
                    crs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roleFunctions;
    }

    public Vector<String> getFunctionsToRle(String rolename) {

        ResultSet crs = null;
        Vector<String> functions = new Vector<String>();

        PreparedStatement statementAuthoritys = null;
        try {
            statementAuthoritys = getConnection().prepareStatement(
                    "SELECT f.name  \n"
                    + "FROM role AS r\n"
                    + "join rolefunction AS uf ON r.id = uf.role_id  \n"
                    + "join function AS f ON uf.function_id = f.id \n"
                    + "WHERE  r.name = ?");

            statementAuthoritys.setString(1, rolename);
            crs = statementAuthoritys.executeQuery();
            while (crs.next()) {
                functions.add(crs.getString("f.name"));
            }
            statementAuthoritys.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (statementAuthoritys != null) {
                    statementAuthoritys.close();
                }
                if (crs != null) {
                    crs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return functions;
    }

    public void doRolFuncInsert(Role role, Vector<Function> functions) {
        System.out.println("ajaj");
        Vector<RoleFunction> allrfs = getAllRoleFunction();
        for (Function function : functions) {
            RoleFunction rf = new RoleFunction(role.getId(), function.getId());
            if (!allrfs.contains(rf)) {
                PreparedStatement usersQueryINSERT = null;
                try {
                    String sqlInsert = "Insert INTO rolefunction (role_id, function_id) VALUES (?,?)";
                    String sqlInsertWhere = "Insert INTO rolefunction (role_id, function_id) "
                            + " SELECT role_id, function_id FROM dual "
                            + "WHERE NOT EXISTS (SELECT * FROM (role_id,function_id) "
                            + " rolefunction WHERE  role_id = ? AND function_id = ? "
                            + "VALUES (?,?))";
                    usersQueryINSERT = getConnection().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                    usersQueryINSERT.setInt(1, role.getId());
                    usersQueryINSERT.setInt(2, function.getId());

                    usersQueryINSERT.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (usersQueryINSERT != null) {
                        try {
                            usersQueryINSERT.close();
                        } catch (SQLException ex) {
                        }
                    }
                }
            }
        }

    }
}
