package controllers;

import entities.ExerciseState;
import sessionBeans.ExerciseStateSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named (value="exerciseStateController")
@RequestScoped
public class ExerciseStateController {
    @EJB
    ExerciseStateSessionBean exerciseStateSessionBean;

    public List<ExerciseState> getExerciseStateList(){
        return exerciseStateSessionBean.getExerciseState();
    }

    public List<ExerciseState> getExerciseStateList(int stateId){
        return exerciseStateSessionBean.getExerciseState();
    }

    public int getExerciseStateId(String state){
        List<ExerciseState> exerciseStateEntities =
        exerciseStateSessionBean.getExerciseState(state);
        return exerciseStateEntities.get(0).getStateId();
    }
}
