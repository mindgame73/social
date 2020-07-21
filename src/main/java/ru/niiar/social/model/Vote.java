package ru.niiar.social.model;


import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity(name="votes")
public class Vote {
    @Id
    @SequenceGenerator(name = "sequence_vote", sequenceName = "vote_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_vote")
    private Integer vote_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="post_id", referencedColumnName = "post_id")
    private Post post;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User votedUser;

    @Column(name="vote_time")
    private Date voteTime;

    @Column(length = 1)
    private int score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return score == vote.score &&
                vote_id.equals(vote.vote_id) &&
                post.equals(vote.post) &&
                votedUser.equals(vote.votedUser) &&
                voteTime.equals(vote.voteTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vote_id, post, votedUser, voteTime, score);
    }

    public Vote(){

    }

    public Vote(Integer voteId, Post post, User votedUser, Date voteTime, int score) {
        this.vote_id = voteId;
        this.post = post;
        this.votedUser = votedUser;
        this.voteTime = voteTime;
        this.score = score;
    }

    public Integer getVoteId() {
        return vote_id;
    }

    public void setVoteId(Integer vote_id) {
        this.vote_id = vote_id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getVotedUser() {
        return votedUser;
    }

    public void setVotedUser(User votedUser) {
        this.votedUser = votedUser;
    }

    public Date getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(Date voteTime) {
        this.voteTime = voteTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
