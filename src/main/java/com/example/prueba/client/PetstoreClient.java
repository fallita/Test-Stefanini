package com.example.prueba.client;

import com.example.prueba.dto.PetRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.prueba.dto.PetResponse;

@Component
public class PetstoreClient {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://petstore.swagger.io/v2/pet/";

    public PetstoreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PetResponse getPetById(Long petId) {
        return restTemplate.getForObject(BASE_URL + petId, PetResponse.class);
    }

    public PetResponse createPet(PetRequest petRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PetRequest> request = new HttpEntity<>(petRequest, headers);
        return restTemplate.postForObject("https://petstore.swagger.io/v2/pet", request, PetResponse.class);
    }
}