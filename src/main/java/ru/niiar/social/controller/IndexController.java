package ru.niiar.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.niiar.social.model.Post;
import ru.niiar.social.repository.PostRepository;

import java.util.List;

@Controller
@RequestMapping(path = {"", "index", "login"})
public class IndexController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public String getIndex(Model model){
        //List<Post>  postList = (List<Post>) postRepository.findAll();
        return "index";}
}
