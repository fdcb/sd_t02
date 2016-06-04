package controllers;

import entities.Classes;
import entities.Exercise;
import sessionBeans.ClassesSessionBean;
import sessionBeans.ExerciseSessionBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Named(value="exerciseController")
@ManagedBean
@RequestScoped
public class ExerciseController implements Serializable {
    @EJB
    ExerciseSessionBean exerciseSessionBean = new ExerciseSessionBean();
    @EJB
    ClassesSessionBean classesSessionBean = new ClassesSessionBean();

    private String classname;

    private List<Exercise> exerciseList;
    private Exercise exercise = new Exercise();
    private Classes classes;

    @PostConstruct
    public void init(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();
        classname = request.getParameter("classname");

        List<Classes> list = classesSessionBean.getClasses(classname);
        classes = list.get(0);
        exerciseList = exerciseSessionBean.getExercises(classes.getClassId());
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public Exercise getExercise(){
        return exercise;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
    public String addExercise(){
        return "listClasses.xhtml";
    }
}
