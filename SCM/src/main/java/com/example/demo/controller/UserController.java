package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {
    
    // User Dashboard Page
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "user/dashboard";
        }


    // User Profile Page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile() {
        return "user/profile";
    }
}
