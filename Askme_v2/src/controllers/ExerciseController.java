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

    private List<Exercise> exerciseList;
    private Classes classes;

    @PostConstruct
    public void init(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();
        classname = request.getParameter("classname");
        username = request.getParameter("username");

        List<Classes> list = classesSessionBean.getClasses(classname);
        classes = list.get(0);
        exerciseList = exerciseSessionBean.getExercises(classes.getClassId());
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
        exercise.setExerciseId(exerciseList.size() + 1);
        exercise.setId_state(exerciseStateList.get(0).getStateId());

        exerciseSessionBean.addExercise(exercise);
        return "SubmitExercise.xhtml";
    }

    public String exerciseIdToString(Exercise exercise){
        Logger log = Logger.getLogger(ClassesController.class.getName());
        log.info("Exercise: " + exercise.getExerciseId());
        return Integer.toString(exercise.getExerciseId());
    }
}
