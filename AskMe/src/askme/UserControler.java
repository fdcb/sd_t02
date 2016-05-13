package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="userControler")
@RequestScoped
public class UserControler {

    @EJB
    UserSessionBean userSessionBean;

    List<UserEntity> userList = new ArrayList<>();

    public List<UserEntity> getUserList(){

        userList = userSessionBean.getUsers();
        return userList;
    }

    public List<UserEntity> getUserList( String username ){

        userList = userSessionBean.getUsers();
        return userList;
    }

}
