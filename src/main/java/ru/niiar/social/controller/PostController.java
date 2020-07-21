package ru.niiar.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.niiar.social.model.Post;
import ru.niiar.social.model.User;
import ru.niiar.social.model.Vote;
import ru.niiar.social.repository.PostRepository;
import ru.niiar.social.repository.UserRepository;
import ru.niiar.social.repository.VoteRepository;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    @ResponseBody
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public Post createPost(@RequestBody Post post, Principal principal){
        post.setAuthor(userRepository.findUserByUsername(principal.getName()).get());
        post.setPostTime(new Date());
        postRepository.save(post);
        return post;
    }

    @ResponseBody
    @RequestMapping(value = "/upvote", method = RequestMethod.GET)
    public Vote upvote(@RequestParam Integer post_id,
                       @RequestParam boolean upvote,
                       Principal principal){
        if (principal != null & post_id != null){
            Vote vote = new Vote();
            Post post = postRepository.findById(post_id).get();
            User user = userRepository.findUserByUsername(principal.getName()).get();
            if (voteRepository.findAllByVotedUserAndAndPost(user,post).size() == 0){
                vote.setPost(post);
                vote.setVotedUser(userRepository.findUserByUsername(principal.getName()).get());
                if(upvote)
                    vote.setScore(1);
                else
                    vote.setScore(-1);
                return voteRepository.save(vote);
            }
        }
        return null;
    }
}
