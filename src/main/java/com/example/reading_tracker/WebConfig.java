package com.example.reading_tracker;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Crea una mappatura diretta tra il nome della view e il suo URL, tramite ViewControllerRegistry (qui,
 * riportato come registry).
 */
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/myErrorPage").setViewName("myErrorPage");
        registry.addViewController("/perform_logout").setViewName("perform_logout");
        registry.addViewController("/user/allTheLibraries").setViewName("allTheLibraries");
        registry.addViewController("/user/addLibrary").setViewName("addLibrary");
        registry.addViewController("/book/newBookForm").setViewName("newBookForm");
    }

}

