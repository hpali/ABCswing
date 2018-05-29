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
import object.Function;

/**
 *
 * @author Admin
 */
public class FunctionDAO extends AbstractDao {

    public FunctionDAO(DbConfig config) {
        super(config);
    }

    public FunctionDAO(String databaseurl, String username, String password) {
        super(databaseurl, username, password);
    }

    public boolean isIndexExist(int index) {
        boolean bExist = false;
        try {
            PreparedStatement allFunctions = getConnection().prepareStatement("SELECT * FROM function WHERE name = " + index);
            ResultSet crs = allFunctions.executeQuery();
            bExist = crs.first();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bExist;
    }

    public ArrayList<Function> getAllFunctions() {
        ResultSet crs = null;
        ArrayList<Function> functions = new ArrayList<Function>();
        PreparedStatement allFunctions = null;
        try {
            allFunctions = getConnection().prepareStatement("SELECT * FROM function");
            crs = allFunctions.executeQuery();
            while (crs.next()) {
                Function f = new Function(crs.getString("NAME"));
                functions.add(f);
            }
            allFunctions.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (allFunctions != null) {
                    allFunctions.close();
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

    public Vector<Function> getFunctionVector() {
        ResultSet crs = null;
        Vector<Function> functions = new Vector<Function>();
        PreparedStatement allFunctions = null;
        try {
            allFunctions = getConnection().prepareStatement("SELECT * FROM function");
            crs = allFunctions.executeQuery();
            while (crs.next()) {
                Function f = new Function(crs.getString("NAME"));
                functions.add(f);
            }
            allFunctions.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (allFunctions != null) {
                    allFunctions.close();
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

    boolean existFunction(Function newFunction) {
        ArrayList<Function> functions = getAllFunctions();
        for (Function f : functions) {
            if (newFunction.equals(f)) {
                return true;
            }
        }
        return false;
    }

    public void doFunctionInsert(Function function) {
        if (!existFunction(function)) {
            PreparedStatement functionQueryINSERT = null;
            try {
                String Query = "Insert INTO function (name) VALUES (?)";
                functionQueryINSERT = getConnection().prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
                functionQueryINSERT.setString(1, function.getName());

                ResultSet generatedKey = functionQueryINSERT.getGeneratedKeys();
                if (generatedKey.next()) {
                    function.setName(generatedKey.getString(1));
                }
                functionQueryINSERT.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (functionQueryINSERT != null) {
                    try {
                        functionQueryINSERT.close();
                    } catch (SQLException ex) {
                    }
                }
            }
        }
    }

    public Function getFunction(String name) {
        Function function = null;
        boolean functionExist = false;
        PreparedStatement prepStatement = null;
        ResultSet crs = null;
        try {
            prepStatement = getConnection().prepareStatement("SELECT * FROM function WHERE name = ?");

            prepStatement.setString(1, name);

            crs = prepStatement.executeQuery();
            functionExist = crs.first();
            if (functionExist) {
                function = new Function(crs.getInt("id"), crs.getString("name"));
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

        return function;
    }

    public void doFunctionUPDATE(Function function) {
        PreparedStatement functionQueryUPDATE = null;
        try {
            String Query = "UPDATE role SET name=? WHERE name=?";
            functionQueryUPDATE = getConnection().prepareStatement(Query);
            functionQueryUPDATE.setString(1, function.getName());
            functionQueryUPDATE.executeUpdate();
            functionQueryUPDATE.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (functionQueryUPDATE != null) {
                try {
                    functionQueryUPDATE.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void doFunctionDELETE(Function function) {
        PreparedStatement functionQueryDELETE = null;
        try {
            String Query = "DELETE FROM function WHERE name =?";
            functionQueryDELETE = getConnection().prepareStatement(Query);
            functionQueryDELETE.setString(1, function.getName());
            functionQueryDELETE.executeUpdate();
            functionQueryDELETE.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (functionQueryDELETE != null) {
                try {
                    functionQueryDELETE.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
