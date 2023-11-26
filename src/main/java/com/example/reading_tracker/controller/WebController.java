package com.example.reading_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    // Pagine generiche - accessibili a tutti //
    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }
    @RequestMapping("/myErrorPage")
    public String myErrorPage() { return "myErrorPage.html";}
    @GetMapping("/index")
    public String goToIndex() {
        return "index.html";
    }
    @RequestMapping("/logout")
    public String logout() {
        return "perform_logout.html";
    }

}
