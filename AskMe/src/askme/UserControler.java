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
    UserSessionBean user;

    List<UserEntity> userList = new ArrayList<>();

  /*  public List<UserEntity> getUserList(){
        userList = user.getUsers();
    }*/

}
