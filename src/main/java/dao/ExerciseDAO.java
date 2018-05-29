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
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Exercise;

/**
 *
 * @author Admin
 */
public class ExerciseDAO extends AbstractDao {

    public ExerciseDAO(DbConfig config) {
        super(config);
    }

    public ExerciseDAO(String databaseurl, String username, String password) {
        super(databaseurl, username, password);
    }

    public ArrayList<Exercise> getExercises() {
        ResultSet crs = null;
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();
        PreparedStatement allExercises = null;
        try {
            allExercises = getConnection().prepareStatement("SELECT * FROM exercises");
            crs = allExercises.executeQuery();
            while (crs.next()) {
                Exercise e = new Exercise(
                        crs.getInt("ID"),
                        crs.getString("AGE"),
                        crs.getInt("YEAR"),
                        crs.getString("LANGUAGE"),
                        crs.getInt("NUMBER"),
                        crs.getString("ANSWER"),
                        crs.getString("URL"));
                exercises.add(e);
            }
            allExercises.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (allExercises != null) {
                    allExercises.close();
                }
                if (crs != null) {
                    crs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exercises;
    }

    public boolean existExercise(Exercise newexercise) {
        ArrayList<Exercise> exicercises = getExercises();
        for (Exercise exercise : exicercises) {
            if (newexercise.equals(exercise)) {
                System.out.println("BENNE VAN");
                return true;
            }
        }
        System.out.println("NINCS BENNE");
        return false;
    }

    public void doExerciseInsert(Exercise exercise) {
 
            PreparedStatement exerciseQueryINSERT = null;
            try {

                String Query = "Insert INTO exercises (age, year,language,number,answer,url) VALUES (?,?,?,?,?,?)";
                exerciseQueryINSERT = getConnection().prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
                exerciseQueryINSERT.setString(1, exercise.getAge());
                exerciseQueryINSERT.setInt(2, exercise.getYear());
                exerciseQueryINSERT.setString(3, exercise.getLanguage());
                exerciseQueryINSERT.setInt(4, exercise.getNumber());
                exerciseQueryINSERT.setString(5, exercise.getAnswer());
                exerciseQueryINSERT.setString(6, exercise.getUrl());

                exerciseQueryINSERT.executeUpdate();
                ResultSet generatedKey = exerciseQueryINSERT.getGeneratedKeys();
            if (generatedKey.next()) {
                exercise.setId(generatedKey.getInt(1));
            }
            } catch (SQLException ex) {
                Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (exerciseQueryINSERT != null) {
                    try {
                        exerciseQueryINSERT.close();
                    } catch (SQLException ex) {
                    }
                }
            }
    }

    public void doExerciseUPDATE(Exercise exercise) { // itt érdemes id alapján keresni és törölni
        PreparedStatement exerciseQueryUPDATE = null;
        try {
            String Query = "UPDATE USER SET age=?,year=?,language=?, number=?,answer=?,url=? "
                    + "WHERE age=? , year=?, language=?,number=?";
            exerciseQueryUPDATE = getConnection().prepareStatement(Query);
            exerciseQueryUPDATE.setString(1, exercise.getAge());
            exerciseQueryUPDATE.setInt(2, exercise.getYear());
            exerciseQueryUPDATE.setString(3, exercise.getLanguage());
            exerciseQueryUPDATE.setInt(4, exercise.getNumber());
            exerciseQueryUPDATE.setString(5, exercise.getAnswer());
            exerciseQueryUPDATE.setString(5, exercise.getUrl());
            exerciseQueryUPDATE.executeUpdate();
            exerciseQueryUPDATE.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (exerciseQueryUPDATE != null) {
                try {
                    exerciseQueryUPDATE.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void doExerciseDELETE(Exercise exercise) {  // itt érdemes id alapján keresni és törölni
        PreparedStatement exerciseQueryDELETE = null;
        try {
            String Query = "DELETE FROM exercises WHERE id = ?";
            exerciseQueryDELETE = getConnection().prepareStatement(Query);
            exerciseQueryDELETE.setInt(1, exercise.getId());
            exerciseQueryDELETE.executeUpdate();
            exerciseQueryDELETE.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (exerciseQueryDELETE != null) {
                try {
                    exerciseQueryDELETE.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean exerciseExist(Exercise exercise) {
        boolean bExist = false;
        PreparedStatement prepStatement = null;
        ResultSet crs = null;
        try {
            prepStatement = getConnection().prepareStatement("SELECT * FROM exercises WHERE"
                    + " age = ?");
            prepStatement.setInt(1, exercise.getId());
            crs = prepStatement.executeQuery();
            bExist = crs.first();
        } catch (SQLException ex) {
            Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
                if (crs != null) {
                    crs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ExerciseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bExist;
    }

}
