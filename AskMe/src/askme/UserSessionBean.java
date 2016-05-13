package askme;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "UserSessionEJB")
public class UserSessionBean {
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("Unchecked")
    public List<UserEntity> getUsers(){
        return (List<UserEntity>) entityManager.
                createNamedQuery("Users.findAll").getResultList();
    }

    @SuppressWarnings("Unchecked")
    public List<UserEntity> getUsers(String username){
        return (List<UserEntity>) entityManager.
                createNamedQuery("Users.findByUsername").
                setParameter("username", username).getResultList();
    }
}
