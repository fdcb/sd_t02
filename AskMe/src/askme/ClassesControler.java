package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.awt.*;
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

    public List<ClassesEntity> getProductList( int class_id){

        classesList = classesSessionBean.getClasses(class_id);
        return classesList;
    }

    public List<ClassesEntity> getProductList( String name ){

        classesList = classesSessionBean.getClasses(name);
        return classesList;
    }

    //ClassesEntity newClass = new ClassesEntity();

   /* public String addNewClasses(){
        newClass.setClassId();
        newClass.setName();
        classesSessionBean.addClass( newClass );
        classesList = classesSessionBean.getClasses();
        return "listClasses.xhtml";
    }*/



}
