package controllers;

import entities.Users;
import sessionBeans.UsersSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="userController")
@RequestScoped
public class UserController {
    @EJB
<<<<<<< HEAD
    UsersSessionBean userSessionBean;
=======
    UsersSessionBean userSessionBean = new UsersSessionBean();
>>>>>>> ea7593c58e68e612988c166e7ed1da6566a62f3b

    public static String username;
    public int id;

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

    public String addNewUser(String username) {
        if (username.equals("") || pass == null)
            return "register.xml";
        List<Users> usersEntities = userSessionBean.getUsers();
<<<<<<< HEAD
        for (int i = 0; i < usersEntities.size(); i++)
            if (usersEntities.get(i).getUsername().equals(username))
                return "register.xml";
        this.username = username;
=======
        for (Users usersEntity : usersEntities)
            if (usersEntity.getUsername().equals(username))
                return "register.xml";
        UserController.username = username;
>>>>>>> ea7593c58e68e612988c166e7ed1da6566a62f3b
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
        this.username = username;
=======
        UserController.username = username;
>>>>>>> ea7593c58e68e612988c166e7ed1da6566a62f3b
        userSessionBean.changeAccessNumber(usersEntities.get(0));
        return "listClasses.xhtml";
    }

    public String resetUser(){
<<<<<<< HEAD
        username="";
=======
        UserController.username = "";
>>>>>>> ea7593c58e68e612988c166e7ed1da6566a62f3b
        return "welcome.xhtml";
    }
}