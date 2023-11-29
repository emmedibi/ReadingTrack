package com.example.reading_tracker.controller;

import com.example.reading_tracker.bean.Bookshelf;
import com.example.reading_tracker.bean.User;
import com.example.reading_tracker.service.BookshelfService;
import com.example.reading_tracker.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class BookshelfController {

    @Autowired
    BookshelfService bookshelfService;
    CustomUserDetailsService customUserDetailsService;

    public BookshelfController(BookshelfService bookshelfService, CustomUserDetailsService customUserDetailsService) {
        this.bookshelfService = bookshelfService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("user/allTheLibraries")
    public String getAllTheUserLibraries(Principal principal, Model model){
        String message = "";
        try {
            User user = customUserDetailsService.findByUsername(principal.getName());
            List<Bookshelf> bookshelves= bookshelfService.findAllByUser(user.getId());
            model.addAttribute("libraries", bookshelves);
            System.out.println("Valore libraries: " + bookshelves.size());
        } catch (Exception e){
            message = "Errore";
            model.addAttribute("message", message);
            return "/user/allTheLibraries.html";
        }
        return "/user/allTheLibraries.html";
    }

}
