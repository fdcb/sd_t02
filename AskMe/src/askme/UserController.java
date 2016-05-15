package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="userController")
@RequestScoped
public class UserController {
    @EJB
    UserSessionBean userSessionBean;

    public List<UserEntity> getUserList() {
        return userSessionBean.getUsers();
    }

    public List<UserEntity> getUserList(String username) {
        return userSessionBean.getUsers(username);
    }
}
