/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import util.Utility;

/**
 *
 * @author Admin
 */
public abstract class AbstractDao implements IDao {

    private DbConfig dbConfig;
    private Connection connection;

    public AbstractDao(String databaseurl, String username, String password) {
        dbConfig = new DbConfig(databaseurl, username, password);
    }

    public AbstractDao(DbConfig config) {
        this.dbConfig = config;
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(dbConfig.getDatabaseurl(), dbConfig.getUsername(), dbConfig.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    
}
