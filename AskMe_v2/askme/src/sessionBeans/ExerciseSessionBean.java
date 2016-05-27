package sessionBeans;

import entities.ExerciseEntity;
import entities.ExerciseEntityPK;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless(name = "ExerciseSessionEJB")
public class ExerciseSessionBean {
    private EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("newpersistenceunit");

    private EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    @SuppressWarnings("unchecked")
    public List<ExerciseEntity> getExercises(int classId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ExerciseEntity>) entityManager.
                createNamedQuery("Exercise.findByClassId").
                setParameter("classId", classId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ExerciseEntity> getExercises(int classId, int exerciseId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ExerciseEntity>) entityManager.
                createNamedQuery("Exercise.findByClassAndId").
                setParameter("classId", classId).
                setParameter("exerciseId", exerciseId).getResultList();
    }

    public void addExercise(ExerciseEntity exerciseEntity){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(exerciseEntity);
        entityManager.getTransaction().commit();
    }

    public void changeExerciseState(ExerciseEntity exerciseEntity,
                                    int state_id){
        ExerciseEntityPK exercisePK = new ExerciseEntityPK(exerciseEntity
                .getExerciseId(), exerciseEntity.getClassId());
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        ExerciseEntity exercise = entityManager.find(ExerciseEntity.class,
                exercisePK);
        entityManager.getTransaction().begin();
        exercise.setId_state(state_id);
        entityManager.getTransaction().commit();
    }
}
