package com.example.matchmaking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Age cannot be null")
    private Integer age;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;

    @NotBlank(message = "InterestedIn cannot be blank")
    private String interestedIn;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotEmpty(message = "Hobbies cannot be empty")
    private List<String> hobbies;

    @NotEmpty(message = "Interests cannot be empty")
    private List<String> interests;

    @NotBlank(message = "Occupation cannot be blank")
    private String occupation;

    @NotBlank(message = "EducationLevel cannot be blank")
    private String educationLevel;

    @NotEmpty(message = "PersonalityTraits cannot be empty")
    private List<String> personalityTraits;
}
