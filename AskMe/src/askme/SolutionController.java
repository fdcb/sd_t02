package askme;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named (value="solutionController")
@RequestScoped
public class SolutionController {
    @EJB
    SolutionSessionBean solutionSessionBean;

    public List<SolutionEntity> getSolutionList() {
        return solutionSessionBean.getSolutions();
    }

    public List<SolutionEntity> getSolutionList(int classId, int exerciseId) {
        return solutionSessionBean.getSolutions(classId, exerciseId);
    }

    public List<SolutionEntity> getSolutionList(int classId, int exerciseId,
                                                int solutionId) {
        return solutionSessionBean.getSolutions(classId, exerciseId,
                solutionId);
    }
}
