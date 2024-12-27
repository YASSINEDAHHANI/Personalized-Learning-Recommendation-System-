package com.example.recommendation_system.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CourseRecommendationService {
    private final RestTemplate restTemplate;

    public CourseRecommendationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCourseRecommendations(String url, Object requestPayload) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json");

        HttpEntity<Object> entity = new HttpEntity<>(requestPayload, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}
