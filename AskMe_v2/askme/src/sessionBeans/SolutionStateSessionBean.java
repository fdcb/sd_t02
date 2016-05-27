package sessionBeans;

import entities.SolutionStateEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless(name = "SolutionStateSessionEJB")
public class SolutionStateSessionBean {
    public static final String WRONG = "wrong";
    public static final String UNSEEN = "unseen";
    public static final String CORRECT = "correct";
    private EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("newpersistenceunit");

    private EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    @SuppressWarnings("unchecked")
    public List<SolutionStateEntity> getSolutionStates(){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionStateEntity>) entityManager.
                createNamedQuery("SolutionState.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionStateEntity> getSolutionStates(int stateId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionStateEntity>) entityManager.
                createNamedQuery("SolutionState.findByStateId").
                setParameter("stateId", stateId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionStateEntity> getSolutionStates(String state){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionStateEntity>) entityManager.
                createNamedQuery("SolutionState.findByState").
                setParameter("state", state).getResultList();
    }
}
