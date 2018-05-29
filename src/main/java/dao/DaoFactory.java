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
public class DaoFactory {

    public static IDao getDao(DaoTypeEnum dbType, DbConfig config) {
        if (dbType.equals(DaoTypeEnum.USER)) {
            return new UserDAO(config);
        } else if (dbType.equals(DaoTypeEnum.USERROLES)) {
            return new UserrolesDAO(config);
        } else if (dbType.equals(DaoTypeEnum.ROLE)) {
            return new RoleDAO(config);
        } else if (dbType.equals(DaoTypeEnum.FUNCTION)) {
            return new FunctionDAO(config);
        } else if (dbType.equals(DaoTypeEnum.ROLEFUNCTION)) {
            return new RoleFunctionsDAO(config);
        }else if (dbType.equals(DaoTypeEnum.EXERCISES)) {
            return new ExerciseDAO(config);
        }
        return null;
    }
}
