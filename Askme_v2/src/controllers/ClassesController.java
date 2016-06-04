package controllers;

import entities.Classes;
import sessionBeans.ClassesSessionBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

@Named (value="classesController")
@ManagedBean
@ViewScoped
public class ClassesController implements Serializable {
    @EJB
    ClassesSessionBean classesSessionBean = new ClassesSessionBean();

    private List<Classes> classList;
    private Classes classes = new Classes();

    public static int idClass;

    public List<Classes> getClassList() {
        return classList;
    }

    public Classes getClasses() {
        return classes;
    }

    @PostConstruct
    public void init(){
        classList = classesSessionBean.getClasses();
    }

    public String addClasses(String name){
        classes.setName(name);
        List<Classes> classesList1 = classesSessionBean.getClasses(classes
                .getName());
        if(!classesList1.isEmpty())
            return "listClasses.xhtml";
        Logger log = Logger.getLogger(ClassesController.class.getName());
        if(getClasses().getName() == null) {
            log.info("ClassName: nope aint working");
            return "listClasses.xhtml";
        }
        log.info("ClassName: " + getClasses().getName());
        getClasses().setClassId(classList.size() + 1);
        classesSessionBean.addClass(getClasses());
        return "listClasses.xhtml";
    }

    public int getClassesNumber(){
        List<Classes> classesEntities = classesSessionBean.getClasses();
        return classesEntities.size();
    }

    public String goToExerciseList(Classes classes){
        Logger log = Logger.getLogger(ClassesController.class.getName());
        log.info("ClassName: " + classes.getName());
        return "SubmitExercise.xhtml";
    }


}
