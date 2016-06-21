package controllers;

import entities.*;
import sessionBeans.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
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
    @EJB
    private ExerciseStateSessionBean exerciseStateSessionBean = new
            ExerciseStateSessionBean();

    private String classname;
    private String username;
    private String exerciseId;
    private boolean author;
    private boolean closed;
    private int unseenId;
    private int closedId;

    private List<Solution> solutionList;
    private List<Solution> allSolutions;
    private Exercise exercise;

    public static final String ALL = "all";


    @PostConstruct
    public void init(){
        List<SolutionState> solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.UNSEEN);
        unseenId = solutionStateList.get(0).getStateId();
        solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.CORRECT);
        int correctId = solutionStateList.get(0).getStateId();
        solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.WRONG);
        int wrongId = solutionStateList.get(0).getStateId();
        List<ExerciseState> exerciseStateList = exerciseStateSessionBean
                .getExerciseState(ExerciseState.CLOSED);
        closedId = exerciseStateList.get(0).getStateId();

        HttpServletRequest request = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();
        classname = request.getParameter("classname");
        username = request.getParameter("username");
        exerciseId = request.getParameter("exerciseId");
        Logger log = Logger.getLogger(ClassesController.class.getName());
        log.info("Exercise: " + exerciseId);
        String state = request.getParameter("state");

        List<Classes> classesList = classesSessionBean.getClasses(classname);
        List<Exercise> exerciseList = exerciseSessionBean.getExercises
                (classesList.get(0).getClassId(), Integer.parseInt(exerciseId));

        exercise = exerciseList.get(0);

        allSolutions = solutionSessionBean.getSolutions(exercise.getClassId(),
                exercise.getExerciseId());

        author = username.equals(exercise.getUsername()) && !(exercise
                .getId_state() == closedId);
        closed = exercise.getId_state() == closedId;

        for(Solution aSolutionList : allSolutions)
            if (aSolutionList.getId_state() == unseenId)
                author = false;

        switch (state){
            case ALL: solutionList = allSolutions; break;
            case SolutionState.CORRECT : solutionList = solutionSessionBean
                    .getSolutionsByState(exercise.getClassId(),exercise.
                            getExerciseId(), correctId); break;
            case SolutionState.UNSEEN : solutionList = solutionSessionBean
                    .getSolutionsByState(exercise.getClassId(),exercise.
                            getExerciseId(), unseenId); break;
            case SolutionState.WRONG: solutionList = solutionSessionBean
                    .getSolutionsByState(exercise.getClassId(),exercise.
                            getExerciseId(), wrongId);
        }
    }

    public List<Solution> getSolutionList(){
        return solutionList;
    }

    public String getUsername(){
        return username;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public String getClassname() {
        return classname;
    }

    public boolean isAuthor() {
        return author;
    }

    public void setAuthor(boolean author) {
        this.author = author;
    }

    public String getUnseenState() {
        return SolutionState.UNSEEN;
    }

    public String getWrongState() {
        return SolutionState.WRONG;
    }

    public String getCorrectState() {
        return SolutionState.CORRECT;
    }

    public boolean isClosed() {
        return closed;
    }

    public String getAllState(){
        return ALL;
    }

    public String addSolution(String description){
        Solution solution = new Solution();

        solution.setDescription(description);
        solution.setUsername(username);
        solution.setExerciseId(exercise.getExerciseId());
        solution.setClassId(exercise.getClassId());
        solution.setSolutionId(allSolutions.size() + 1);
        solution.setId_state(unseenId);

        solutionSessionBean.addSolution(solution);
        return "SubmitSolution.xhtml";
    }

    public String solutionIdToString(Solution solution){
        return Integer.toString(solution.getSolutionId());
    }

    public String changeExerciseState(){
        exerciseSessionBean.changeExerciseState(exercise, closedId);
        return "SubmitExercise.xhtml";
    }
}

