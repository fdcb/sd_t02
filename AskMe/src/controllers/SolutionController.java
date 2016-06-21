package controllers;

<<<<<<< HEAD
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

        author = username.equals(exercise.getUsername()) && (exercise
                .getId_state() != closedId);
        closed = exercise.getId_state() == closedId;

        for(Solution aSolutionList : allSolutions)
            if (aSolutionList.getId_state() == unseenId)
                author = false;

        if(state != null)
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
        else
            solutionList = allSolutions;
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

=======
import entities.Exercise;
import entities.Solution;
import sessionBeans.ExerciseSessionBean;
import sessionBeans.ExerciseStateSessionBean;
import sessionBeans.SolutionSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="solutionController")
@RequestScoped
public class SolutionController {
    @EJB
    SolutionSessionBean solutionSessionBean;
    @EJB
    ExerciseSessionBean exerciseSessionBean;

    Solution solutionEntity = new Solution();

    private String description, username;
    private int idSol,id_exercise,id_classes, state;
    int idExe, idSolution;

    public List<Solution> getSolutionList() {
        return solutionSessionBean.getSolutions();
    }

    public List<Solution> getSolutionList(int classId, int exerciseId) {
        return solutionSessionBean.getSolutions(classId, exerciseId);
    }

    public List<Solution> getSolutionList(int classId, int exerciseId,
                                                int solutionId) {
        return solutionSessionBean.getSolutions(classId, exerciseId,
                solutionId);
    }

    // ------------- [ GETTERS / SETTERS ] -----------------//


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIdSol() {
        return idSol;
    }

    public void setIdSol(int idSol) {
        this.idSol = idSol;
    }

    public int getId_exercise() {
        return id_exercise;
    }

    public void setId_exercise(int id_exercise) {
        this.id_exercise = id_exercise;
    }

    public int getId_classes() {
        return id_classes;
    }

    public void setId_classes(int id_classes) {
        this.id_classes = id_classes;
    }

    public Solution getSolution() {
        return solutionEntity;
    }

    public void setSolution(Solution solutionEntity) {
        this.solutionEntity = solutionEntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdExe() {
        return idExe;
    }

    public void setIdExe(int idExe) {
        this.idExe = idExe;
    }

    public int getIdSolution() {
        return idSolution;
    }

    public void setIdSolution(int idSolution) {
        this.idSolution = idSolution;
    }

    public SolutionSessionBean getSolutionSessionBean() {
        return solutionSessionBean;
    }

    public void setSolutionSessionBean(SolutionSessionBean solutionSessionBean) {
        this.solutionSessionBean = solutionSessionBean;
    }

    public String addSolution(){
        solutionEntity = new Solution();
        solutionEntity.setExerciseId( idExe );
        solutionEntity.setDescription( description );
        solutionEntity.setUsername( username );
        short s = -1; // -1 -> por validar | 0 -> incorreta | 1 -> incorreta
        solutionEntity.setId_state(-1);
        solutionSessionBean.addSolution( solutionEntity);
        return "listClasses.xhtml";
    }

    public String classify(){
        List<Solution> solucoes = new ArrayList<>();
        solucoes = solutionSessionBean.getSolutions( idSol ,id_classes );
        List<Exercise> exercicios = new ArrayList<>();
        exercicios = exerciseSessionBean.getExercises( id_classes, id_exercise );
        UserController userControler = new UserController();
        String username = userControler.getUsername();
        if(exercicios.get(0).getUsername().equals(username) &&
                solucoes.get(0).getExerciseId()==exercicios.get(0).getExerciseId() &&
                solucoes.get(0).getClassId()==exercicios.get(0).getClassId()){
           // solutionSessionBean.removeSolution(solucoes.get(0));
            solucoes.get(0).setId_state( state );
            solutionSessionBean.addSolution(solucoes.get(0));
            return "listClasses.xhtml";
        }
        return "classifySolution.xhtml";
    }

    public void deleteWrong() {
        List<Exercise> exerciseEntityList = new ArrayList<>();
        // exerciseEntityList = exerciseSessionBean.getExerciseClosed();
        List<Solution> solutionEntityList = new ArrayList<>();
        solutionEntityList = solutionSessionBean.getSolutions();
        for (int i = 0; i < exerciseEntityList.size(); i++) {
            for (int j = 0; j < solutionEntityList.size(); j++) {
                if (solutionEntityList.get(j).getExerciseId() == exerciseEntityList.get(i).getExerciseId()
                        && solutionEntityList.get(j).getClassId() == exerciseEntityList.get(i).getClassId()) ;
                //             solutionSessionBean.removeSolution( solutionEntityList.get(j));
            }
        }

    }
}
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
