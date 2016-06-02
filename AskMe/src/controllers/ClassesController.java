package controllers;

import entities.Classes;
import sessionBeans.ClassesSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.*;
import java.util.logging.Logger;

@Named (value="classesController")
@RequestScoped
public class ClassesController {
    @EJB
    ClassesSessionBean classesSessionBean = new ClassesSessionBean();

    Classes classesEntity = null;
    List<Classes> classList = new ArrayList<>();

    public static int idClass;
    public static String name;


    public List<Classes> getClassList() {
        classList = classesSessionBean.getClasses();
        return classList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String addClasses(String name){
        classesEntity = new Classes();
        this.name = name;
        List<Classes> classesEntities = classesSessionBean.getClasses();
        for(int i = 0; i < classesEntities.size(); i++)
            if(classesEntities.get(i).getName().equals(name))
                return "listClasses.xhtml";
        classesEntity.setClassId(classesEntities.size() + 1);
        idClass = classesEntity.getClassId();
        classesEntity.setName(name);
        classesSessionBean.addClass(classesEntity);
        return "listClasses.xhtml";
    }

    public int getClassesNumber(){
        List<Classes> classesEntities = classesSessionBean.getClasses();
        return classesEntities.size();
    }

    public String goToExerciseList(String className){
        Logger log = Logger.getLogger(ClassesController.class.getName());
        log.info("ClassName: " + className);
        this.name = className;
        return "SubmitExercise.xhtml";
    }
}
