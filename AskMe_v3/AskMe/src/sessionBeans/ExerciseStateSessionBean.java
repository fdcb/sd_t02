package sessionBeans;

import entities.ExerciseState;

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
    public List<ExerciseState> getExerciseState(){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ExerciseState>) entityManager.
                createNamedQuery("ExerciseState.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ExerciseState> getExerciseState(int stateId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ExerciseState>) entityManager.
                createNamedQuery("ExerciseState.findByStateId").
                setParameter("stateId", stateId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ExerciseState> getExerciseState(String state){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ExerciseState>) entityManager.
                createNamedQuery("ExerciseState.findByState").
                setParameter("state", state).getResultList();
    }

}

