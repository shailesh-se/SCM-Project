package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String homepage(Model model) {
        model.addAttribute("greetingMessage", "Welcome to Spring Boot!");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutpage(Model model) {
        System.out.println("About Page Loading");
        model.addAttribute("isLogin", true);
        return "about";
    }
    
    @RequestMapping("/services")
    public String servicespage() {
        System.out.println("services Page Loading");
        return "services";
    }
}
