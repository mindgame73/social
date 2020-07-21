package ru.niiar.social.repository;

import org.springframework.data.repository.CrudRepository;
import ru.niiar.social.model.Post;
import ru.niiar.social.model.User;
import ru.niiar.social.model.Vote;

import java.util.List;

public interface VoteRepository extends CrudRepository<Vote,Integer> {
    List<Vote> findAllByVotedUserAndAndPost(User votedUser, Post post);
    List<Vote> findTop5ByVotedUserInOrderByVoteTimeDesc(User votedUser);
}
