package com.codeup.springblogapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollRequest() {
        return "roll-dice";
    }


    @PostMapping("/roll-dice")
    public String diceRoll(@RequestParam(name = "roll") int roll, Model model) {
        int randRoll = (int) (6 * Math.random() + 1);
        model.addAttribute("roll", roll);
        model.addAttribute("randRoll", randRoll);
        String rollResponse;
        if (roll == randRoll) {
            rollResponse = "Congratulations You Guessed Correctly!";
        } else{
            rollResponse = "Sorry Try Again";
        }
        model.addAttribute( "rollResponse", rollResponse );
        return "roll-dice";
    }
}
