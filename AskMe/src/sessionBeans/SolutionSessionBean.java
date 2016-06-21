package sessionBeans;

import entities.Solution;
import entities.SolutionPK;

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
    public List<Solution> getSolutions(){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Solution>) entityManager.
                createNamedQuery("Solution.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Solution> getSolutions(int classId, int exerciseId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Solution>) entityManager.
                createNamedQuery("Solution.findByExerciseAndClass").
                setParameter("exerciseId", exerciseId).
                setParameter("classId", classId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Solution> getSolutions(int classId, int exerciseId,
                                             int solutionId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Solution>) entityManager.
                createNamedQuery("Solution.findByExerciseClassAndSolutionId").
                setParameter("exerciseId", exerciseId).
                setParameter("solutionId", solutionId).
                setParameter("classId", classId).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Solution> getSolutionsByState(int classId, int exerciseId,
                                                    int stateId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Solution>) entityManager.
                createNamedQuery("Solution.findByState").
                setParameter("exerciseId", exerciseId).
                setParameter("stateId", stateId).
                setParameter("classId", classId).getResultList();
    }

    public void addSolution(Solution solutionEntity){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(solutionEntity);
        entityManager.getTransaction().commit();
    }

    public void changeSolutionState(Solution solutionEntity, int
            state_id){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        SolutionPK solutionPK = new SolutionPK(solutionEntity
                .getSolutionId(), solutionEntity.getExerciseId(),
                solutionEntity.getClassId());
        Solution solution = entityManager.find(Solution.class,
                solutionPK);
        entityManager.getTransaction().begin();
        solution.setId_state(state_id);
        entityManager.getTransaction().commit();
    }
<<<<<<< HEAD

    public void deleteSolutions(Solution solutionEntity){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        SolutionPK solutionPK = new SolutionPK(solutionEntity
                .getSolutionId(), solutionEntity.getExerciseId(),
                solutionEntity.getClassId());
        Solution solution = entityManager.find(Solution.class,
                solutionPK);
        entityManager.getTransaction().begin();
        entityManager.remove(solution);
        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Solution> getSolutionsByState(int stateId){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Solution>) entityManager.
                createNamedQuery("Solution.findAllByState").
                setParameter("stateId", stateId).getResultList();
    }
=======
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
}

