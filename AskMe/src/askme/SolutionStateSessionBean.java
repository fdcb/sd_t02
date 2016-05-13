package askme;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "SolutionStateSessionEJB")
public class SolutionStateSessionBean {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<SolutionStateEntity> getSolutionStates(){
        return (List<SolutionStateEntity>) entityManager.
                createNamedQuery("SolutionState.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionStateEntity> getSolutionStates(int stateId){
        return (List<SolutionStateEntity>) entityManager.
                createNamedQuery("SolutionState.findByStateId").
                setParameter("stateId", stateId).getResultList();
    }
}
