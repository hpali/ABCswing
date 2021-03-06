/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import object.Exercise;

/**
 *
 * @author Admin
 */
@XmlRootElement(name = "exercises")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExercisesList {

    @XmlElement(name = "exercise")
    private ArrayList<Exercise> exercises = new ArrayList<>();

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

}
