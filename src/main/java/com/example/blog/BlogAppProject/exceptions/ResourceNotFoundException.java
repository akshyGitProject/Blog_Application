package com.example.blog.BlogAppProject.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourceNotFoundException extends RuntimeException{
    String ResourceName;
    String FieldName;
    long FieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        ResourceName = resourceName;
        FieldName = fieldName;
        FieldValue = fieldValue;
    }
}
