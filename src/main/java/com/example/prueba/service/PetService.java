package com.example.prueba.service;

import com.example.prueba.client.PetstoreClient;
import com.example.prueba.dto.PetCreateResponse;
import com.example.prueba.dto.PetRequest;
import com.example.prueba.dto.PetResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PetService {
    private final PetstoreClient petstoreClient;

    public PetService(PetstoreClient petstoreClient) {
        this.petstoreClient = petstoreClient;
    }

    public PetResponse getPetById(Long petId) {
        PetResponse pet = petstoreClient.getPetById(petId);
        System.out.println("Pet obtained: " + pet); // Print to console
        return pet;
    }

    public PetCreateResponse createPet(PetRequest petRequest) {
        PetResponse externalResponse = petstoreClient.createPet(petRequest);
        System.out.println("External API response: " + externalResponse); // Print to console

        PetCreateResponse response = new PetCreateResponse();
        response.setTransactionId(UUID.randomUUID().toString());
        response.setDateCreated(LocalDateTime.now());
        response.setStatus(externalResponse.getStatus());
        response.setName(externalResponse.getName());
        return response;
    }
}
