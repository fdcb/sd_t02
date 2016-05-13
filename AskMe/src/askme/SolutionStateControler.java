package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="solutionStateControler")
@RequestScoped
public class SolutionStateControler {

    @EJB
    SolutionStateSessionBean solutionStateSessionBean;

    List<SolutionStateEntity> solutionStateList = new ArrayList<>();

    public List<SolutionStateEntity> getSolutionStateList(){

        solutionStateList = solutionStateSessionBean.getSolutionStates();
        return solutionStateList;
    }

    public List<SolutionStateEntity> getSolutionStateList( int stateId ){

        solutionStateList = solutionStateSessionBean.getSolutionStates();
        return solutionStateList;
    }
}
