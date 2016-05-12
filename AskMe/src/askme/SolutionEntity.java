package askme;

import javax.persistence.*;

@Entity
@Table(name = "Solution", schema = "", catalog = "")
@IdClass(SolutionEntityPK.class)
@NamedQueries({
        @NamedQuery(name = "Solution.findAll", query =
                "SELECT s FROM SolutionEntity s"),
        @NamedQuery(name = "Solution.findBySolutionId", query = "SELECT s" +
                " FROM SolutionEntity s WHERE s.solutionId = :solutionId"),
        @NamedQuery(name = "Solution.findByExerciseId", query = "SELECT s" +
                " FROM SolutionEntity s WHERE s.exerciseId = :exerciseId"),
        @NamedQuery(name = "Solution.findByClassId", query = "SELECT s" +
                " FROM SolutionEntity s WHERE s.classId = :classId"),
        @NamedQuery(name = "Solution.findByDescription", query = "SELECT s" +
                " FROM SolutionEntity s WHERE s.description = :description")
})
public class SolutionEntity {
    private Integer solutionId;
    private int exerciseId;
    private int classId;
    private String username;
    private int idState;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "id_state")
    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SolutionEntity that = (SolutionEntity) o;

        if (exerciseId != that.exerciseId)
            return false;
        if (classId != that.classId)
            return false;
        if (idState != that.idState)
            return false;
        if (solutionId != null ? !solutionId.equals(that.solutionId) :
                that.solutionId != null)
            return false;
        if (username != null ? !username.equals(that.username) :
                that.username != null)
            return false;
        if (description != null ? !description.equals(that.description) :
                that.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = solutionId != null ? solutionId.hashCode() : 0;
        result = 31 * result + exerciseId;
        result = 31 * result + classId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + idState;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
