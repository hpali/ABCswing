/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;


/**
 *
 * @author Admin
 */
public class RoleFunction {

    private int role_id;
    private int funtion_id;

    public RoleFunction() {
    }

    public RoleFunction(int role_id, int funtion_id) {
        this.role_id = role_id;
        this.funtion_id = funtion_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getFuntion_id() {
        return funtion_id;
    }

    public void setFuntion_id(int funtion_id) {
        this.funtion_id = funtion_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoleFunction other = (RoleFunction) obj;
        if (this.role_id != other.role_id
                && this.funtion_id != other.funtion_id) {
            return false;
        }

        return true;
    }

}
