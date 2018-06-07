/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;

public class ExercisePK implements Serializable {

    protected String age;
    protected int year;
    protected String language;
    protected int number;

    public ExercisePK() {

    }

    public ExercisePK(String Age, int Year, String Language, int Number) {
        this.age = Age;
        this.year = Year;
        this.language = Language;
        this.number = Number;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ExercisePK)) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        ExercisePK that = (ExercisePK) object;
        return year == that.year
                && number == that.number
                && java.util.Objects.equals(age, that.age)
                && java.util.Objects.equals(language, that.language);
    }

    public int hashCode() {

        return java.util.Objects.hash(super.hashCode(), age, year, language, number);
    }
}
