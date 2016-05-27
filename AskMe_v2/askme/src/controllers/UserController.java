package controllers;

import entities.UsersEntity;
import sessionBeans.UsersSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;

@Named (value="userController")
@RequestScoped
public class UserController {
    @EJB
    UsersSessionBean userSessionBean;

    static UsersEntity user = null;
    String pass, username;
    int id=0;
    boolean loginFail= false;

    List<UsersEntity> list = new ArrayList<>();

    public List<UsersEntity> getUsersList() {
        return userSessionBean.getUsers();
    }

    public List<UsersEntity> getUsersList(String username) {
        return userSessionBean.getUsers(username);
    }

    // ---- [ GETTERS | SETTERS ] -----//
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isLoginFail() {
        return loginFail;
    }

    public void setLoginFail(boolean falhouLogin) {
        this.loginFail = falhouLogin;
    }

    public static UsersEntity getUser() {
        return user;
    }

    public static void setUser(UsersEntity user) {
        UserController.user = user;
    }

    public String addNewUser() {
        UsersEntity  user = new UsersEntity ();
        user.setUsername( username );
        user.setPassword( pass );
        userSessionBean.addUser(user);
        list = userSessionBean.getUsers();
        return "welcome.xhtml";
    }


   /* public String getUser(){
        user = userSessionBean.getUsers( username , pass);
        if (user == null) loginFail = true;
        else{
            loginFail = false;
        }
        return "index.xhtml";
    }*/

    public String logIn(){
        List<UsersEntity> access = new ArrayList<>();
        if( username.equals("admin") && pass.equals("admin"))
            return "adminMode.xhtml";
        access=userSessionBean.getUsers(username);
        id=access.get(0).getNumberAccess();
        id++;
        access.get(0).setNumberAccess(id);

        if( !access.isEmpty() ){
            for(int i=0;i<access.size();i++){
                if(access.get(i).getPassword().equals(pass)){
                    loginFail=false;
                    return "listClasses.xhtml";}
            }
        }
        loginFail=true;
        return "welcome.xhtml";
    }

}
