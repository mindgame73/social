package ru.niiar.social.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name="users")
public class User {
    @Id
    @SequenceGenerator(name = "sequence_user", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user")
    private Integer user_id;

    @Column(unique = true, nullable = false)
    private String login;


    @Column(length = 128, nullable = false)
    private String password;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Post> listPosts;

    public Integer getUserid() {
        return user_id;
    }

    public void setUserid(Integer user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id.equals(user.user_id) &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, login, password);
    }

    public User(){

    }

    public User(Integer userId, String login, String password) {
        this.user_id = userId;
        this.login = login;
        this.password = password;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer userid) {
        this.user_id = userid;
    }
}
