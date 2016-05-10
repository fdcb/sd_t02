package askme;

import javax.persistence.*;

/**
 * Created by Filipa on 10/05/2016.
 */
@Entity
@Table(name = "Solution", schema = "", catalog = "")
@IdClass(SolutionEntityPK.class)
public class SolutionEntity {
    private Integer solutionId;
    private int exerciseId;
    private int classId;
    private String description;

    @Id
    @Column(name = "solution_id")
    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    @Id
    @Column(name = "exercise_id")
    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Id
    @Column(name = "class_id")
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolutionEntity that = (SolutionEntity) o;

        if (exerciseId != that.exerciseId) return false;
        if (classId != that.classId) return false;
        if (solutionId != null ? !solutionId.equals(that.solutionId) : that.solutionId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = solutionId != null ? solutionId.hashCode() : 0;
        result = 31 * result + exerciseId;
        result = 31 * result + classId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
