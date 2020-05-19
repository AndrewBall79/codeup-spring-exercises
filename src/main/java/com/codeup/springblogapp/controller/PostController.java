package com.codeup.springblogapp.controller;

import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import com.codeup.springblogapp.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private UserRepository userDao;
    private final PostRepository postRepo;
    private EmailService emailService;

    public PostController(UserRepository userDao, PostRepository postDao, EmailService emailService
    ) {

        this.userDao = userDao;
        this.postRepo = postDao;
        this.emailService = emailService;
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts")
    public String showIndexPage(Model model) {
        List<Post> postList = postRepo.findAll();
        model.addAttribute("posts", postList);
        return "redirect:/index";
    }

    @GetMapping("/posts/{id}")
    public String showAnIndividualPost(@PathVariable long id, Model model) {
        Post thisPost = postRepo.getOne(id);
        model.addAttribute("post", thisPost);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/delete")
    public String getDeletePostForm(@PathVariable long id, Model model) {
        Post aPost = postRepo.getOne(id);
        model.addAttribute("post", aPost);
        return "posts/delete";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        Post aPost = postRepo.getOne(id);
        postRepo.delete(aPost);
        return "redirect:/index";
    }


    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model) {
        Post post = postRepo.getOne(id);
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post) {
        postRepo.save(post);
        return "redirect:/index";
    }

    @GetMapping("posts/create")
    public String submitCreatePost(Model model) {
        User user = userDao.getOne(1L);
        Post post = new Post();
        post.setUser(user);
        model.addAttribute("post", post);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String showCreateForm(@ModelAttribute Post post) {
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(author == null){
            return "redirect:/login";
        }else{
        post.setUser(author);
        postRepo.save(post);
        emailService.prepareAndSend(post, "You Created a post!",
                "Title: "+ post.getTitle()+"\n"+
                        "Description: "+ post.getBody());
        return "redirect:/index";
        }
    }


}

