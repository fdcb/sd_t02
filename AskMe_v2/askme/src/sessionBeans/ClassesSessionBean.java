package sessionBeans;

import entities.ClassesEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless(name = "classesSessionEJB")
public class ClassesSessionBean {
    private EntityManagerFactory entityManagerFactory = Persistence
           .createEntityManagerFactory("newpersistenceunit");


    private EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @SuppressWarnings("unchecked")
    public List<ClassesEntity> getClasses(){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ClassesEntity>) entityManager.
                createNamedQuery("Class.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ClassesEntity> getClasses(int class_id){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ClassesEntity>) entityManager.
                createNamedQuery("Class.findByClassId").
                setParameter("classId", class_id).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ClassesEntity> getClasses(String name){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<ClassesEntity>) entityManager.
                createNamedQuery("Class.findByName").
                setParameter("name", name).getResultList();
    }

    public void addClass(ClassesEntity classesEntity){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        entityManager.persist(classesEntity);
    }
}
