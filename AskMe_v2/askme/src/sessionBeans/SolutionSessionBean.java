package sessionBeans;

import entities.SolutionEntity;
import entities.SolutionEntityPK;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless(name = "SolutionSessionEJB")
public class SolutionSessionBean {
    private EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("newpersistenceunit");

    private EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @SuppressWarnings("unchecked")
    public List<SolutionEntity> getSolutions(){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionEntity>) entityManager.
                createNamedQuery("Solution.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionEntity> getSolutions(int classId, int exerciseId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionEntity>) entityManager.
                createNamedQuery("Solution.findByExerciseAndClass").
                setParameter("exerciseId", exerciseId).
                setParameter("classId", classId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionEntity> getSolutions(int classId, int exerciseId,
                                             int solutionId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionEntity>) entityManager.
                createNamedQuery("Solution.findByExerciseClassAndSolutionId").
                setParameter("exerciseId", exerciseId).
                setParameter("solutionId", solutionId).
                setParameter("classId", classId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SolutionEntity> getSolutionsByState(int classId, int exerciseId,
                                                    int stateId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<SolutionEntity>) entityManager.
                createNamedQuery("Solution.findByState").
                setParameter("exerciseId", exerciseId).
                setParameter("stateId", stateId).
                setParameter("classId", classId).getResultList();
    }

    public void addSolution(SolutionEntity solutionEntity){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        entityManager.persist(solutionEntity);
    }

    public void changeSolutionState(SolutionEntity solutionEntity, int
            state_id){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        SolutionEntityPK solutionPK = new SolutionEntityPK(solutionEntity
                .getSolutionId(), solutionEntity.getExerciseId(),
                solutionEntity.getClassId());
        SolutionEntity solution = entityManager.find(SolutionEntity.class,
                solutionPK);
        entityManager.getTransaction().begin();
        solution.setId_state(state_id);
        entityManager.getTransaction().commit();
    }
}
