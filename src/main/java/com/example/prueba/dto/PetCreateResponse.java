package com.example.prueba.dto;

import java.time.LocalDateTime;
import lombok.Data;

// Request
@Data
public class PetCreateResponse {
    private String transactionId;
    private LocalDateTime dateCreated;
    private String status;
    private String name;
    // Getters and setters
}
