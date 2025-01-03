package com.example.recommendation_system.services;

import com.example.recommendation_system.entities.Course;
import com.example.recommendation_system.entities.UserPreferences;
import com.example.recommendation_system.repositories.CourseRepository;
import com.example.recommendation_system.repositories.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Save user preferences
    public UserPreferences savePreferences(UserPreferences preferences) {
        return userPreferencesRepository.save(preferences);
    }

    // Get recommendations based on user preferences
    public List<Course> getRecommendations(UserPreferences preferences) {
        if (preferences == null) {
            return courseRepository.findAll(); // Return all courses if no preferences provided
        }

        return courseRepository.findAll().stream()
                .filter(course -> course.getCategory().equalsIgnoreCase(preferences.getPreferredCategory()))
                .filter(course -> preferences.getSkills().stream()
                        .anyMatch(skill -> course.getTitle().toLowerCase().contains(skill.toLowerCase())))
                .filter(course -> isLevelCompatible(course.getLevel(), preferences.getLevel()))
                .sorted(Comparator.comparingDouble(Course::getSimilarity).reversed())
                .collect(Collectors.toList());
    }

    // Check if course level is compatible with user level
    private boolean isLevelCompatible(String courseLevel, String userLevel) {
        List<String> levels = List.of("Beginner", "Intermediate", "Advanced");
        int courseIndex = levels.indexOf(courseLevel);
        int userIndex = levels.indexOf(userLevel);
        return courseIndex >= userIndex;
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
