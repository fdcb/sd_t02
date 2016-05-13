package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named (value="solutionControler")
@RequestScoped
public class SolutionControler {

    @EJB
    SolutionSessionBean solutionSessionBean;

    List<SolutionEntity> solutionList = new ArrayList<>();

    public List<SolutionEntity> getSolutionList(){

        solutionList = solutionSessionBean.getSolutions();
        return solutionList;
    }

    public List<SolutionEntity> getSolutionList(int classId, int exerciseId){

        solutionList = solutionSessionBean.getSolutions();
        return solutionList;
    }

    public List<SolutionEntity> getSolutionList(int classId, int exerciseId, int solutionId){

        solutionList = solutionSessionBean.getSolutions();
        return solutionList;
    }
}
