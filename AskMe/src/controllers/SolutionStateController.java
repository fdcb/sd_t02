package controllers;

<<<<<<< HEAD
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
import java.util.logging.Logger;

@Named (value="solutionStateController")
@ManagedBean
@RequestScoped
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
    private String solutionIdS;
    private String exerciseId;
    private boolean author;

    private Solution solution = new Solution();
    private Exercise exercise = new Exercise();

    @PostConstruct
    public void init(){
        List<SolutionState> solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.UNSEEN);

        int unseenId = solutionStateList.get(0).getStateId();

        HttpServletRequest request = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();

        classname = request.getParameter("classname");
        username = request.getParameter("username");
        exerciseId = request.getParameter("exerciseId");
        solutionIdS = request.getParameter("solutionId");

        Logger log = Logger.getLogger(ClassesController.class.getName());
        log.info("Solution: " + solutionIdS);
        log.info("Solution: " + exerciseId);
        log.info("Solution: " + username);
        log.info("Solution: " + classname);

        int solutionId = Integer.parseInt(solutionIdS);

        List<Classes> classesList = classesSessionBean.getClasses(classname);
        List<Exercise> exerciseList = exerciseSessionBean.getExercises
                (classesList.get(0).getClassId(), Integer.parseInt(exerciseId));
        exercise = exerciseList.get(0);

        List<Solution> solutionsList = solutionSessionBean.getSolutions
                (classesList.get(0).getClassId(), exercise.getExerciseId(),
                        solutionId);
        solution = solutionsList.get(0);

        author = exercise.getUsername().equals(username) && (solution
                .getId_state() == unseenId);


    }

    public String getClassname(){
        return classname;
    }

    public String getUsername(){
        return username;
    }

    public boolean isAuthor(){
        return author;
    }

    public Solution getSolution(){
        return solution;
    }

    public Exercise getExercise(){
        return exercise;
    }

    public String getExerciseId(){
        return exerciseId;
    }

    public String getSolutionIdS(){
        return solutionIdS;
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
=======
import entities.SolutionState;
import sessionBeans.SolutionStateSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="solutionStateController")
@RequestScoped
public class SolutionStateController {
    @EJB
    SolutionStateSessionBean solutionStateSessionBean;

    public List<SolutionState> getSolutionStateList() {
        return solutionStateSessionBean.getSolutionStates();
    }

    public List<SolutionState> getSolutionStateList(int stateId) {
        return solutionStateSessionBean.getSolutionStates(stateId);
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
    }
}
