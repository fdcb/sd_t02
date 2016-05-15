package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named (value="classesController")
@RequestScoped
public class ClassesController {
    @EJB
    ClassesSessionBean classesSessionBean;

    public List<ClassesEntity> getProductList() {
        return classesSessionBean.getClasses();
    }

    public List<ClassesEntity> getProductList(int class_id) {
        return classesSessionBean.getClasses(class_id);
    }

    public List<ClassesEntity> getProductList(String name) {
        return classesSessionBean.getClasses(name);
    }

    //ClassesEntity newClass = new ClassesEntity();

   /* public String addNewClasses() {
        newClass.setClassId();
        newClass.setName();
        classesSessionBean.addClass(newClass );
        classesList = classesSessionBean.getClasses();
        return "listClasses.xhtml";
    }*/
}
