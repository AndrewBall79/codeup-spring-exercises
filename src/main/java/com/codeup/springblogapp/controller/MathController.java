package com.codeup.springblogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MathController {

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String showAdd(@PathVariable long num1, @PathVariable long num2){
        return "Adding: " + num1 + " + " + num2 + " = " + (num1 + num2) ;
    }

    @GetMapping("/subtract/{num1}/and/{num2}")
    @ResponseBody
    public String showSub(@PathVariable long num1, @PathVariable long num2){
        return "Subtract: " + num1 + " + " + num2 + " = " + (num1 - num2) ;
    }


    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String showMult(@PathVariable long num1, @PathVariable long num2){
        return "Multiply: " + num1 + " + " + num2 + " = " + (num1 * num2) ;
    }

    @GetMapping("/divide/{num1}/and/{num2}")
    @ResponseBody
    public String showDiv(@PathVariable long num1, @PathVariable long num2){
        return "Multiply: " + num1 + " + " + num2 + " = " + (num1 / num2) ;
    }
}
