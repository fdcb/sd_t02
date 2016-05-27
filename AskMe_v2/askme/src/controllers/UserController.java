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

    public List<UsersEntity> getUsersList() {
        return userSessionBean.getUsers();
    }

    public List<UsersEntity> getUsersList(String username) {
        return userSessionBean.getUsers(username);
    }
}
