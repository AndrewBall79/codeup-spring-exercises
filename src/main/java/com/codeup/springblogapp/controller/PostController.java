package com.codeup.springblogapp.controller;



import com.codeup.springblogapp.model.Ad;
import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PostController {

    private UserRepository userDao;
    private final PostRepository postRepo;

    public PostController(UserRepository userDao, PostRepository postDao){
        this.userDao = userDao;
        this.postRepo = postDao;
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts")
        public String showIndexPage(Model model){
        List<Post> postList = postRepo.findAll();
        model.addAttribute("posts", postList);
        return "posts/index";
    }






    @GetMapping("/posts/{id}")
    public String showAnIndividualPost(@PathVariable long id, Model model){
        Post thisPost = postRepo.getOne(id);
        model.addAttribute("post",thisPost);
        return "posts/show";
    }

@GetMapping("/posts/delete/{id}")
public String getDeletePostForm(@PathVariable long id, Model model){
    Post aPost = postRepo.getOne(id);
    model.addAttribute("post", aPost);
    return "posts/delete";
}

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id){
        Post aPost = postRepo.getOne(id);
        postRepo.delete(aPost);
        return "/posts/show";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model) {
        Post post = postRepo.getOne(id);
        User user = userDao.getOne(1L);
        post.setUser(user);
        model.addAttribute("post", post);
        return "posts/create";
    }

    @GetMapping("posts/create")
    public String submitCreatePost(Model model){
        Post post = new Post();
        User user = userDao.getOne(1L);
        post.setUser(user);
        model.addAttribute("post", post);
        return "posts/create";

    }

    @PostMapping("/posts/create")
    public RedirectView showCreateForm(@ModelAttribute Post post) {
        postRepo.save(post);
        return new RedirectView("/index");
    }


}

