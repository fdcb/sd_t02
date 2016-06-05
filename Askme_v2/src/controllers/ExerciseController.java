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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

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
    private String exerciseId;

    private List<Exercise> exerciseList;
    private Exercise exercise = new Exercise();
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

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getUsername(){
        return username;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String addExercise(String description){
        exercise.setClassId(classes.getClassId());
        exercise.setUsername(username);
        exercise.setDescription(description);
        exercise.setExerciseId(exerciseList.size() + 1);

        List<ExerciseState> exerciseStateList = exerciseStateSessionBean
                .getExerciseState(ExerciseState.OPEN);
        exercise.setId_state(exerciseStateList.get(0).getStateId());

        exerciseSessionBean.addExercise(exercise);
        return "SubmitExercise.xhtml";
    }

    public String exerciseIdToString(){
        exerciseId = Integer.toString(exercise.getExerciseId());
        return "SubmitSolution.xhtml";
    }
}
