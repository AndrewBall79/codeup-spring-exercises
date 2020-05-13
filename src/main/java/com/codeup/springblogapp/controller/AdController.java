//package com.codeup.springblogapp.controller;
//
//
//import com.codeup.springblogapp.model.Ad;
//import com.codeup.springblogapp.repositories.AdRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class AdController {
//    private final AdRepository adDao;
//
//    public AdController(AdRepository adDao) {
//        this.adDao = adDao;
//    }
//
//    @GetMapping("/ads")
//    public String index(Model model) {
//        model.addAttribute("ads", adDao.findAll());
//        return "ads/index";
//    }
//
//    @GetMapping("/ads")
//    public String showAds(Model model) {
//        List<Ad> adList = new ArrayList<>();
//        Ad ad = new Ad("67 cool car", "good'un");
//        adList.add(ad);
//        ad = new Ad("broken desk", "No top");
//        adList.add(ad);
//        ad = new Ad("blue box", "regular box, except it's blue");
//        adList.add(ad);
//        ad = new Ad("porthole from Titanic", "the real deal Folks!");
//        adList.add(ad);
//        ad = new Ad("Old clothes", "Gamgam passed away, now I have all these old clothes. They hardly smell of Gamgam");
//        adList.add(ad);
//        model.addAttribute("ads", adList);
//        return "ads/index";
//    }
//
//    @GetMapping("/ad")
//    public String showAd(Model model) {
//        List<Ad> adList = new ArrayList<>();
//        Ad ad = new Ad("book", "good'un");
//        model.addAttribute("as", adList);
//        return "ads/show";
//    }
//
//    @PostMapping("/ads/create")
//    public String createAd(@RequestParam(name = "title") String title,
//                           @RequestParam(name = "description") String description,
//                           Model model) {
//        Ad ad = new Ad(title, description);
//        model.addAttribute("ad", ad);
//        return "ads/show";
//
//    }
//}
