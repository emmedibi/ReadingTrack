package com.example.reading_tracker.controller;

import com.example.reading_tracker.bean.CustomUserDetails;
import com.example.reading_tracker.bean.User;
import com.example.reading_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/successlogin")
public class SuccessLoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String index(Model model, Principal principal){
        CustomUserDetails userDetails = (CustomUserDetails)principal;
        System.out.println("success login");
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("email", user.getEmail());
        return "/index.html";

    }

}
