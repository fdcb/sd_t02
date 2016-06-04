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
    UsersSessionBean userSessionBean = new UsersSessionBean();

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
        for (int i = 0; i < usersEntities.size(); i++)
            if (usersEntities.get(i).getUsername().equals(username))
                return "register.xml";
        this.username = username;
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
        this.username = username;
        userSessionBean.changeAccessNumber(usersEntities.get(0));
        return "listClasses.xhtml";
    }

    public String resetUser(){
        username="";
        return "welcome.xhtml";
    }
}