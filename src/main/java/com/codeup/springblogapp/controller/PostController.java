package com.codeup.springblogapp.controller;



import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }


    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showById(@PathVariable long id) {
        return "/posts/show" + id;
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
        this.postDao.save(post);
        return "posts/create";
    }
}

