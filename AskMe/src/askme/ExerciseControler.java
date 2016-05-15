package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value="exerciseControler")
@RequestScoped
public class ExerciseControler {

    @EJB
    ExerciseSessionBean exerciseSessionBean;

    List<ExerciseEntity> exerciseList = new ArrayList<>();


    public List<ExerciseEntity> getExerciseList(int exercise_id){

        exerciseList = exerciseSessionBean.getExercises(exercise_id);
        return exerciseList ;
    }

    public List<ExerciseEntity> getExerciseList(int classId, int exercise_id){

        exerciseList = exerciseSessionBean.getExercises(classId, exercise_id);
        return exerciseList ;
    }


}
