package sessionBeans;

import entities.UsersEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless(name = "UsersSessionEJB")
public class UsersSessionBean {
    private EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("newpersistenceunit");

    private EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    @SuppressWarnings("Unchecked")
    public List<UsersEntity> getUsers(){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<UsersEntity>) entityManager.
                createNamedQuery("Users.findAll").getResultList();
    }

    @SuppressWarnings("Unchecked")
    public List<UsersEntity> getUsers(String username){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<UsersEntity>) entityManager.
                createNamedQuery("Users.findByUsername").
                setParameter("username", username).getResultList();
    }

    public void addUser(UsersEntity usersEntity){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(usersEntity);
        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("Unchecked")
    public List<UsersEntity> getUsers(String username, String password){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<UsersEntity>) entityManager.
                createNamedQuery("Users.findByUsernamePassword").
                setParameter("username", username).
                setParameter("password", password).getResultList();
    }

    public void changeAccessNumber(UsersEntity usersEntity){
        EntityManager entityManager =  getEntityManagerFactory()
                .createEntityManager();
        UsersEntity user = entityManager.find(UsersEntity.class, usersEntity
                .getUsername());
        entityManager.getTransaction().begin();
        user.setNumberAccess(user.getNumberAccess() + 1);
        entityManager.getTransaction().commit();
    }
}
