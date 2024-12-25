package com.example.matchmaking.service.Impl;

import com.example.matchmaking.dto.CompatibilityResponse;
import com.example.matchmaking.dto.UserRequest;
import com.example.matchmaking.dto.UserResponse;
import com.example.matchmaking.enitity.User;
import com.example.matchmaking.exception.UserNotFoundException;
import com.example.matchmaking.repository.UserRepository;
import com.example.matchmaking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setGender(userRequest.getGender());
        user.setInterestedIn(userRequest.getInterestedIn());
        user.setLocation(userRequest.getLocation());
        user.setHobbies(userRequest.getHobbies());
        user.setInterests(userRequest.getInterests());
        user.setOccupation(userRequest.getOccupation());
        user.setEducationLevel(userRequest.getEducationLevel());
        user.setPersonalityTraits(userRequest.getPersonalityTraits());

        // Generate sequential user ID
        long count = userRepository.count() + 1;
        user.setUserId("user" + count);

        return userRepository.save(user);
    }

    @Override
    public List<UserResponse> generateMatches(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found : " + userId));

        List<User> potentialMatches = userRepository.findAll().stream()
                .filter(u -> !u.getUserId().equals(userId) && u.getGender().equals(user.getInterestedIn()))
                .toList();

        return potentialMatches.stream().map(match -> {
                    double score = calculateCompatibility(user, match);
                    List<String> commonInterests = new ArrayList<>(user.getInterests());
                    commonInterests.retainAll(match.getInterests());

                    List<String> commonHobbies = new ArrayList<>(user.getHobbies());
                    commonHobbies.retainAll(match.getHobbies());

                    return new UserResponse(match.getUserId(), match.getName(), score, commonInterests, commonHobbies);
                }).sorted(Comparator.comparingDouble(UserResponse::getCompatibilityScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public CompatibilityResponse getCompatibility(String userId1, String userId2) {
        User user1 = userRepository.findByUserId(userId1).orElse(null);
        User user2 = userRepository.findByUserId(userId2).orElse(null);

        if (user1 == null || user2 == null) {
            List<String> missingUsers = new ArrayList<>();
            if (user1 == null) missingUsers.add(userId1);
            if (user2 == null) missingUsers.add(userId2);

            throw new UserNotFoundException("User id not found : " + String.join(", ", missingUsers));
        }
        double score = calculateCompatibility(user1, user2);
        return new CompatibilityResponse(userId1, userId2, score);
    }


    private double calculateCompatibility(User user1, User user2) {
        double interestScore = user1.getInterests().isEmpty() ? 0 : (double) user1.getInterests().stream()
                .filter(user2.getInterests()::contains).count() / user1.getInterests().size();

        double hobbyScore = user1.getHobbies().isEmpty() ? 0 : (double) user1.getHobbies().stream()
                .filter(user2.getHobbies()::contains).count() / user1.getHobbies().size();

        return (interestScore + hobbyScore) / 2;
    }
}