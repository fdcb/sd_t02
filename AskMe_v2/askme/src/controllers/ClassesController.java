package controllers;

import entities.ClassesEntity;
import sessionBeans.ClassesSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.*;

@Named (value="classesController")
@RequestScoped
public class ClassesController {
    @EJB
    ClassesSessionBean classesSessionBean;

    ClassesEntity classesEntity = null;
    List<ClassesEntity> classList = new ArrayList<>();

    int idClass;
    String name;


    public List<ClassesEntity> getClassList() {
        return classesSessionBean.getClasses();
    }

    public List<ClassesEntity> getClassList(String name) {
        return classesSessionBean.getClasses(name);
    }


    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassesEntity getClassesEntity() {
        return classesEntity;
    }

    public void setClassesEntity(ClassesEntity classesEntity) {
        this.classesEntity = classesEntity;
    }

    public String addClasses(){
        classesEntity = new ClassesEntity();
        classesEntity.setClassId( idClass );
        classesEntity.setName( name );
        classesSessionBean.addClass( classesEntity);
        return "welcome.xhtml";
    }

    public String getClassFromDB(int idClass) {
        classesEntity = classesSessionBean.getClasses(idClass);
        return "listClasses.xhtml";
    }


}
