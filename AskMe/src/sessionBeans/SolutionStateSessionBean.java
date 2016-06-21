package sessionBeans;

import entities.SolutionState;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless(name = "SolutionStateSessionEJB")
public class SolutionStateSessionBean {



    private EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("newpersistenceunit");

    private EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    @SuppressWarnings("unchecked")
    public List<SolutionState> getSolutionStates(){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionState>) entityManager.
                createNamedQuery("SolutionState.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionState> getSolutionStates(int stateId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionState>) entityManager.
                createNamedQuery("SolutionState.findByStateId").
                setParameter("stateId", stateId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionState> getSolutionStates(String state){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionState>) entityManager.
                createNamedQuery("SolutionState.findByState").
                setParameter("state", state).getResultList();
    }
}