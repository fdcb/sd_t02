package sessionBeans;

import entities.Exercise;
import entities.ExercisePK;

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
    public List<Exercise> getExercises(int classId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Exercise>) entityManager.
                createNamedQuery("Exercise.findByClassId").
                setParameter("classId", classId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Exercise> getExercises(int classId, int exerciseId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Exercise>) entityManager.
                createNamedQuery("Exercise.findByClassAndId").
                setParameter("classId", classId).
                setParameter("exerciseId", exerciseId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Exercise> getExercisesByState(int classId, int stateId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Exercise>) entityManager.
                createNamedQuery("Exercise.findByClassAndState").
                setParameter("classId", classId).
                setParameter("stateId", stateId).getResultList();
    }

    public void addExercise(Exercise exerciseEntity){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(exerciseEntity);
        entityManager.getTransaction().commit();
    }

    public void changeExerciseState(Exercise exerciseEntity,
                                    int state_id){
        ExercisePK exercisePK = new ExercisePK(exerciseEntity
                .getExerciseId(), exerciseEntity.getClassId());
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        Exercise exercise = entityManager.find(Exercise.class,
                exercisePK);
        entityManager.getTransaction().begin();
        exercise.setId_state(state_id);
        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Exercise> getExercisesByUser(String username){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Exercise>) entityManager.
                createNamedQuery("Exercise.findByUser").
                setParameter("username", username).getResultList();
    }
}

