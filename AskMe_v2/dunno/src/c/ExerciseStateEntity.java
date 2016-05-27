package c;

import javax.persistence.*;

/**
 * Created by Filipa on 5/27/2016.
 */
@Entity
@Table(name = "ExerciseState", schema = "", catalog = "")
public class ExerciseStateEntity {
    private Integer stateId;
    private String state;

    @Id
    @Column(name = "state_id")
    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseStateEntity that = (ExerciseStateEntity) o;

        if (stateId != null ? !stateId.equals(that.stateId) : that.stateId != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stateId != null ? stateId.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
