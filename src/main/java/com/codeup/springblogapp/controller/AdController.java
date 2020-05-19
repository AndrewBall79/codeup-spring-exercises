package com.codeup.springblogapp.controller;

import com.codeup.springblogapp.model.Ad;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.AdRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import com.codeup.springblogapp.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdController {

    private UserRepository userDao; // this has all the functions of JpaRepository
    private AdRepository adDao; // this has all the functions of JpaRepository
    private EmailService emailService;


    public AdController(UserRepository userDao, AdRepository adDao, EmailService emailService) {
        this.userDao = userDao;
        this.adDao = adDao;
        this.emailService = emailService;
    }

    @GetMapping("/ads")
    public String showAds(Model model) {
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String showAd(@PathVariable long id, Model model) {
        Ad ad = adDao.getOne(id);
        model.addAttribute("ad", ad);
        return "ads/show";
    }

    @GetMapping("/ads/{id}/edit")
    public String editAd(@PathVariable long id, Model model) {
        Ad ad = adDao.getOne(id);
        User user = userDao.getOne(1L);
        ad.setUser(user);
        model.addAttribute("ad", ad);
        return "ads/create";
    }

    @GetMapping("/ads/create")
    public String gotoCreateAdForm(Model model) {
        Ad ad = new Ad();
        User user = userDao.getOne(1L);
        ad.setUser(user);
        model.addAttribute("ad", ad);
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public RedirectView createAd(@ModelAttribute Ad ad) {
        User user = userDao.getOne(1L);
        ad.setUser(user);
        adDao.save(ad);
        emailService.prepareAndSend(ad, "You Created an ad!",
                "Title: "+ ad.getTitle()+"\n"+
                "Description: "+ ad.getDescription());
        return new RedirectView("/ads/" + ad.getId());
    }


}