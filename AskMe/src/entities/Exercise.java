package entities;

import javax.persistence.*;

@Entity
@Table(name = "Exercise", schema = "", catalog = "")
@IdClass(ExercisePK.class)
@NamedQueries({
        @NamedQuery(
                name = "Exercise.findByClassId",
                query = "SELECT e FROM Exercise e " +
                        "WHERE e.classId = :classId"
        ),
        @NamedQuery(
                name = "Exercise.findByClassAndId",
                query = "SELECT e FROM Exercise e " +
                        "WHERE e.classId = :classId " +
                        "AND e.exerciseId = :exerciseId"
        ),
        @NamedQuery(
                name = "Exercise.changeState",
                query = "SELECT e FROM Exercise e "+
                        "WHERE e.exerciseId=:exerciseId " +
                        "AND e.classId=:classId"
        ),
        @NamedQuery(
                name = "Exercise.findByClassAndState",
                query = "SELECT e FROM Exercise e " +
                        "WHERE e.classId = :classId " +
                        "AND e.id_state = :stateId"
        ),
        @NamedQuery(
                name = "Exercise.findByUser",
                query = "SELECT e FROM Exercise e " +
                        "WHERE e.username =:username"
        )
})
public class Exercise {
    private Integer exerciseId;
    private int classId;
    private String description;
    private String username;
    private Integer id_state;

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

        Exercise that = (Exercise) o;

        if (classId != that.classId)
            return false;
        if (exerciseId != null ? !exerciseId.equals(that.exerciseId) :
                that.exerciseId != null)
            return false;
        if (description != null ? !description.equals(that.description) :
                that.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = exerciseId != null ? exerciseId.hashCode() : 0;
        result = 31 * result + classId;
        result = 31 * result + (description != null
                ? description.hashCode() : 0);
        return result;
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
    public Integer getId_state() {
        return id_state;
    }

    public void setId_state(Integer id_state) {
        this.id_state = id_state;
    }
}

