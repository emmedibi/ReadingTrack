package com.example.reading_tracker.controller;

import com.example.reading_tracker.bean.CustomUserDetails;
import com.example.reading_tracker.bean.User;
import com.example.reading_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

    // Pagine generiche - accessibili a tutti //
    @RequestMapping("/login")
    public String login() {
        System.out.println("login controller");
        return "login.html";
    }
    @RequestMapping("/myErrorPage")
    public String myErrorPage() { return "myErrorPage.html";}
    @GetMapping("/index")
    public String goToIndex() {
        System.out.println("index controller");
        return "index.html";
    }
    @RequestMapping("/logout")
    public String logout() {
        return "perform_logout.html";
    }

    @RequestMapping("/mapping")
    public String myMethod(Principal principal, Model model){
        CustomUserDetails userDetails = (CustomUserDetails)principal;
        System.out.println("mapping");
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("email", "user.getEmail()");
        return "/";
    }

    @GetMapping("/")
    public String helloGfg(Principal principal, Authentication auth, Model model) {
        // Get the Username
        String userName = principal.getName();
        System.out.println("Current Logged in User is: " + userName);

        // Get the User Roles/Authorities
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        System.out.println("Current Logged in User Roles are: " + authorities);
        User user = userRepository.findUserByUsername(userName);
        model.addAttribute("username", userName);
        model.addAttribute("roles", authorities);
        model.addAttribute("email", user.getEmail());

        return "index.html";
    }

}
