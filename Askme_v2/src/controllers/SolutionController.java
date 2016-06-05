package controllers;

import entities.Classes;
import entities.Exercise;
import entities.Solution;
import entities.SolutionState;
import sessionBeans.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named (value="solutionController")
@ManagedBean
@RequestScoped
public class SolutionController implements Serializable{
    @EJB
    SolutionSessionBean solutionSessionBean = new SolutionSessionBean();
    @EJB
    ExerciseSessionBean exerciseSessionBean = new ExerciseSessionBean();
    @EJB
    ClassesSessionBean classesSessionBean = new ClassesSessionBean();
    @EJB
    SolutionStateSessionBean solutionStateSessionBean = new
            SolutionStateSessionBean();

    private String classname;
    private String username;
    private int exerciseId;

    private List<Solution> solutionList;
    private Exercise exercise;

    @PostConstruct
    public void init(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();
        classname = request.getParameter("classname");
        username = request.getParameter("username");
        exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
        Logger log = Logger.getLogger(ClassesController.class.getName());
        log.info("Exercise: " + exerciseId);
        List<Classes> classesList = classesSessionBean.getClasses(classname);
        List<Exercise> exerciseList = exerciseSessionBean.getExercises
                (classesList.get(0).getClassId(), exerciseId);

        exercise = exerciseList.get(0);

        solutionList = solutionSessionBean.getSolutions(exercise.getClassId(),
                exercise.getExerciseId());
    }

    public List<Solution> getSolutionList() {
        return solutionList;
    }

    public String getUsername() {
        return username;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public String getClassname() {
        return classname;
    }

    public void addSolution(String description){
        Solution solution = new Solution();
        List<SolutionState> solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.UNSEEN);

        solution.setDescription(description);
        solution.setUsername(username);
        solution.setExerciseId(exercise.getExerciseId());
        solution.setClassId(exercise.getClassId());
        solution.setSolutionId(solutionList.size() + 1);
        solution.setId_state(solutionStateList.get(0).getStateId());

        solutionSessionBean.addSolution(solution);
    }
}

