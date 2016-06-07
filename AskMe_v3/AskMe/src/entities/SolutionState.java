package entities;

import javax.persistence.*;

@Entity
@Table(name = "SolutionState", schema = "", catalog = "")
@NamedQueries({
        @NamedQuery(
                name = "SolutionState.findAll",
                query = "SELECT s FROM SolutionState s"
        ),
        @NamedQuery(
                name = "SolutionState.findByStateId",
                query = "SELECT s FROM SolutionState s " +
                        "WHERE s.stateId = :stateId"
        ),
        @NamedQuery(
                name = "SolutionState.findByState",
                query = "SELECT s FROM SolutionState s " +
                        "WHERE s.state = :state"
        )
})
public class SolutionState {
    private Integer stateId;
    private String state;

    public static final String UNSEEN = "unseen";
    public static final String WRONG = "wrong";
    public static final String CORRECT = "correct";

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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SolutionState that = (SolutionState) o;

        if (stateId != null ? !stateId.equals(that.stateId)
                : that.stateId != null)
            return false;
        if (state != null ? !state.equals(that.state)
                : that.state != null)
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

