package com.example.prueba.dto;

import lombok.Data;

// Request
@Data
public class PetResponse {
    private Long id;
    private String name;
    private String status;
    // Getters and setters
}

