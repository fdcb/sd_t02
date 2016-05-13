package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="exerciseStateControler")
@RequestScoped
public class ExerciseStateControler {

    @EJB
    ExerciseStateSessionBean exerciseStateSessionBean;

    List<ExerciseStateEntity> exerciseStateList = new ArrayList<>();

    public List<ExerciseStateEntity> getExerciseStateList(){

        exerciseStateList = exerciseStateSessionBean.getExerciseState();
        return exerciseStateList;
    }

    public List<ExerciseStateEntity> getExerciseStateList( int stateId ){

        exerciseStateList = exerciseStateSessionBean.getExerciseState();
        return exerciseStateList;
    }
}
