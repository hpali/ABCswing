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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.User;
import object.Userroles;

/**
 *
 * @author Fm
 */
public class UserrolesDAO extends AbstractDao {

    public UserrolesDAO(DbConfig config) {
        super(config);
    }

    public UserrolesDAO(String databaseurl, String username, String password) {
        super(databaseurl, username, password);
    }

    public boolean isIndexExist(int index) {
        boolean bExist = false;
        try {
            PreparedStatement allUser = getConnection().prepareStatement("SELECT * FROM userroles where USERNAME = " + index);
            ResultSet crs = allUser.executeQuery();
            bExist = crs.first();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bExist;
    }

    public ArrayList<Userroles> getAllUserroles() {
        ResultSet crs = null;
        ArrayList<Userroles> luserroles = new ArrayList<Userroles>();
        PreparedStatement allUsers = null;
        try {
            allUsers = getConnection().prepareStatement("SELECT * FROM userroles");
            crs = allUsers.executeQuery();
            while (crs.next()) {
                Userroles ur = new Userroles(crs.getInt("USER_ID"), crs.getInt("ROLE_ID"));
                luserroles.add(ur);
            }
            allUsers.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (allUsers != null) {
                    allUsers.close();
                }
                if (crs != null) {
                    crs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return luserroles;
    }

    public int getRolesID(String sRoles) {

        ResultSet crs = null;
        int iRoles = 2;
        PreparedStatement statementAuthoritys = null;
        try {
            statementAuthoritys = getConnection().prepareStatement(
                    "SELECT r.id \n"
                    + "FROM role AS r "
                    + "WHERE  r.name = ?;");
            statementAuthoritys.setString(1, sRoles);
            crs = statementAuthoritys.executeQuery();
            if (crs.next()) {
                iRoles = crs.getInt("r.id");
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

        return iRoles;
    }

    public String getRolesName(User user) {

        ResultSet crs = null;
        String sRoles = "";
        PreparedStatement statementAuthoritys = null;
        try {
            statementAuthoritys = getConnection().prepareStatement(
                    "SELECT r.name\n"
                    + "FROM user AS u \n"
                    + "join userroles AS ur ON u.id = ur.user_id\n"
                    + "join role AS r  ON ur.role_id = r.id\n"
                    + "WHERE  u.user_id = ?;");
            statementAuthoritys.setInt(1, user.getId());
            crs = statementAuthoritys.executeQuery();
            if (crs.next()) {
                sRoles = crs.getString("r.name");
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

        return sRoles;
    }

    public void doUserRolesInsert(Userroles userroles) {
        PreparedStatement usersrolesQueryINSERT = null;
        ResultSet generatedKey = null;
        try {
            String Query = "Insert INTO userroles (user_id,role_id) VALUES (?,?)";
            usersrolesQueryINSERT = getConnection().prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
            usersrolesQueryINSERT.setInt(1, userroles.getUser_id());
            usersrolesQueryINSERT.setInt(2, userroles.getRole_id());

            usersrolesQueryINSERT.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (usersrolesQueryINSERT != null) {
                try {
                    usersrolesQueryINSERT.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public String[] getAuthorityArray(String sAuthority) {
        ResultSet crs = null;
        List<String> listAuthority = new ArrayList<String>();
        PreparedStatement statementAuthoritys = null;
        try {
            statementAuthoritys = getConnection().prepareStatement(
                    "SELECT f.name \n"
                    + "FROM role AS r \n"
                    + "join rolefunction AS rf ON r.id = rf.roles_id\n"
                    + "join function AS f ON rf.id = f.id \n"
                    + "WHERE  r.name = ?;");

            statementAuthoritys.setString(1, sAuthority);
            crs = statementAuthoritys.executeQuery();
            while (crs.next()) {
                listAuthority.add(crs.getString("f.name"));
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

        return listAuthority.toArray(new String[listAuthority.size()]);
    }

    public void doUserRolesUPDATE(Userroles userroles) {
        PreparedStatement usersQueryUPDATE = null;
        try {

            String Query = "UPDATE userroles SET user_id=? ,role_id=?  WHERE user_id=? role_id=? ";
            usersQueryUPDATE = getConnection().prepareStatement(Query);
            usersQueryUPDATE.setInt(1, userroles.getUser_id());
            usersQueryUPDATE.setInt(2, userroles.getRole_id());
            usersQueryUPDATE.executeUpdate();
            usersQueryUPDATE.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (usersQueryUPDATE != null) {
                try {
                    usersQueryUPDATE.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void doUserRolesDELETE(Userroles userroles) {
        PreparedStatement felhasznaloQueryDELETE = null;
        try {
            String Query = "DELETE FROM userroles WHERE user_id = ?,role_id";
            felhasznaloQueryDELETE = getConnection().prepareStatement(Query);
            felhasznaloQueryDELETE.setInt(1, userroles.getUser_id());
            felhasznaloQueryDELETE.setInt(2, userroles.getUser_id());
            felhasznaloQueryDELETE.executeUpdate();
            felhasznaloQueryDELETE.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (felhasznaloQueryDELETE != null) {
                try {
                    felhasznaloQueryDELETE.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean userRolesExist(Userroles userroles) {
        boolean bExist = false;
        PreparedStatement prepStatement = null;
        ResultSet crs = null;
        try {
            //prepStatement = getConnection().prepareStatement("SELECT * FROM felhasznalok WHERE username = ? AND md5(password) = ?");
            prepStatement = getConnection().prepareStatement("SELECT * FROM userroles WHERE user_id = ? AND role_id = ?");
            prepStatement.setInt(1, userroles.getUser_id());
            prepStatement.setInt(2, userroles.getRole_id());
            crs = prepStatement.executeQuery();
            bExist = crs.first();
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
        return bExist;
    }

    public String[] getAuthority(String sUsername) {

        ResultSet crs = null;
        List<String> sAuthority = new ArrayList<String>();
        String valami = new String();
        PreparedStatement statementAuthoritys = null;
        try {
            statementAuthoritys = getConnection().prepareStatement(
                    "SELECT r.name  \n"
                    + "FROM user AS u\n"
                    + "join userroles AS ur ON u.id = ur.user_id  \n"
                    + "join role AS r ON ur.role_id = r.id \n"
                    + "WHERE  u.username = ?");

            statementAuthoritys.setString(1, sUsername);
            crs = statementAuthoritys.executeQuery();

            if (crs.next()) {
                sAuthority.add(crs.getString("r.name"));
                valami = (crs.getString("r.name"));

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

        return sAuthority.toArray(new String[sAuthority.size()]);
    }

    private static boolean isPasswordCorrect(char[] input, char[] correctPassword) {
        boolean isCorrect = true;

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            for (int i = 0; i < input.length; i++) {
                if (input[i] != correctPassword[i]) {
                    isCorrect = false;
                }
            }
        }

        //Zero out the password.
        for (int i = 0; i < correctPassword.length; i++) {
            correctPassword[i] = 0;
        }

        return isCorrect;
    }

    public Userroles getUserrole(User user) {

        ResultSet crs = null;
        Userroles userroles = null;
        boolean userrolesExist = false;
        PreparedStatement statementAuthoritys = null;
        try {
            statementAuthoritys = getConnection().prepareStatement(
                    "SELECT r.name\n"
                    + "FROM user AS u \n"
                    + "join userroles AS ur ON u.id = ur.user_id\n"
                    + "join role AS r  ON ur.role_id = r.id\n"
                    + "WHERE  u.username = ?;");
            statementAuthoritys.setInt(1, user.getId());
            crs = statementAuthoritys.executeQuery();
            if (userrolesExist) {
                userroles = new Userroles(crs.getInt("user_id"), crs.getInt("role_id"));
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

        return userroles;
    }
}
