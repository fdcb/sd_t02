package askme;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ExerciseEntityPK implements Serializable {
    private Integer exerciseId;
    private int classId;

    @Column(name = "exercise_id")
    @Id
    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseEntityPK that = (ExerciseEntityPK) o;

        if (classId != that.classId) return false;
        if (exerciseId != null ? !exerciseId.equals(that.exerciseId) : that.exerciseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = exerciseId != null ? exerciseId.hashCode() : 0;
        result = 31 * result + classId;
        return result;
    }
}
