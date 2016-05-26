package sessionBeans;

import entities.ExerciseStateEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless(name = "ExerciseStateSessionEJB")
public class ExerciseStateSessionBean {
    private EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("newpersistenceunit");

    private EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @SuppressWarnings("unchecked")
    public List<ExerciseStateEntity> getExerciseState(){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ExerciseStateEntity>) entityManager.
                createNamedQuery("ExerciseState.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ExerciseStateEntity> getExerciseState(int stateId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ExerciseStateEntity>) entityManager.
                createNamedQuery("ExerciseState.findByStateId").
                setParameter("stateId", stateId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ExerciseStateEntity> getExerciseState(String state){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ExerciseStateEntity>) entityManager.
                createNamedQuery("ExerciseState.findByState").
                setParameter("state", state).getResultList();
    }
}
