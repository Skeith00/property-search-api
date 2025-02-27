package com.sergimontanes.api.controller;

import com.sergimontanes.api.dto.PropertyDTO;
import com.sergimontanes.api.dto.PropertySearchDTO;
import com.sergimontanes.api.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST API controller
@RequestMapping("/property") // Base URL for this controller
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public String storeProperty(@RequestBody PropertyDTO property) {
        try {
            propertyService.storeProperty(property);
            return "Search executed, check logs for results.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropertyDTO> search(@RequestBody PropertySearchDTO search) {
        return propertyService.searchProperties(search);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropertyDTO getProperty(@RequestParam Long propertyId) {
        return propertyService.getProperty(propertyId);
    }

}
