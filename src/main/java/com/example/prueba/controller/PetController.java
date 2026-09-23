package com.example.prueba.controller;

import com.example.prueba.dto.PetCreateResponse;
import com.example.prueba.dto.PetRequest;
import com.example.prueba.dto.PetResponse;
import com.example.prueba.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResponse> getPet(@PathVariable Long petId) {
        try {
            PetResponse pet = petService.getPetById(petId);
            return ResponseEntity.ok(pet);
        } catch (HttpClientErrorException.NotFound ex) {
            // Log the error and return 404
            System.out.println("External API returned 404 for pet id: " + petId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PetCreateResponse createPet(@RequestBody PetRequest petRequest) {
        return petService.createPet(petRequest);
    }

    // This handler catches the 404 from the external API
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handlePetNotFound(HttpClientErrorException.NotFound ex) {
        // Return a clean 404 to your client
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Pet not found in the external store. Please try a different ID.");
    }
}
