package controllers;

import entities.SolutionState;
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

    public List<SolutionState> getSolutionStateList() {
        return solutionStateSessionBean.getSolutionStates();
    }

    public List<SolutionState> getSolutionStateList(int stateId) {
        return solutionStateSessionBean.getSolutionStates(stateId);
    }
}
