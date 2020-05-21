package com.codeup.springblogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class HelloController {


    @GetMapping("/hello")
    @ResponseBody
    public String helloSpring(){
        return "<h2><em>Hello from Spring!</em></h2>";
    }

//    @RequestMapping(path = "/upload", method = RequestMethod.POST) <== same as
    @PostMapping("/upload") //<== same as ^
    public void upload(){

    }

    @GetMapping("/hi/{name}")
    @ResponseBody
    public String sayHi(@PathVariable String name){
        return "Hi, " + name;
    }

    @GetMapping("/defined-ad/{id}")
    @ResponseBody
    public String showAd(@PathVariable long id){
        return "showing details for ad with id: " + id;
    }

    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }

    @GetMapping("/welcome")
    public String showWelcome() {
        return "welcome";
    }

}