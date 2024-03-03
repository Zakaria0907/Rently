package com.rently.rentlyAPI.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class AbstractEntityTest {

    @Test
    public void testNoArgsConstructor() {
        // Arrange


        // Act
        AbstractEntity testAbstractEntity = new AbstractEntity();

        // Assert
        assertNull(testAbstractEntity.getId());
        assertNull(testAbstractEntity.getCreationDate());
        assertNull(testAbstractEntity.getLastModifiedDate());

    }

    @Test
    public void testBuilder() {
        // Arrange


        // Act
        AbstractEntity testAbstractEntity = AbstractEntity.builder().build();

        // Assert
        assertNull(testAbstractEntity.getId());
        assertNull(testAbstractEntity.getCreationDate());
        assertNull(testAbstractEntity.getLastModifiedDate());

    }
    
}
