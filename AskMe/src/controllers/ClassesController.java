package controllers;

import entities.Classes;
import entities.Exercise;
import entities.Solution;
import entities.SolutionState;
import sessionBeans.ClassesSessionBean;
import sessionBeans.ExerciseSessionBean;
import sessionBeans.SolutionSessionBean;
import sessionBeans.SolutionStateSessionBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

@Named (value="classesController")
@ManagedBean
@RequestScoped
public class ClassesController implements Serializable {
    @EJB
    ClassesSessionBean classesSessionBean = new ClassesSessionBean();
    @EJB
    ExerciseSessionBean exerciseSessionBean = new ExerciseSessionBean();
    @EJB
    SolutionStateSessionBean solutionStateSessionBean = new
            SolutionStateSessionBean();
    @EJB
    SolutionSessionBean solutionSessionBean = new SolutionSessionBean();

    private List<Classes> classList;
    private Classes classes = new Classes();

    public List<Classes> getClassList() {
        return classList;
    }

    public Classes getClasses() {
        return classes;
    }

    @PostConstruct
    public void init(){
        classList = classesSessionBean.getClasses();
    }

    public String addClasses(String name){
        classes.setName(name);
        List<Classes> classesList1 = classesSessionBean.getClasses(classes
                .getName());
        if(!classesList1.isEmpty())
            return "listClasses.xhtml";
        if(classes.getName() == null) {
            return "listClasses.xhtml";
        }
        classes.setClassId(classList.size() + 1);
        classesSessionBean.addClass(classes);
        return "listClasses.xhtml";
    }

    public int getClassesNumber(){
        List<Classes> classesEntities = classesSessionBean.getClasses();
        return classesEntities.size();
    }

    public String goToExerciseList(){
        return "SubmitExercise.xhtml";
    }

    public boolean isUnseenExercises(){
        List<Exercise> exerciseList = exerciseSessionBean.getExercisesByUser
                (UserController.username);

        List<SolutionState> solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.UNSEEN);
        int unseenId = solutionStateList.get(0).getStateId();

        for(int i = 0; i < exerciseList.size(); i++) {
            List<Solution> solutionList = solutionSessionBean
                    .getSolutionsByState(exerciseList.get(i).getClassId(),
                            exerciseList.get(i).getExerciseId(), unseenId);
            if(!solutionList.isEmpty())
                return true;
        }

        return false;
    }
}

