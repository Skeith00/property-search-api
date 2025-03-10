package com.sergimontanes.api.controller;

import com.sergimontanes.api.dto.PropertyDTO;
import com.sergimontanes.api.dto.PropertySearchDTO;
import com.sergimontanes.api.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> storeProperty(@RequestBody PropertyDTO property) {
        propertyService.storeProperty(property);
        return ResponseEntity.ok("OK");
    }

    @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PropertyDTO>> search(@RequestBody PropertySearchDTO search) {
        return ResponseEntity.ok(propertyService.searchProperties(search));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getProperty(id));
    }
}
