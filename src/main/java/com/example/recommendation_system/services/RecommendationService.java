package com.example.recommendation_system.services;

import com.example.recommendation_system.entities.Course;
import com.example.recommendation_system.entities.UserPreferences;
import com.example.recommendation_system.repositories.CourseRepository;
import com.example.recommendation_system.repositories.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Save preferences
    public UserPreferences savePreferences(UserPreferences preferences) {
        return userPreferencesRepository.save(preferences);
    }

    // Get recommendations based on user preferences
    public List<Course> getRecommendations(UserPreferences preferences) {
        if (preferences == null) {
            return courseRepository.findAll(); // or some default logic
        }

        // Recommendation logic (e.g., filtering by category)
        return courseRepository.findByCategory(preferences.getPreferredCategory());
    }

    // Get all courses (for testing or fetching all courses)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get preferences by user ID
    public Optional<UserPreferences> getPreferences(Long id) {
        return userPreferencesRepository.findById(id);
    }
}
