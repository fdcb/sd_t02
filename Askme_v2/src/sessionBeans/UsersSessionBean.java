package sessionBeans;

import entities.Users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
<<<<<<< HEAD
=======
import java.math.BigDecimal;
>>>>>>> a6013c6f3acd8b7d98453630907badba7bb63b29
import java.util.HashMap;
import java.util.List;

@Stateless(name = "UsersSessionEJB")
public class UsersSessionBean {

    private EntityManagerFactory entityManagerFactory;

<<<<<<< HEAD
    private EntityManagerFactory getEntityManagerFactory(){
        HashMap<String, String> properties = new HashMap<>();
        properties.put("ConnectionDriverName", "org.sqlite.JDBC");
        entityManagerFactory = Persistence.createEntityManagerFactory
                ("newpersistenceunit",properties);
=======
    private EntityManagerFactory getEntityManagerFactory() {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("ConnectionDriverName", "org.sqlite.JDBC");
        entityManagerFactory = Persistence.createEntityManagerFactory
                ("newpersistenceunit", properties);
>>>>>>> a6013c6f3acd8b7d98453630907badba7bb63b29
        return entityManagerFactory;
    }

    @SuppressWarnings("unchecked")
<<<<<<< HEAD
    public List<Users> getUsers(){
=======
    public List<Users> getUsers() {
>>>>>>> a6013c6f3acd8b7d98453630907badba7bb63b29
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Users>) entityManager.createNamedQuery("Users.findAll")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
<<<<<<< HEAD
    public List<Users> getUsers(String username){
=======
    public List<Users> getUsers(String username) {
>>>>>>> a6013c6f3acd8b7d98453630907badba7bb63b29
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Users>) entityManager.createNamedQuery("Users" +
                ".findByUsername").setParameter("username", username)
                .getResultList();
    }

<<<<<<< HEAD
    public void addUsers(Users users){
=======
    public void addUsers(Users users) {
>>>>>>> a6013c6f3acd8b7d98453630907badba7bb63b29
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(users);
        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
<<<<<<< HEAD
    public List<Users> getUsers(String username, String password){
=======
    public List<Users> getUsers(String username, String password) {
>>>>>>> a6013c6f3acd8b7d98453630907badba7bb63b29
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return (List<Users>) entityManager.createNamedQuery("Users" +
                ".findByUsernamePassword").setParameter("username", username)
                .setParameter("password", password).getResultList();
    }

<<<<<<< HEAD
    public void changeAccessNumber(Users users){
=======
    public void changeAccessNumber(Users users) {
>>>>>>> a6013c6f3acd8b7d98453630907badba7bb63b29
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        Users users1 = entityManager.find(Users.class, users.getUsername());
        entityManager.getTransaction().begin();
        users1.setNumberAccess(users1.getNumberAccess() + 1);
        entityManager.getTransaction().commit();
    }
<<<<<<< HEAD
=======

    public int getTotalAccess() {
        EntityManager entityManager = getEntityManagerFactory()
                .createEntityManager();
        return ((int) entityManager.createNativeQuery
                ("SELECT SUM(numberAccess) FROM Users")
                .getSingleResult());
    }


>>>>>>> a6013c6f3acd8b7d98453630907badba7bb63b29
}
