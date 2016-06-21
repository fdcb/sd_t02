package controllers;

<<<<<<< HEAD
import entities.Solution;
import entities.SolutionState;
import entities.Users;
import sessionBeans.SolutionSessionBean;
import sessionBeans.SolutionStateSessionBean;
import sessionBeans.UsersSessionBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
=======
import entities.Users;
import sessionBeans.UsersSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
import java.util.ArrayList;
import java.util.List;

@Named (value="userController")
<<<<<<< HEAD
@ManagedBean
=======
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
@RequestScoped
public class UserController {
    @EJB
    UsersSessionBean userSessionBean = new UsersSessionBean();
<<<<<<< HEAD
    @EJB
    SolutionStateSessionBean solutionStateSessionBean = new
            SolutionStateSessionBean();
    @EJB
    SolutionSessionBean solutionSessionBean = new SolutionSessionBean();

    public static String username;
    public int id;
    private int accessNumber;


    @PostConstruct
    public void init(){
        accessNumber = userSessionBean.getTotalAccess();
    }
=======

    public static String username;
    public int id;
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e

    private List<Users> list = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String pass = "qwerty";

    public List<Users> getList() {
        return list;
    }

    public void setList(List<Users> list) {
        this.list = list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

<<<<<<< HEAD
    // ----------[  Other methods ]---------------//
=======
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
    public String addNewUser(String username) {
        if (username.equals("") || pass == null)
            return "register.xml";
        List<Users> usersEntities = userSessionBean.getUsers();
<<<<<<< HEAD
        for (Users usersEntity : usersEntities)
            if (usersEntity.getUsername().equals(username))
                return "register.xml";
        UserController.username = username;
=======
        for (int i = 0; i < usersEntities.size(); i++)
            if (usersEntities.get(i).getUsername().equals(username))
                return "register.xml";
        this.username = username;
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(pass);
        userSessionBean.addUsers(user);
        return "listClasses.xhtml";
    }

    public String logIn(String username) {
        if (username.equals("admin")) {
            Users usersEntity = new Users();
            usersEntity.setUsername("admin");
            userSessionBean.changeAccessNumber(usersEntity);
            return "adminMode.xhtml";
        }
        List<Users> usersEntities = userSessionBean.getUsers(username);
        if (usersEntities.isEmpty())
            return "welcome.xhtml";
<<<<<<< HEAD
        UserController.username = username;
=======
        this.username = username;
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
        userSessionBean.changeAccessNumber(usersEntities.get(0));
        return "listClasses.xhtml";
    }

    public String resetUser(){
<<<<<<< HEAD
        UserController.username = "";
        return "welcome.xhtml";
    }

    // -------------- [ ADMIN STUFF ] ---------------//
    public int getAccessNumber() {
        return accessNumber;
    }

    public void deleteWrongSolutions(){
        List<SolutionState> solutionStateList = solutionStateSessionBean
                .getSolutionStates(SolutionState.WRONG);
        int wrongId= solutionStateList.get(0).getStateId();
        List<Solution> solutionList = solutionSessionBean.getSolutionsByState
                (wrongId);
        for(Solution aSolutionList : solutionList) {
            solutionSessionBean.deleteSolutions(aSolutionList);
        }
    }




=======
        username="";
        return "welcome.xhtml";
    }
>>>>>>> 96e3b105d3d46d2308844798615aebaac5b4b81e
}