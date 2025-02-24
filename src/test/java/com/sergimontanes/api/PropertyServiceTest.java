package com.sergimontanes.api;

import com.sergimontanes.api.dto.PropertyDTO;
import com.sergimontanes.api.dto.PropertySearchDTO;
import com.sergimontanes.api.model.Property;
import com.sergimontanes.api.repository.PropertyRepository;
import com.sergimontanes.api.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PropertyServiceTest {

    @BeforeEach
    void setUp() {
        // Setup if needed before each test
    }

    @Test
    void storeProperty_ShouldSaveProperty() {
        // Arrange
        PropertyDTO propertyDTO = new PropertyDTO();
        Property property = new Property();

        // Act
        PropertyRepository propertyRepository = mock(PropertyRepository.class);
        PropertyService propertyService = new PropertyService(propertyRepository);
        propertyService.storeProperty(propertyDTO);

        // Assert
        verify(propertyRepository, times(1)).save(property);
    }

    @Test
    void searchProperties_ShouldReturnPropertyDTOList() {

        // Arrange
        PropertySearchDTO searchDTO = new PropertySearchDTO();
        searchDTO.setMinBathrooms(1);
        searchDTO.setMinBedrooms(1);

        Property property = new Property();
        property.setId(1L);


        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId("1");
        List<Property> propertyList = List.of(property);

        PropertyRepository propertyRepository = mock(PropertyRepository.class);
        when(propertyRepository.findAll(any(Specification.class))).thenReturn(propertyList);

        // Act
        PropertyService propertyService = new PropertyService(propertyRepository);
        List<PropertyDTO> result = propertyService.searchProperties(searchDTO);

        // Assert
        assertThat(result).hasSize(1).containsExactly(propertyDTO);
        verify(propertyRepository, times(1)).findAll(any(Specification.class));
    }
}