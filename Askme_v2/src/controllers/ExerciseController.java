package controllers;

import entities.Classes;
import entities.Exercise;
import entities.ExerciseState;
import sessionBeans.ClassesSessionBean;
import sessionBeans.ExerciseSessionBean;
import sessionBeans.ExerciseStateSessionBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named(value="exerciseController")
@ManagedBean
@ViewScoped
public class ExerciseController implements Serializable {
    @EJB
    ExerciseSessionBean exerciseSessionBean = new ExerciseSessionBean();
    @EJB
    ClassesSessionBean classesSessionBean = new ClassesSessionBean();
    @EJB
    ExerciseStateSessionBean exerciseStateSessionBean = new
            ExerciseStateSessionBean();

    private String classname;
    private String username;

    private List<Exercise> allExercises;
    private List<Exercise> exerciseList;
    private Classes classes;

    @PostConstruct
    public void init(){
        List<ExerciseState> exerciseStateList = exerciseStateSessionBean
                .getExerciseState(ExerciseState.CLOSED);
        int closeId = exerciseStateList.get(0).getStateId();
        exerciseStateList = exerciseStateSessionBean.getExerciseState
                (ExerciseState.OPEN);
        int openId = exerciseStateList.get(0).getStateId();

        HttpServletRequest request = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();
        classname = request.getParameter("classname");
        username = request.getParameter("username");
        String state = request.getParameter("state");

        List<Classes> list = classesSessionBean.getClasses(classname);
        classes = list.get(0);
        allExercises = exerciseSessionBean.getExercises(classes.getClassId());

        if (state != null){
            switch (state){
                case ExerciseState.CLOSED: exerciseList = exerciseSessionBean
                        .getExercisesByState(classes.getClassId(), closeId);
                    break;
                case ExerciseState.OPEN: exerciseList = exerciseSessionBean
                        .getExercisesByState(classes.getClassId(), openId); break;
                default: exerciseStateList = allExercises;
            }
        }
        else
            exerciseList = allExercises;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public String getUsername(){
        return username;
    }

    public String getClassname() {
        return classname;
    }

    public String addExercise(String description){
        Exercise exercise = new Exercise();
        List<ExerciseState> exerciseStateList = exerciseStateSessionBean
                .getExerciseState(ExerciseState.OPEN);

        exercise.setClassId(classes.getClassId());
        exercise.setUsername(username);
        exercise.setDescription(description);
        exercise.setExerciseId(allExercises.size() + 1);
        exercise.setId_state(exerciseStateList.get(0).getStateId());

        exerciseSessionBean.addExercise(exercise);
        return "SubmitExercise.xhtml";
    }

    public String exerciseIdToString(Exercise exercise){
        Logger log = Logger.getLogger(ClassesController.class.getName());
        log.info("Exercise: " + exercise.getExerciseId());
        String cenas =Integer.toString(exercise.getExerciseId());
        log.info("Exercise: string -> " + cenas);
        return cenas;
    }

    public String getClosedState(){
        return ExerciseState.CLOSED;
    }

    public String getOpenState(){
        return ExerciseState.OPEN;
    }
    public String getAllState(){
        return SolutionController.ALL;
    }
}
