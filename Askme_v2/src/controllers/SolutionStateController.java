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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named (value="solutionStateController")
@ManagedBean
@ViewScoped
public class SolutionStateController implements Serializable{
    @EJB
    SolutionSessionBean solutionSessionBean = new SolutionSessionBean();
    @EJB
    ClassesSessionBean classesSessionBean = new ClassesSessionBean();
    @EJB
    SolutionStateSessionBean solutionStateSessionBean = new
            SolutionStateSessionBean();
    @EJB
    ExerciseSessionBean exerciseSessionBean = new ExerciseSessionBean();

    private String classname;
    private String username;

    public String getExerciseId() {
        return exerciseId;
    }

    public String getSolutionIdS() {
        return solutionIdS;
    }

    private String solutionIdS;
    private String exerciseId;
    private int solutionId;
    private boolean author;
    private int unseenId;

    private Solution solution = new Solution();
    private Exercise exercise = new Exercise();

    @PostConstruct
    public void init(){
        List<SolutionState> solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.UNSEEN);
        unseenId = solutionStateList.get(0).getStateId();
        HttpServletRequest request = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();
        classname = request.getParameter("classname");
        username = request.getParameter("username");
        exerciseId = request.getParameter("exerciseId");
        solutionIdS= request.getParameter("solutionId");
        solutionId = Integer.parseInt(solutionIdS);

        List<Classes> classesList = classesSessionBean.getClasses(classname);
        List<Exercise> exerciseList = exerciseSessionBean.getExercises
                (classesList.get(0).getClassId(), Integer.parseInt(exerciseId));
        exercise = exerciseList.get(0);

        List<Solution> solutionsList = solutionSessionBean.getSolutions
                (classesList.get(0).getClassId(),Integer.parseInt(exerciseId)
                        , solutionId);
        solution = solutionsList.get(0);

        author = exercise.getUsername().equals(username) && (solution
                .getId_state() == unseenId);
    }

    public String getClassname() {
        return classname;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthor() {
        return author;
    }

    public Solution getSolution() {
        return solution;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public String changeSolutionStateWrong(){
        List<SolutionState> solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.WRONG);
        solutionSessionBean.changeSolutionState(solution, solutionStateList
                .get(0).getStateId());
        return "consult.xhtml";
    }

    public String changeSolutionStateCorrect(){
        List<SolutionState> solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.CORRECT);
        solutionSessionBean.changeSolutionState(solution, solutionStateList
                .get(0).getStateId());
        return "consult.xhtml";
    }
}
