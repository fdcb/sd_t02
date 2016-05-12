package askme;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "ExerciseStateSessionEJB")
public class ExerciseStateSessionBean {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<ExerciseStateEntity> getExerciseState(){
        return (List<ExerciseStateEntity>) entityManager.
                createNamedQuery("ExerciseState.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ExerciseStateEntity> getExerciseState(int stateId){
        return (List<ExerciseStateEntity>) entityManager.
                createNamedQuery("ExerciseState.findByStateId").
                setParameter("stateId", stateId).getResultList();
    }


    public ExerciseStateSessionBean() {
    }
}
