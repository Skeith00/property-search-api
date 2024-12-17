package com.sergimontanes.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Marks this class as a REST API controller
@RequestMapping("/search/property") // Base URL for this controller
public class SearchPropertyController {

    // A simple GET endpoint
    @PostMapping
    public String searchProperty() {
        return "Hello, Spring Boot!";
    }

}
