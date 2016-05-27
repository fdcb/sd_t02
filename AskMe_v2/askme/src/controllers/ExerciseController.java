package controllers;

import entities.ClassesEntity;
import entities.ExerciseEntity;
import entities.UsersEntity;
import sessionBeans.ExerciseSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value="exerciseController")
@RequestScoped
public class ExerciseController {
    @EJB
    ExerciseSessionBean exerciseSessionBean;

    ExerciseEntity exerciseEntity = new ExerciseEntity();
    List<ExerciseEntity> exerciseEntityList = new ArrayList<>();

    List<ExerciseEntity> openexercicios = new ArrayList<>();
    List<ExerciseEntity> closedexercicios = new ArrayList<>();
    List<ExerciseEntity> exercicesidexer = new ArrayList<>();

    int idClass, idExercise;


    private static int id_class,id_exercise;
    private static String solution,state,description;
    private String username;


    public List<ExerciseEntity> getExerciseList(int exercise_id) {
        return exerciseSessionBean.getExercises(exercise_id);
    }

    public List<ExerciseEntity> getExerciseList(int classId, int exercise_id) {
        return exerciseSessionBean.getExercises(classId, exercise_id);
    }

    // ---------- [ GETTERS | SETTERS ] -------//


    public ExerciseSessionBean getExerciseSessionBean() {
        return exerciseSessionBean;
    }

    public void setExerciseSessionBean(ExerciseSessionBean exerciseSessionBean) {
        this.exerciseSessionBean = exerciseSessionBean;
    }

    public List<ExerciseEntity> getExerciseEntityList() {
        return exerciseEntityList;
    }

    public void setExerciseEntityList(List<ExerciseEntity> exerciseEntityList) {
        this.exerciseEntityList = exerciseEntityList;
    }

    public List<ExerciseEntity> getOpenexercicios() {
        return openexercicios;
    }

    public void setOpenexercicios(List<ExerciseEntity> openexercicios) {
        this.openexercicios = openexercicios;
    }

    public List<ExerciseEntity> getClosedexercicios() {
        return closedexercicios;
    }

    public void setClosedexercicios(List<ExerciseEntity> closedexercicios) {
        this.closedexercicios = closedexercicios;
    }

    public List<ExerciseEntity> getExercicesidexer() {
        return exercicesidexer;
    }

    public void setExercicesidexer(List<ExerciseEntity> exercicesidexer) {
        this.exercicesidexer = exercicesidexer;
    }

    public ExerciseEntity getExerciseEntity() {
        return exerciseEntity;
    }

    public void setExerciseEntity(ExerciseEntity exerciseEntity) {
        this.exerciseEntity = exerciseEntity;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


     public String addExercise(){
         List<ClassesEntity> classesEntityList = new ArrayList<>();
         List<UsersEntity> user = new ArrayList<>();

         //classesEntityList = exerciseSessionBean
         exerciseEntityList=exerciseSessionBean.getExercises( exerciseEntity.getExerciseId() );

         if ( !user.isEmpty() ){
                int auxId=0;
                for ( int i=0; i<exerciseEntityList.size();i++ ){
                    if ( exerciseEntityList.get(0).getExerciseId() > auxId )
                        auxId=exerciseEntityList.get( i ).getExerciseId();
                }
             auxId++;
             exerciseEntity.setExerciseId( auxId );
             exerciseEntity.setDescription( description );
             exerciseEntity.setClassId( idClass );
             exerciseEntity.setUsername( username );
             exerciseEntity.setId_state( 1 ); // 0 -> fechado | 1 -> aberto
             exerciseSessionBean.addExercise( exerciseEntity );

         }
         return "listClasses.xhtml";
    }

  /*  public String closeExercise(){
        List<Solucoes> solucoes = new ArrayList<>();
        solucoes = sBean.getByIDEXUC(id_uc, id_exercicio);
        if(solucoes.isEmpty()){
            List<Exercicios> exercicios = new ArrayList<>();
            exercicios=bean.getOpenExercicios();
            for(int i=0; i<exercicios.size();i++){
                if(exercicios.get(i).getIdExercicioUc()==id_exercicio){
                    UserControler userControler = new UserControler();
                    String username = userControler.getUsername();
                    if(username.equals(exercicios.get(i).getUsername())){
                        List<Exercicios> exercicios1 = new ArrayList<>();
                        exercicios1 = bean.getExercisesByIDEXER(id_exercicio,id_uc);
                        bean.deleteExercicio(exercicios1.get(0));
                        exercicios1.get(0).setState("fechado");
                        bean.addExercicio(exercicios1.get(0));
                        return "main_page.xhtml";
                    }
                }
            }
        }
        else{
            int i = checkSolutions(solucoes);
            if(i==1){
                List<Exercicios> exercicios = new ArrayList<>();
                exercicios=bean.getOpenExercicios();
                for(int j=0; j<exercicios.size();j++){
                    if(exercicios.get(j).getIdExercicioUc()==id_exercicio){
                        if(username.equals(exercicios.get(j).getUsername())){
                            UserControler userControler = new UserControler();
                            String username = userControler.getUsername();
                            List<Exercicios> exercicios1 = new ArrayList<>();
                            exercicios1 = bean.getExercisesByIDEXER(id_exercicio,id_uc);
                            bean.deleteExercicio(exercicios1.get(j));
                            exercicios1.get(j).setState("fechado");
                            bean.addExercicio(exercicios1.get(j));
                            return "main_page.xhtml";
                        }
                    }
                }
            }
        }
        return "close_exercicio.xhtml";
    }*/
}
