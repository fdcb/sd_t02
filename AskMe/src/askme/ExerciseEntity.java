package askme;

import javax.persistence.*;

@Entity
@Table(name = "Exercise", schema = "", catalog = "")
@IdClass(ExerciseEntityPK.class)
public class ExerciseEntity {
    private Integer exerciseId;
    private int classId;
    private String username;
    private int idState;
    private String description;

    @Id
    @Column(name = "exercise_id")
    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseEntity that = (ExerciseEntity) o;

        if (classId != that.classId) return false;
        if (idState != that.idState) return false;
        if (exerciseId != null ? !exerciseId.equals(that.exerciseId) : that.exerciseId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = exerciseId != null ? exerciseId.hashCode() : 0;
        result = 31 * result + classId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + idState;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
