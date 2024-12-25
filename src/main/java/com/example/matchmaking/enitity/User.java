package com.example.matchmaking.enitity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String userId;

    private String name;
    private int age;
    private String gender;
    private String interestedIn;
    private String location;

    @ElementCollection
    private List<String> hobbies;

    @ElementCollection
    private List<String> interests;

    private String occupation;
    private String educationLevel;

    @ElementCollection
    private List<String> personalityTraits;

}


