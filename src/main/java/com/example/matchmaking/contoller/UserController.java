package com.example.matchmaking.contoller;


import com.example.matchmaking.dto.CompatibilityResponse;
import com.example.matchmaking.dto.UserRequest;
import com.example.matchmaking.dto.UserResponse;
import com.example.matchmaking.enitity.User;
import com.example.matchmaking.enums.Status;
import com.example.matchmaking.response.BaseResponse;
import com.example.matchmaking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String healthCheck() {
        return "{\"message\": \"Welcome to the Dating App Matchmaking API\"}";
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<BaseResponse<User, String>> createUser(@Valid @RequestBody UserRequest userRequest) {
        BaseResponse<User, String> response = new BaseResponse<>();
        User savedUser = userService.createUser(userRequest);
        response.setData(savedUser);
        response.setStatus(Status.SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // Get matches for a user
    @PostMapping("/api/v1/match/{userId}")
    public ResponseEntity<BaseResponse<List<UserResponse>, String>> getMatches(@PathVariable String userId) {
        BaseResponse<List<UserResponse>, String> response = new BaseResponse<>();
        response.setData(userService.generateMatches(userId));
        response.setStatus(Status.SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Get compatibility between two users
    @GetMapping("/api/v1/compatibility/{userId1}/{userId2}")
    public ResponseEntity<BaseResponse<CompatibilityResponse, String>> getCompatibility(
            @PathVariable String userId1, @PathVariable String userId2) {
        BaseResponse<CompatibilityResponse, String> response = new BaseResponse<>();
        response.setData(userService.getCompatibility(userId1, userId2));
        response.setStatus(Status.SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
