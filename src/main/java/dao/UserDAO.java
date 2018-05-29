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
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.User;
import util.Utility;

/**
 *
 * @author Admin
 */
public class UserDAO extends AbstractDao {

   
    public UserDAO(DbConfig config) {
        super(config);
    }

    public UserDAO(String databaseurl, String username, String password) {
        super(databaseurl, username, password);
    }

    public boolean isIndexExist(int index) {
        boolean bExist = false;
        try {
            PreparedStatement allUser = getConnection().prepareStatement("SELECT * FROM user where USERNAME = " + index);
            ResultSet crs = allUser.executeQuery();
            bExist = crs.first();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bExist;
    }

    public ArrayList<User> getAllUsers() {
        ResultSet crs = null;
        ArrayList<User> luserek = new ArrayList<User>();
        PreparedStatement allUsers = null;
        try {
            allUsers = getConnection().prepareStatement("SELECT * FROM user");
            crs = allUsers.executeQuery();
            while (crs.next()) {
                User c = new User(crs.getString("USERNAME"), crs.getString("PASSWORD"), crs.getString("NAME"));
                luserek.add(c);
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
        return luserek;
    }

    public boolean existUser(User newuser) {
        ArrayList<User> users = getAllUsers();
        for (User user : users) {
            if (newuser.equals(user)) {
                System.out.println("BENNE VAN");
                return true;
            }
        }
        System.out.println("NINCS BENNE");
        return false;
    }

    public void doUserInsert(User user) {
        if (!existUser(user)) {
            PreparedStatement usersQueryINSERT = null;
            try {

                String Query = "Insert INTO user (username, password,name) VALUES (?,md5(?),?)";
                usersQueryINSERT = getConnection().prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
                usersQueryINSERT.setString(1, user.getUsername());
                usersQueryINSERT.setString(2, user.getPassword());
                usersQueryINSERT.setString(3, user.getFullname());

                ResultSet generatedKey = usersQueryINSERT.getGeneratedKeys();
                if (generatedKey.next()) { //???
                    user.setUsername(generatedKey.getString(1));
                }
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

    public void doUserUPDATE(User user) {
        PreparedStatement usersQueryUPDATE = null;
        try {
            String Query = "UPDATE USER SET username=?,password=md5(?),name=? WHERE id=?";
            usersQueryUPDATE = getConnection().prepareStatement(Query);
            usersQueryUPDATE.setInt(1, user.getId());
            usersQueryUPDATE.setString(2, user.getUsername());
            usersQueryUPDATE.setString(3, user.getPassword());
            usersQueryUPDATE.setString(4, user.getFullname());
            //   usersQueryUPDATE.setString(4, user.getUsername());
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

    public void doUserDELETE(User user) {
        PreparedStatement felhasznaloQueryDELETE = null;
        try {
            String Query = "DELETE FROM user WHERE username =?";
            felhasznaloQueryDELETE = getConnection().prepareStatement(Query);
            felhasznaloQueryDELETE.setString(1, user.getUsername());
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

    /**
     * itt van az MD5
     *
     * @param username
     * @param password
     * @return
     */
    public boolean userExist(User user) {
        boolean bExist = false;
        PreparedStatement prepStatement = null;
        ResultSet crs = null;
        try {
            prepStatement = getConnection().prepareStatement("SELECT * FROM user WHERE username = ?");
            prepStatement.setString(1, user.getUsername());
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

    public User getUser(String username) {
        User userAktiv = null;
        boolean bExist = false;
        PreparedStatement prepStatement = null;
        ResultSet crs = null;
        try {
            prepStatement = getConnection().prepareStatement("SELECT * FROM user WHERE username = ?");
            prepStatement.setString(1, username);

            crs = prepStatement.executeQuery();
            bExist = crs.first();
            if (bExist) {
                userAktiv = new User(crs.getInt("id"),
                        crs.getString("username"),
                        crs.getString("password"),
                        crs.getString("name"));
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

        return userAktiv;
    }

    public Vector<String> getAuthority(String sUsername) {

        ResultSet crs = null;
        Vector<String> sAuthority = new Vector<String>();
        PreparedStatement statementAuthoritys = null;
        try {
            statementAuthoritys = getConnection().prepareStatement(
                    "SELECT f.name  \n"
                    + "FROM user AS u\n"
                    + "join userroles AS ur ON u.id = ur.user_id  \n"
                    + "join role AS r ON ur.role_id = r.id \n"
                    + "join rolefunction AS rf  ON r.id = rf.role_id \n"
                    + "join function AS f ON rf.function_id = f.id \n"
                    + "WHERE  u.username = ?");

            statementAuthoritys.setString(1, sUsername);
            crs = statementAuthoritys.executeQuery();
            while (crs.next()) {
                sAuthority.add(crs.getString("f.name"));
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

        return sAuthority;
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
}
