package entities;

import javax.persistence.*;

@Entity
@Table(name = "Users", schema = "", catalog = "")
@NamedQueries({
        @NamedQuery(
                name = "Users.findAll",
                query = "SELECT u FROM UsersEntity u"
        ),
        @NamedQuery(
                name = "Users.findByUsername",
                query = "SELECT u FROM UsersEntity u " +
                        "WHERE u.username = :username"
        ),
        @NamedQuery(
                name = "Users.findByUsernamePassword",
                query = "SELECT u FROM UsersEntity u " +
                        "WHERE u.username = :username " +
                        "AND u.password = :password"
        )
})
public class UsersEntity {
    private String username;
    private String password;
    private int numberAccess;

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "numberAccess")
    public int getNumberAccess() {
        return numberAccess;
    }

    public void setNumberAccess(int numberAccess) {
        this.numberAccess = numberAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UsersEntity that = (UsersEntity) o;

        if (numberAccess != that.numberAccess)
            return false;
        if (username != null ? !username.equals(that.username)
                : that.username != null)
            return false;
        if (password != null ? !password.equals(that.password)
                : that.password != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + numberAccess;
        return result;
    }
}
