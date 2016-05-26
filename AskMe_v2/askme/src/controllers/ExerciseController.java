package controllers;

import entities.ExerciseEntity;
import sessionBeans.ExerciseSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named(value="exerciseController")
@RequestScoped
public class ExerciseController {
    @EJB
    ExerciseSessionBean exerciseSessionBean;

    public List<ExerciseEntity> getExerciseList(int exercise_id) {
        return exerciseSessionBean.getExercises(exercise_id);
    }

    public List<ExerciseEntity> getExerciseList(int classId, int exercise_id) {
        return exerciseSessionBean.getExercises(classId, exercise_id);
    }
}
