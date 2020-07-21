package ru.niiar.social.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name="posts")
public class Post {
    @Id
    @SequenceGenerator(name = "sequence_post", sequenceName = "post_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_post")
    private Integer post_id;

    @Column
    private String title;

    //CLOB for oracle
    @Lob
    @Column(name="cont",columnDefinition = "text")
    private String content;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User author;

    @Column(name="post_time")
    private Date postTime;

    @JsonIgnore
    @Transient
    private long score = 0;

    public long getScore() {
        score =  voteList.stream()
                .map(Vote::getScore).mapToInt(Integer::intValue).sum();
        return score;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    private List<Vote> voteList;

    public Integer getPostId() {
        return post_id;
    }

    public void setPostId(Integer post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public Post(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return post_id.equals(post.post_id) &&
                title.equals(post.title) &&
                content.equals(post.content) &&
                author.equals(post.author) &&
                postTime.equals(post.postTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post_id, title, content, author, postTime);
    }

    public Post(Integer postId, String title, String content, User author, Date postTime) {
        this.post_id = postId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.postTime = postTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
}
