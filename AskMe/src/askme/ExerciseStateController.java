package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named (value="exerciseStateController")
@RequestScoped
public class ExerciseStateController {
    @EJB
    ExerciseStateSessionBean exerciseStateSessionBean;

    public List<ExerciseStateEntity> getExerciseStateList(){
        return exerciseStateSessionBean.getExerciseState();
    }

    public List<ExerciseStateEntity> getExerciseStateList(int stateId){
        return exerciseStateSessionBean.getExerciseState();
    }
}
