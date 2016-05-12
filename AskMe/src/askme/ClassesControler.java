package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="classesControler")
@RequestScoped
public class ClassesControler {

    @EJB
    ClassesSessionBean classesSessionBean;

    List<ClassesEntity> classesList = new ArrayList<>();

    public List<ClassesEntity> getProductList(){

        classesList = classesSessionBean.getClasses();
        return classesList;
    }

    ClassesEntity newClass = new ClassesEntity();

    public String add
}
