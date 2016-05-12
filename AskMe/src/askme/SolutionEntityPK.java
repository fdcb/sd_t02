package askme;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SolutionEntityPK implements Serializable {
    private Integer solutionId;
    private int exerciseId;
    private int classId;

    @Column(name = "solution_id")
    @Id
    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    @Column(name = "exercise_id")
    @Id
    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Column(name = "class_id")
    @Id
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SolutionEntityPK that = (SolutionEntityPK) o;

        if (exerciseId != that.exerciseId)
            return false;
        if (classId != that.classId)
            return false;
        if (solutionId != null ? !solutionId.equals(that.solutionId) :
                that.solutionId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = solutionId != null ? solutionId.hashCode() : 0;
        result = 31 * result + exerciseId;
        result = 31 * result + classId;
        return result;
    }
}
