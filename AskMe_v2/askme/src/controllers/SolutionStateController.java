package controllers;

import entities.SolutionStateEntity;
import sessionBeans.SolutionStateSessionBean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="solutionStateController")
@RequestScoped
public class SolutionStateController {
    @EJB
    SolutionStateSessionBean solutionStateSessionBean;

    public List<SolutionStateEntity> getSolutionStateList() {
        return solutionStateSessionBean.getSolutionStates();
    }

    public List<SolutionStateEntity> getSolutionStateList(int stateId) {
        return solutionStateSessionBean.getSolutionStates(stateId);
    }
}
