package com.codeup.springblogapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String showIndex(){
        return "posts index page" ;
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showById(@PathVariable long id){
        return "view an individual post " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showCreate(){
        return "view the form for creating a post" ;
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "create new post" ;
    }

}
