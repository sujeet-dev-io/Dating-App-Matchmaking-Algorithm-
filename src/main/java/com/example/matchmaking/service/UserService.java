package com.example.matchmaking.service;

import com.example.matchmaking.dto.CompatibilityResponse;
import com.example.matchmaking.dto.UserRequest;
import com.example.matchmaking.dto.UserResponse;
import com.example.matchmaking.enitity.User;

import java.util.List;

public interface UserService {

    public User createUser(UserRequest userRequest);
    List<UserResponse> generateMatches(String userId);
    CompatibilityResponse getCompatibility(String userId1, String userId2);
}
