package com.sergimontanes.api.controller;

import com.sergimontanes.api.dto.PropertyDTO;
import com.sergimontanes.api.dto.PropertySearchDTO;
import com.sergimontanes.api.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST API controller
@RequestMapping("/search/property") // Base URL for this controller
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public String searchProperty() {
        return "Hello, Spring Boot!";
    }

    @PostMapping("/property/store")
    public String storeProperty(@RequestBody PropertyDTO property) {
        try {
            propertyService.storeProperty(property);
            return "Search executed, check logs for results.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/property/search")
    public List<PropertyDTO> search(@RequestBody PropertySearchDTO search) {
        return propertyService.searchProperties(search);
    }

    @GetMapping("/property/{id}")
    public PropertyDTO getProperty(@RequestParam Long propertyId) {
        return propertyService.getProperty(propertyId);
    }

}
