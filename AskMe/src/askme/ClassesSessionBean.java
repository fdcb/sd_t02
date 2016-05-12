package askme;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "ClassesSessionEJB")
public class ClassesSessionBean {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<ClassesEntity> getClasses(){
        return (List<ClassesEntity>) entityManager.
                createNamedQuery("Class.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ClassesEntity> getClasses(int class_id){
        return (List<ClassesEntity>) entityManager.
                createNamedQuery("Class.findByClassId").
                setParameter("classId", class_id).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ClassesEntity> getClasses(String name){
        return (List<ClassesEntity>) entityManager.
                createNamedQuery("Class.findByName").
                setParameter("name", name).getResultList();
    }

    public ClassesSessionBean() {
    }
}
