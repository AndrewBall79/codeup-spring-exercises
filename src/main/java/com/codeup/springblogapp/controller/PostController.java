package com.codeup.springblogapp.controller;

import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import com.codeup.springblogapp.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PostController {

    private UserRepository userDao;
    private final PostRepository postDao;
    private EmailService emailService;

    public PostController(UserRepository userDao, PostRepository postDao, EmailService emailService
    ) {

        this.userDao = userDao;
        this.postDao = postDao;
        this.emailService = emailService;
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts")
    public String showIndexPage(Model model) {
        List<Post> postList = postDao.findAll();
        model.addAttribute("posts", postList);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showAnIndividualPost(@PathVariable long id, Model model) {
        Post thisPost = postDao.getOne(id);
        model.addAttribute("post", thisPost);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/delete")
    public String getDeletePostForm(@PathVariable long id, Model model) {
        Post aPost = postDao.getOne(id);
        model.addAttribute("post", aPost);
        return "posts/delete";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        Post aPost = postDao.getOne(id);
        postDao.delete(aPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model) {
        Post post = postDao.getOne(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postDao.save(post);
        return "redirect:/index";
    }

    @GetMapping("posts/create")
    public String submitCreatePost(Model model) {
        Post post = new Post();
        User user = userDao.getOne(1L);
        post.setUser(user);
        model.addAttribute("post", post);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public RedirectView showCreateForm(@ModelAttribute Post post) {
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(author == null || !(author instanceof UserDetails)){
            return new RedirectView("redirect:/login");
        }
        post.setUser(author);
        postDao.save(post);
        emailService.prepareAndSend(post, "You Created a post!",
                "Title: "+ post.getTitle()+"\n"+
                        "Description: "+ post.getBody());
        return new RedirectView("redirect:/posts/" + post.getId());
    }
}

