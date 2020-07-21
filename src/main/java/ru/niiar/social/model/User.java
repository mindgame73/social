package ru.niiar.social.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private String username;


    @Column(length = 128, nullable = false)
    private String password;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Post> getListPosts() {
        return listPosts;
    }

    public void setListPosts(Set<Post> listPosts) {
        this.listPosts = listPosts;
    }
    @JsonManagedReference
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Post> listPosts;


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
                username.equals(user.username) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username, password);
    }

    public User(){

    }

    public User(Integer userId, String username, String password) {
        this.user_id = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer userid) {
        this.user_id = userid;
    }
}
