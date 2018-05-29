/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Admin
 */
public class DBManager {

    private static DBManager dbManager;

    public static DBManager getDbManager() {
        return dbManager;
    }

    public static void setDbManager(DBManager aDbManager) {
        dbManager = aDbManager;
    }
    private UserDAO userDao;
    private UserrolesDAO userrolesDAO;
    private RoleDAO roleDAO;
    private FunctionDAO functionDAO;
    private RoleFunctionsDAO roleFunctionsDAO;
    private ExerciseDAO exerciseDAO;

    private DBManager() {
    }

    private DBManager(DbConfig dbConfig) {
        userDao = (UserDAO) DaoFactory.getDao(DaoTypeEnum.USER, dbConfig);
        userrolesDAO = (UserrolesDAO) DaoFactory.getDao(DaoTypeEnum.USERROLES, dbConfig);
        roleDAO = (RoleDAO) DaoFactory.getDao(DaoTypeEnum.ROLE, dbConfig);
        functionDAO = (FunctionDAO) DaoFactory.getDao(DaoTypeEnum.FUNCTION, dbConfig);
        roleFunctionsDAO = (RoleFunctionsDAO) DaoFactory.getDao(DaoTypeEnum.ROLEFUNCTION, dbConfig);
        exerciseDAO = (ExerciseDAO) DaoFactory.getDao(DaoTypeEnum.EXERCISES, dbConfig);

    }

    public static synchronized DBManager getInstance(DbConfig config) {
        if (dbManager == null) {
            dbManager = new DBManager(config);
        }
        return dbManager;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public UserrolesDAO getUserrolesDAO() {
        return userrolesDAO;
    }

    public void setUserrolesDAO(UserrolesDAO userrolesDAO) {
        this.userrolesDAO = userrolesDAO;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public FunctionDAO getFunctionDAO() {
        return functionDAO;
    }

    public void setFunctionDAO(FunctionDAO functionDAO) {
        this.functionDAO = functionDAO;
    }

    public RoleFunctionsDAO getRoleFunctionsDAO() {
        return roleFunctionsDAO;
    }

    public void setRoleFunctionsDAO(RoleFunctionsDAO roleFunctionsDAO) {
        this.roleFunctionsDAO = roleFunctionsDAO;
    }

    public ExerciseDAO getExerciseDAO() {
        return exerciseDAO;
    }

    public void setExerciseDAO(ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    
}
