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

    // Save user preferences
    @PostMapping("/preferences")
    public ResponseEntity<UserPreferences> savePreferences(@RequestBody UserPreferences preferences) {
        System.out.println(preferences);
        UserPreferences savedPreferences = recommendationService.savePreferences(preferences);
        return ResponseEntity.ok(savedPreferences);
    }

    // Get recommendations based on user preferences
    @PostMapping("/recommendations")
    public ResponseEntity<List<Course>> getRecommendations(@RequestBody UserPreferences preferences) {
        System.out.println(preferences);
        List<Course> recommendedCourses = recommendationService.getRecommendations(preferences);
        return ResponseEntity.ok(recommendedCourses);
    }

    // Get all courses
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = recommendationService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Get preferences by user ID
    @GetMapping("/preferences/{id}")
    public ResponseEntity<UserPreferences> getPreferences(@PathVariable Long id) {
        Optional<UserPreferences> preferences = recommendationService.getPreferences(id);
        return preferences.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
