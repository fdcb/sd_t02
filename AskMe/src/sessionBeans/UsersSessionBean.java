package sessionBeans;

import entities.Users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
<<<<<<< HEAD
=======
import javax.persistence.PersistenceContext;
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
import java.util.HashMap;
import java.util.List;

@Stateless(name = "UsersSessionEJB")
public class UsersSessionBean {

<<<<<<< HEAD
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
=======
    private EntityManagerFactory getEntityManagerFactory(){
        HashMap<String, String> properties = new HashMap<>();
        properties.put("ConnectionDriverName", "org.sqlite.JDBC");
        return Persistence.createEntityManagerFactory
                ("newpersistenceunit", properties);
    }

    @SuppressWarnings("unchecked")
    public List<Users> getUsers(){
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Users>) entityManager.createNamedQuery("Users.findAll")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
<<<<<<< HEAD
    public List<Users> getUsers(String username) {
=======
    public List<Users> getUsers(String username){
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
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
<<<<<<< HEAD
    public List<Users> getUsers(String username, String password) {
=======
    public List<Users> getUsers(String username, String password){
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Users>) entityManager.createNamedQuery("Users" +
                ".findByUsernamePassword").setParameter("username", username)
                .setParameter("password", password).getResultList();
    }

<<<<<<< HEAD
    public void changeAccessNumber(Users users) {
=======
    public void changeAccessNumber(Users users){
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        Users users1 = entityManager.find(Users.class, users.getUsername());
        entityManager.getTransaction().begin();
        users1.setNumberAccess(users1.getNumberAccess() + 1);
        entityManager.getTransaction().commit();
    }
<<<<<<< HEAD

    public int getTotalAccess() {
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return ((int) entityManager.createNativeQuery
                ("SELECT SUM(numberAccess) FROM Users")
                .getSingleResult());
    }


=======
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
}
