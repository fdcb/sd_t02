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

         classesEntityList = exerciseSessionBean
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

    public String closeExercise(){
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
    }
}
