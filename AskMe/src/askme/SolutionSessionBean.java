package askme;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "SolutionSessionEJB")
public class SolutionSessionBean {
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<SolutionEntity> getSolutions(){
        return (List<SolutionEntity>) entityManager.
                createNamedQuery("Solution.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionEntity> getSolutions(int classId, int exerciseId){
        return (List<SolutionEntity>) entityManager.
                createNamedQuery("Solution.findByExerciseAndClass").
                setParameter("exerciseId", exerciseId).
                setParameter("classId", classId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionEntity> getSolutions(int classId, int exerciseId,
                                             int solutionId){
        return (List<SolutionEntity>) entityManager.
                createNamedQuery("Solution.findByExerciseClassAndSolutionId").
                setParameter("exerciseId", exerciseId).
                setParameter("solutionId", solutionId).
                setParameter("classId", classId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionEntity> getSolutionsByState(int classId, int exerciseId,
                                             int stateId){
        return (List<SolutionEntity>) entityManager.
                createNamedQuery("Solution.findByState").
                setParameter("exerciseId", exerciseId).
                setParameter("stateId", stateId).
                setParameter("classId", classId).getResultList();
    }
}
