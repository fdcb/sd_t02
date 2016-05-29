package controllers;

import entities.UsersEntity;
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
    UsersSessionBean userSessionBean;

    public static String username = "cenas";
    public int id;

    List<UsersEntity> list = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String pass = "qwerty";

    public List<UsersEntity> getList() {
        return list;
    }

    public void setList(List<UsersEntity> list) {
        this.list = list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String addNewUser(String username) {
        if(username.equals("") || pass == null)
            return "register.xml";
        List<UsersEntity> usersEntities = userSessionBean.getUsers();
        for(int i = 0; i < usersEntities.size(); i++)
            if(usersEntities.get(i).getUsername().equals(username))
                return "register.xml";
        this.username = username;
        UsersEntity  user = new UsersEntity();
        user.setUsername(username);
        user.setPassword(pass);
        userSessionBean.addUser(user);
        return "listClasses.xhtml";
    }

    public String logIn(String username){
        if (username.equals("admin")) {
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setUsername("admin");
            userSessionBean.changeAccessNumber(usersEntity);
            return "adminMode.xhtml";
        }
        List<UsersEntity> usersEntities = userSessionBean.getUsers(username);
        if(usersEntities.isEmpty())
            return "welcome.xhtml";
        this.username = username;
        userSessionBean.changeAccessNumber(usersEntities.get(0));
        return "listClasses.xhtml";
    }
}
