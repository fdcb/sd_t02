package sessionBeans;

import entities.Classes;

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
    public List<Classes> getClasses(){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Classes>) entityManager.
                createNamedQuery("Class.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public Classes getClasses(int class_id){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        List<Classes> classesEntities = (List<Classes>) entityManager.
                createNamedQuery("Class.findByClassId").
                setParameter("classId", class_id).getResultList();
        return classesEntities.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<Classes> getClasses(String name){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Classes>) entityManager.
                createNamedQuery("Class.findByName").
                setParameter("name", name).getResultList();
    }

    public void addClass(Classes classesEntity){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(classesEntity);
        entityManager.getTransaction().commit();
    }
}

