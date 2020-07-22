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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping(path = {"", "index", "last"})
public class IndexController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String getIndex(Model model, Principal principal){

        List<Post> postList = (List<Post>) postRepository.findAllByOrderByPostTimeDesc();
        model.addAttribute("posts", postList);

        if (principal != null)
            model.addAttribute("username", principal.getName());

        //random one s
        Post post = postList.get(new Random().nextInt(postList.size()));
        model.addAttribute("randPost", post);

        if (principal != null){
            User user = userRepository.findUserByUsername(principal.getName()).get();
            List<Vote> voteList = voteRepository.findTop5ByVotedUserInOrderByVoteTimeDesc(user);
            model.addAttribute("topvoted", voteList);
        }
        return "index";}

        @GetMapping({"/top", "/flop"})
        public String getTop10(Model model, Principal principal, HttpServletRequest request){
            if (principal != null)
                model.addAttribute("username", principal.getName());
            //random
            List<Post> postList = (List<Post>) postRepository.findAllByOrderByPostTimeDesc();
            Post post = postList.get(new Random().nextInt(postList.size()));
            model.addAttribute("randPost", post);

            List<Vote> allVotes = (List<Vote>) voteRepository.findAll();
            Map<Post, Long> unorderedMap = allVotes.stream()
                    .collect(Collectors.groupingBy(Vote::getPost, Collectors.summingLong(Vote::getScore)));

            Map<Post, Long> ordered;
            if (request.getRequestURL().toString().endsWith("/flop")){
                 ordered = unorderedMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .limit(10)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b) -> a, LinkedHashMap::new));
            }
            else
            {
                 ordered = unorderedMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(10)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b) -> a, LinkedHashMap::new));
            }

            model.addAttribute("posts", ordered);
            if (principal != null){
                User user = userRepository.findUserByUsername(principal.getName()).get();
                List<Vote> voteList = voteRepository.findTop5ByVotedUserInOrderByVoteTimeDesc(user);
                model.addAttribute("topvoted", voteList);
            }
        return "top";
        }
}
