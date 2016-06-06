package sessionBeans;

import entities.Solution;
import entities.SolutionPK;
import entities.Users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;

@Stateless(name = "UsersSessionEJB")
public class UsersSessionBean {

    private EntityManagerFactory entityManagerFactory;

    private EntityManagerFactory getEntityManagerFactory() {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("ConnectionDriverName", "org.sqlite.JDBC");
        entityManagerFactory = Persistence.createEntityManagerFactory
                ("newpersistenceunit", properties);
        return entityManagerFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Users> getUsers() {
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Users>) entityManager.createNamedQuery("Users.findAll")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Users> getUsers(String username) {
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Users>) entityManager.createNamedQuery("Users" +
                ".findByUsername").setParameter("username", username)
                .getResultList();
    }

    public void addUsers(Users users){
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(users);
        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Users> getUsers(String username, String password) {
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Users>) entityManager.createNamedQuery("Users" +
                ".findByUsernamePassword").setParameter("username", username)
                .setParameter("password", password).getResultList();
    }

    public void changeAccessNumber(Users users) {
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        Users users1 = entityManager.find(Users.class, users.getUsername());
        entityManager.getTransaction().begin();
        users1.setNumberAccess(users1.getNumberAccess() + 1);
        entityManager.getTransaction().commit();
    }

    public int getTotalAccess() {
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return ((int) entityManager.createNativeQuery
                ("SELECT SUM(numberAccess) FROM Users")
                .getSingleResult());
    }


}
