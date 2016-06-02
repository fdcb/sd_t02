package controllers;

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
