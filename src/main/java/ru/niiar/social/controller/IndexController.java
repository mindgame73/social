package ru.niiar.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.niiar.social.model.Post;
import ru.niiar.social.model.User;
import ru.niiar.social.model.Vote;
import ru.niiar.social.repository.PostRepository;
import ru.niiar.social.repository.UserRepository;
import ru.niiar.social.repository.VoteRepository;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = {"", "index"})
public class IndexController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String getIndex(Model model, Principal principal){
        List<Vote> most10popular = (List<Vote>) voteRepository.findAll();
        Map<Post, Long> map = most10popular.stream()
                .collect(Collectors.groupingBy(Vote::getPost,Collectors.summingLong(Vote::getScore)));


        List<Post>  postList = (List<Post>) postRepository.findAllByOrderByPostTimeDesc();
        model.addAttribute("posts", postList);

        if (principal != null){
            User user = userRepository.findUserByUsername(principal.getName()).get();
            List<Vote> voteList = voteRepository.findTop5ByVotedUserInOrderByVoteTimeDesc(user);
            model.addAttribute("topvoted", voteList);
        }
        return "index";}
}
