package com.codeup.springblogapp.controller;



import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostRepository postRepo;

    public PostController(PostRepository postDao) {
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


    @GetMapping("posts/create")
    public String showCreate() {
        return "/posts/create";
    }

    @PostMapping("posts/create")
    public String createPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             @RequestParam(name = "type") String type,
                             Model model) {
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setType(type);
        this.postRepo.save(post);
        return "posts/create";
    }

    @GetMapping("/posts/{id}")
    public String showAnIndividualPost(@PathVariable long id, Model model){
        Post aPost = postRepo.getOne(id);
        model.addAttribute("post",aPost);
        return "posts/show";
    }

    @GetMapping("/posts/edit/{id}")
    public String getEditPostOne(@PathVariable long id, Model model){
        Post aPost = postRepo.getOne(id);
        model.addAttribute("post",aPost);
        return "/posts/edit";
    }
    @PostMapping("/posts/edit/{id}")
    public String savePostEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body, @RequestParam String type, Model model){
        Post editPost = postRepo.getOne(id);
        editPost.setTitle(title);
        editPost.setBody(body);
        editPost.setType(type);
        postRepo.save(editPost);
        model.addAttribute("post", editPost);
        return "/posts/show";
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
}

