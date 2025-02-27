package com.sergimontanes.api.service;

import com.sergimontanes.api.dto.PropertyDTO;
import com.sergimontanes.api.dto.PropertySearchDTO;
import com.sergimontanes.api.error.ResourceNotFoundException;
import com.sergimontanes.api.model.Property;
import com.sergimontanes.api.repository.PropertyRepository;
import com.sergimontanes.api.repository.PropertySpecification;
import com.sergimontanes.api.transformer.PropertyTransformer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    //private final ElasticsearchService<Document> elasticsearchService;
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        //this.elasticsearchService = elasticsearchService;
        this.propertyRepository = propertyRepository;
    }

    public void storeProperty(PropertyDTO propertyDTO) {
        Property property = PropertyTransformer.transformProperty(propertyDTO);
        property.setUpdatedAt(LocalDateTime.now());
        property.setCreatedAt(LocalDateTime.now());
        propertyRepository.save(property);
    }

    public List<PropertyDTO> searchProperties(PropertySearchDTO search) {
        Specification<Property> spec = PropertySpecification.filterBy(search);
        List<Property> properties = propertyRepository.findAll(spec);
        return properties.stream().map(PropertyTransformer::transformPropertyDTO).toList();
    }

    public PropertyDTO getProperty(Long id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        Property property = optionalProperty.orElseThrow(() -> new ResourceNotFoundException("Property" + id + " not found"));
        return PropertyTransformer.transformPropertyDTO(property);
    }
}
