package com.example.reading_tracker.controller;

import com.example.reading_tracker.bean.*;
import com.example.reading_tracker.repository.UserRepository;
import com.example.reading_tracker.service.*;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;
    PublisherService publisherService;
    UserRepository userRepository;
    TagService tagService;
    GenreService genreService;
    SagaService sagaService;
    BookshelfService bookshelfService;

    public BookController(BookService bookService, PublisherService publisherService, UserRepository userRepository, TagService tagService, GenreService genreService, SagaService sagaService, BookshelfService bookshelfService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.userRepository = userRepository;
        this.tagService = tagService;
        this.genreService = genreService;
        this.sagaService = sagaService;
        this.bookshelfService = bookshelfService;
    }


    @GetMapping("book/newBookForm")
    public String showBookForm(Model model, Principal principal){
            List<Genre> genres = genreService.getAllGenres();
            model.addAttribute("genres", genres);
            List<Saga> saga = sagaService.getAllSaga();
            model.addAttribute("sagas", saga);
            List<Tag> tags = tagService.getAllTags();
            System.out.println(tags.size());
            model.addAttribute("tags", tags);
            Collection<Bookshelf> bookshelves = bookshelfService.getAllBookshelves();
            System.out.println(bookshelves.size());
            model.addAttribute("libraries", bookshelves);
            List<Publisher> publishers = publisherService.getAllPublishers();
            System.out.println(publishers.size());
            model.addAttribute("publishers", publishers);
        return "/book/newBookForm";
    }


    @PostMapping("book/addBook")
    public String addABook(Model model, @Valid @ModelAttribute("book") Book book, @ModelAttribute("publisher_name") String publisher_name, @ModelAttribute("tag_name") String tag_names, @ModelAttribute("saga_name") String saga_name, @ModelAttribute("genre_name") String genre,  BindingResult bookBindingResult, Principal principal){
        List<String> errorMessagesToShow = new ArrayList<>();
        /*RACCOLTA DEGLI ERRORI DI VALIDAZIONE
        GLI ERRORI VENGONO MOSTRATI A SCHERMO TRAMITE LA LISTA errorMessagesToShow
        CHE RACCOGLIE IL CAMPO NON VALIDO (error.getField()) E IL MESSAGGIO DI ERRORE (error.getDefaultMessage())*/
        // Se ci sono errori di validazione //
        // 1. ERRORI RIFERITI AGLI ATTRIBUTI DELLA CLASSE UserRegistrationDto
        if(bookBindingResult.hasErrors()) {
            // Per ogni errore di validazione trovato da BindingResult //
            for (ObjectError error : bookBindingResult.getAllErrors()) {
                // System.out.println(error.getDefaultMessage());
                // Recupero il campo non valido
                String fieldErrors = ((FieldError) error).getField();
                // Recupero il messaggio relativo alla mancata validità del dato
                String errorMessage = (error).getDefaultMessage();
                // Costruisco una stringa con i appena ottenuti
                String errorFromFromData = "The data referred to the field " + fieldErrors + " is not valid: " + errorMessage; // Costruisco una stringa con i dati utili
                // Inserisco la stringa in una lista
                errorMessagesToShow.add(errorFromFromData);
            }
            // la lista viene inserita nel model che mostrerà poi i dati all'utente.
            model.addAttribute("errorMessages", errorMessagesToShow);
            // Si viene rindirizzati nuovamente alla pagina della form.
            return "/myErrorPage.html";
        }
        try {
            // SET THE PUBLISHER
            if(!publisherService.checkPublisherByName(publisher_name)){
                Publisher new_publisher = publisherService.addAPublisher(publisher_name);
                book.setPublisher(new_publisher);
            } else {
                book.setPublisher(publisherService.findPublisher(publisher_name));
            }
            System.out.println("Publisher: " + book.getPublisher().getName());
            // SET THE TAG(s)
            // First, we check if the tag is only one or more than one
            Collection<Tag> collection_tags = new ArrayList<>();
            if(tag_names != null) {
                String[] tags = tag_names.split(",");
                for (String s : tags) {
                    if (!tagService.checkTagName(s)) {
                        Tag new_tag = tagService.addATag(s);
                        collection_tags.add(new_tag);
                    } else {
                        collection_tags.add(tagService.findTag(s));
                    }
                }
                book.setTags(collection_tags);
            }
            System.out.println("Tags: " + book.getTags().toString());
            // SET GENRES
            Collection<Genre> collection_genres = new ArrayList<>();
            if(genre != null) {
                String[] genres = genre.split(",");
                for (String s : genres) {
                    if (!genreService.checkGenreName(s)) {
                        Genre new_genre = genreService.addAGenre(s);
                        collection_genres.add(new_genre);
                    } else {
                        collection_genres.add(genreService.findGenre(s));
                    }
                }
                book.setGenres(collection_genres);
            }
            System.out.println("Genres: " + book.getGenres().toString());
            // SET THE SAGA
            if(!sagaService.checkSagaName(saga_name)){
                Saga new_saga = sagaService.addSaga(saga_name, collection_genres, 1);
                book.setSaga(new_saga);
            } else {
                book.setSaga(sagaService.findSaga(saga_name));
                // Saga volumes +1
            }
            System.out.println("Saga: " + book.getSaga().getSaga_name());
            bookService.save(book);

            String message = "The new book " + book.getTitle().toUpperCase() +  " is created";
            model.addAttribute("message", message);
/*            User user = userRepository.findUserByUsername(principal.getName());
            model.addAttribute("username", principal.getName());
            model.addAttribute("email", user.getEmail());*/

        }catch(NullPointerException e){
            String message = e.getMessage();
            model.addAttribute("message", message);
        } catch(ValidationException e){
            String errorFromFromData = e.getMessage();
            errorMessagesToShow.add(errorFromFromData);
            model.addAttribute("errorMessages", errorMessagesToShow);
        }
        // indirizzo pagina che verrà visualizzata
        return "/index.html";
    }


}
