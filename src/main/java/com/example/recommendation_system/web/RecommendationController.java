package com.example.recommendation_system.web;

import com.example.recommendation_system.entities.Course;
import com.example.recommendation_system.entities.UserPreferences;
import com.example.recommendation_system.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/preferences")
    public ResponseEntity<UserPreferences> savePreferences(@RequestBody UserPreferences preferences) {
        UserPreferences savedPreferences = recommendationService.savePreferences(preferences);
        return ResponseEntity.ok(savedPreferences);
    }

    @PostMapping("/recommendations")
    public ResponseEntity<List<Course>> getRecommendations(@RequestBody UserPreferences preferences) {
        List<Course> recommendedCourses = recommendationService.getRecommendations(preferences);
        return ResponseEntity.ok(recommendedCourses);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = recommendationService.getRecommendations(null);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/preferences/{id}")
    public ResponseEntity<UserPreferences> getPreferences(@PathVariable Long id) {
        Optional<UserPreferences> preferences = recommendationService.getPreferences(id);
        return preferences.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
