package askme;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "ExerciseSessionEJB")
public class ExerciseSessionBean {

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<ExerciseEntity> getExercises(int classId){
        return (List<ExerciseEntity>) entityManager.
                createNamedQuery("Exercise.findByClassId").
                setParameter("classId", classId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ExerciseEntity> getExercises(int classId, int exerciseId){
        return (List<ExerciseEntity>) entityManager.
                createNamedQuery("Exercise.findByClassAndId").
                setParameter("classId", classId).
                setParameter("exerciseId", exerciseId).getResultList();
    }
    public ExerciseSessionBean() {
    }
}
