package com.example.reading_tracker.controller;

import com.example.reading_tracker.bean.Bookshelf;
import com.example.reading_tracker.bean.User;
import com.example.reading_tracker.service.BookshelfService;
import com.example.reading_tracker.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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

    @PostMapping("user/addLibrary")
    public String addLibrary(Model model, @Valid @ModelAttribute("bookshelf") Bookshelf bookshelf, BindingResult bookshelfBindingResult, Principal principal){
        String message = "";
        List<String> errorMessagesToShow = new ArrayList<>();
        String errorFromFromData = null;
        /*RACCOLTA DEGLI ERRORI DI VALIDAZIONE
        GLI ERRORI VENGONO MOSTRATI A SCHERMO TRAMITE LA LISTA errorMessagesToShow
        CHE RACCOGLIE IL CAMPO NON VALIDO (error.getField()) E IL MESSAGGIO DI ERRORE (error.getDefaultMessage())*/
        // Se ci sono errori di validazione //
        // 1. ERRORI RIFERITI AGLI ATTRIBUTI DELLA CLASSE UserRegistrationDto
        if(bookshelfBindingResult.hasErrors()) {
            // Per ogni errore di validazione trovato da BindingResult //
            for (ObjectError error : bookshelfBindingResult.getAllErrors()) {
                // System.out.println(error.getDefaultMessage());
                // Recupero il campo non valido
                String fieldErrors = ((FieldError) error).getField();
                // Recupero il messaggio relativo alla mancata validità del dato
                String errorMessage = (error).getDefaultMessage();
                // Costruisco una stringa con i appena ottenuti
                errorFromFromData = "The data referred to the field " + fieldErrors + " is not valid: " + errorMessage; // Costruisco una stringa con i dati utili
                // Inserisco la stringa in una lista
                errorMessagesToShow.add(errorFromFromData);
            }
            // la lista viene inserita nel model che mostrerà poi i dati all'utente.
            model.addAttribute("errorMessages", errorMessagesToShow);
            // Si viene rindirizzati nuovamente alla pagina della form.
            return "/myErrorPage.html";
        }
        try {
            User user = customUserDetailsService.findByUsername(principal.getName());
            System.out.println(bookshelf.getName());
            bookshelf.setUser(user);
            bookshelfService.save(bookshelf);
            message = "The new bookshelf " + bookshelf.getName().toUpperCase() +  " is created";
            model.addAttribute("message", message);
            List<Bookshelf> bookshelves= bookshelfService.findAllByUser(user.getId());
            model.addAttribute("libraries", bookshelves);
            System.out.println("Valore libraries: " + bookshelves.size());
        } catch(NullPointerException e){
            message = e.getMessage();
            model.addAttribute("message", message);
        } catch(ValidationException e){
            errorFromFromData = e.getMessage();
            errorMessagesToShow.add(errorFromFromData);
            model.addAttribute("errorMessages", errorMessagesToShow);
        }
        catch (Exception e) {
            message = "Could not save the bookshelf ";
            model.addAttribute("message", message);
        }
        // indirizzo pagina che verrà visualizzata
        return "/user/allTheLibraries.html";
    }

}
