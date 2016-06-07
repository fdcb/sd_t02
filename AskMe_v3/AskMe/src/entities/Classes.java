package entities;

import javax.persistence.*;

@Entity
@Table(name = "Class", schema = "", catalog = "")
@NamedQueries({
        @NamedQuery(
                name = "Class.findAll",
                query = "SELECT c FROM Classes c"
        ),
        @NamedQuery(
                name = "Class.findByClassId",
                query = "SELECT c FROM Classes c " +
                        "WHERE c.classId = :classId"
        ),
        @NamedQuery(
                name = "Class.findByName",
                query = "SELECT c FROM Classes c WHERE c.name = :name"
        )
})
public class Classes {
    private Integer classId;
    private String name;

    @Id
    @Column(name = "class_id")
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Classes that = (Classes) o;

        if (classId != null ? !classId.equals(that.classId)
                : that.classId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId != null ? classId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

